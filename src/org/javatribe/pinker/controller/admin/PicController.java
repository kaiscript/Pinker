package org.javatribe.pinker.controller.admin;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


@Controller
@RequestMapping("/pic")
public class PicController {
	
	@RequestMapping(value="/upload")
	@ResponseBody
	public Map<String,String> uploadPic(@RequestParam MultipartFile photo,HttpServletRequest request,HttpServletResponse response) throws IOException{
		String realPath=request.getSession().getServletContext().getRealPath("/pic");
		File file=new File(realPath+"/"+photo.getOriginalFilename());
		Map<String,String> map=new HashMap<String,String>();
		response.setContentType("text/plain; charset=UTF-8");  
		try {
			FileUtils.copyInputStreamToFile(photo.getInputStream(), file);
			map.put("success", "true");
			map.put("photoName", photo.getOriginalFilename());
			return map;//成功
		} catch (IOException e) {
			e.printStackTrace();
		}
		map.put("success","false");
		return map;//失败
	}
}
