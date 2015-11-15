package org.javatribe.pinker.controller;

import java.util.List;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.javatribe.pinker.entity.Course;
import org.javatribe.pinker.entity.Student;
import org.javatribe.pinker.service.CourseService;
import org.javatribe.pinker.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kaiscript
 * 2015年10月21日 下午8:05:33
 */
@Controller
@RequestMapping("/course")
public class CourseReturnController {
	
	@Resource(name = "studentServiceImpl")
	private StudentService studentService;
	
	@Resource(name = "courseServiceImpl")
	private CourseService courseService;
	
	@RequestMapping(value="/getElectiveCourse/{elective_category}",method=RequestMethod.POST)
	public void getElectiveCourse(@PathVariable int elective_category){
		
	}
	
	@RequestMapping(value = "/attentionCourse/{stu_id}.json",method=RequestMethod.GET)
	@ResponseBody
	public JSONArray getAttentionCourse(@PathVariable int stu_id){
		JSONArray jsonArr = new JSONArray();
		List<Course> courses = courseService.getByIdSet(getCourseIdSet(stu_id));
		for(Course c:courses){
			JSONObject json = new JSONObject();
			json.put("courseId", c.getCrs_id());
			json.put("courseName", c.getCrs_name());
			json.put("courseDesc", c.getCrs_desc());
			jsonArr.add(json);
		}
		return jsonArr;
	}
	
	/**
	 * 根据学生id取得所关注课程的idSet
	 * @param stu_id
	 * @return
	 */
	public Integer[] getCourseIdSet(int stu_id){
		Student student = studentService.getById(stu_id);
		String attentionCourse = student.getStu_attn_crs_ids();
		String[] strCourses = attentionCourse.split(",");
		Integer[] coursesIdSet = new Integer[strCourses.length];
		for(int i=0;i<strCourses.length;i++){
			coursesIdSet[i]=Integer.parseInt(strCourses[i]);
		}
		return coursesIdSet;
	}
}
