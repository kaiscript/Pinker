package org.javatribe.pinker.controller;

import java.util.Date;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import org.javatribe.pinker.entity.Student;
import org.javatribe.pinker.entity.Teacher;
import org.javatribe.pinker.service.DepartmentService;
import org.javatribe.pinker.service.MajorService;
import org.javatribe.pinker.service.StudentService;
import org.javatribe.pinker.service.TeacherService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Mars
 * 
 *         2015年10月14日 下午9:57:55
 */

@Controller
@RequestMapping("/user")
public class UserController {

	private StudentService studentService;
	private TeacherService teacherService;
	private DepartmentService departmentService;
	private MajorService majorService;

	@Resource(name = "teacherServiceImpl")
	public void setTeacherService(TeacherService teacherService) {
		this.teacherService = teacherService;
	}

	@Resource(name = "studentServiceImpl")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@Resource(name = "departmentServiceImpl")
	public void setDepeDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@Resource(name = "majorServiceImpl")
	public void setMajorService(MajorService majorService) {
		this.majorService = majorService;
	}

	@RequestMapping(value = "/{id}/{password}/{name}/{department}/{major}/{sex}.json", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject register(@PathVariable int id,
			@PathVariable String password, @PathVariable String name,
			@PathVariable String department, @PathVariable String major,
			@PathVariable String sex) {
		JSONObject registerJson = new JSONObject();
		
		// 判断该学号是否注册过
		boolean isRegistered = false;
		if (isStudent(id)) {
			if (studentService.getById(id) != null) {
				isRegistered = true;
			} else {
				Student s = new Student();
				s.setStu_id(id);
				s.setStu_password(password);
				s.setStu_name(name);
				s.setMajor(majorService.getMajorByName(major));
				s.setDepartment(departmentService.getDepartmentByName(department));
				s.setStu_sex(sex);
				s.setStu_regist_time(new Date());
				
				System.out.println(studentService.save(s));
				
			}
		} else {
			if (teacherService.getById(id) != null) {
				isRegistered = true;
			} else {
				Teacher t = new Teacher();
			}
		}
		return registerJson;
	}

	/**
	 * 根据用户id长度判断是学生还是教师
	 * 
	 * @param user_id
	 *            用户id
	 * @return 是否为学生
	 */
	public boolean isStudent(int user_id) {
		String id = String.valueOf(user_id);
		// 学生学号长度为9
		if (id.length() == 9)
			return true;
		return false;
	}
}
