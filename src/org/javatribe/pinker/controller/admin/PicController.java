package org.javatribe.pinker.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.commons.io.FileUtils;
import org.javatribe.pinker.entity.Picture;
import org.javatribe.pinker.entity.Student;
import org.javatribe.pinker.service.PicService;
import org.javatribe.pinker.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.qcloud.PicCloud;

/**
 * @author kaiscript
 * 2015年10月15日 下午8:11:51
 */
@Controller
@RequestMapping("/pic")
public class PicController {
	/**
	 * 万象优图项目实例信息
	 */
	int APP_ID_V2 =10009183 ;	
	String SECRET_ID_V2 ="AKIDjSJSiYB9YjHYHKnkEGmsPr6oYvSYtkjT";
	String SECRET_KEY_V2 ="XHeCvEaWrglnhmdAcchBDnrUYAckJy8C";
	String BUCKET ="kaiscript";
	
	PicCloud pc = new PicCloud(APP_ID_V2, SECRET_ID_V2, SECRET_KEY_V2, BUCKET);
	
	private StudentService studentService;
	private PicService picService;
	
	@Resource(name = "picServiceImpl")
	public void setPicService(PicService picService) {
		this.picService = picService;
	}

	@Resource(name = "studentServiceImpl")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@RequestMapping(value="/upload")
	@ResponseBody
	public Map<String,String> uploadPic(@RequestParam MultipartFile photo,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String realPath=request.getSession().getServletContext().getRealPath("/pic");
		File file=new File(realPath+"/"+photo.getOriginalFilename());
		Map<String,String> map=new HashMap<String,String>();
		response.setContentType("text/plain; charset=UTF-8");  
		try {
			FileUtils.copyInputStreamToFile(photo.getInputStream(), file);
			map.put("success", "true");//成功
			map.put("photoName", photo.getOriginalFilename());
			return map;
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put("success","false");
		return map;//失败
	}
	
	/**
	 * 返回 腾讯云 万象优图 签名sign
	 * @return 万象优图签名
	 */
	//  http://web.image.myqcloud.com/photos/v2/APP_ID_V2/kaiscript/0
	@RequestMapping("/getsign")
	@ResponseBody
	public JSONObject getSign(){
		long expired = System.currentTimeMillis()/1000 + 3600;
		String sign = pc.getSign(expired);
		JSONObject jsonObject = new JSONObject();
		jsonObject.put("url","http://web.image.myqcloud.com/photos/v2/"+APP_ID_V2+"/"+BUCKET+"/0/");
		jsonObject.put("sign",sign.toString());
		return jsonObject;
	}
	
	/**
	 * 	
	 * @param userid
	 * @param url
	 * @param downloadurl
	 * @return
	 */
	@RequestMapping("/uploadHeadimg")
	@ResponseBody
	public JSONObject updateHeadimg(String userid,String url,String downloadurl){ //参数名字与前端传递的参数相同，则参数前面可以省略@PathParam
		int userId = Integer.valueOf(userid);
		Student student = studentService.getById(userId);
		downloadurl+="?imageView2/2/w/400/h/600/q/85";//万象优图 图像处理接口添加   ?imageView2/2/w/400/h/600/q/85
		student.setStu_head_img(downloadurl);
		JSONObject jsonObject = new JSONObject();
		if(studentService.update(student)){//更新相应学生实体
			jsonObject.put("code", 0); //0代表成功，1失败
		}
		else{
			jsonObject.put("code", 1);
		}
		Picture pic = picService.getById(userId);
		if(pic == null){
			pic = new Picture(userId, url, downloadurl);
			picService.save(pic);
		}
		else{//图片非空，则更新链接
			pic.setPic_url(url);
			pic.setPic_download_url(downloadurl);
			picService.update(pic);
		}
		return jsonObject;
	}
	
}
