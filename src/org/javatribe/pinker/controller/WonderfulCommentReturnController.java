package org.javatribe.pinker.controller;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.javatribe.pinker.entity.Student;
import org.javatribe.pinker.entity.Wonderful_comment;
import org.javatribe.pinker.service.StudentService;
import org.javatribe.pinker.service.WonderfulCommentService;
import org.javatribe.pinker.util.FormatTrans;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/** @author kaiscript
 * 2015年10月26日 下午9:22:16
 */
@Controller
@RequestMapping(value = "/wonderfulcomment")
public class WonderfulCommentReturnController {
	
	private WonderfulCommentService wonderfulCommentService;
	private StudentService studentService;
	
	@Resource(name = "wonderfulServiceImpl")
	public void setWonderfulCommentService(
			WonderfulCommentService wonderfulCommentService) {
		this.wonderfulCommentService = wonderfulCommentService;
	}
	
	@Resource(name = "studentServiceImpl")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@RequestMapping(value="/all.json",method=RequestMethod.GET)
	@ResponseBody
	public JSONArray getAll(){
		JSONArray JSONarr = new JSONArray();
		List<Wonderful_comment> comments = wonderfulCommentService.getAllList();
		for(Wonderful_comment c:comments){
			JSONObject json = new JSONObject();
			String username = getName(c.getComment().getCmt_user_id());
			json.put("commentId", c.getComment().getCmt_id());
			json.put("userId", c.getComment().getCmt_user_id());
			json.put("userHeadImg", getHeadImg(c.getComment().getCmt_user_id()));
			json.put("userName", username);
			json.put("teaName", c.getComment().getCourse().getCrs_teacher_name());
			json.put("courseId", c.getComment().getCourse().getCrs_id());
			json.put("courseName", c.getComment().getCourse().getCrs_name());
			json.put("courseDesc", c.getComment().getCourse().getCrs_desc());
			json.put("data", FormatTrans.getHowLongTime(c.getComment().getCmt_time()));
			json.put("content", c.getComment().getCmt_content());
			json.put("good", c.getComment().getCmt_like_number());
			json.put("bad", c.getComment().getCmt_against_number());
			
		}
		return JSONarr;
	}
	
	/**
	 * 根据用户id长度判断是学生还是教师，从而去对应的学生或教师表查询 用户名字
	 * 
	 * @param user_id
	 *            用户id
	 * @return 用户姓名
	 */
	public String getName(int user_id) {
		String id = String.valueOf(user_id);
		// 学生学号长度为9
		if (id.length() == 9) {
			Student student = studentService.getById(user_id);
			return student.getStu_name();
		}
		return "";
	}

	/**
	 * 根据用户id长度判断是学生还是教师，从而去对应的学生或教师表查询 用户头像url
	 * 
	 * @param user_id
	 * @return
	 */
	public String getHeadImg(int user_id) {
		String id = String.valueOf(user_id);
		if (id.length() == 9) {
			Student student = studentService.getById(user_id);
			return student.getStu_head_img();
		}
		return "";
	}
	
}
