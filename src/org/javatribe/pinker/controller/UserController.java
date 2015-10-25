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
import org.springframework.web.bind.annotation.RequestParam;
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

	/**
	 * 注册用户
	 * @param id
	 * @param password
	 * @param name
	 * @param department
	 * @param major
	 * @param sex
	 * @param pw_question
	 * @param pw_answer
	 * @return
	 */
	@RequestMapping(value = "/register/{id}/{password}/{name}/{department}/{major}/{sex}/{pw_question}/{pw_answer}.json", method = RequestMethod.GET)
	@ResponseBody
	public JSONObject register(@PathVariable int id,
			@PathVariable String password, @PathVariable String name,
			@PathVariable String department, @PathVariable String major,
			@PathVariable String sex, @PathVariable String pw_question, @PathVariable String pw_answer) {
		JSONObject registerJson = new JSONObject();

		// 判断该学号是否注册过
		boolean isScuccess = true;

		if (isStudent(id)) {
			if (studentService.getById(id) != null) {
				isScuccess = false;
			} else {
				Student s = new Student();
				s.setStu_id(id);
				s.setStu_password(password);
				s.setStu_name(name);
				s.setMajor(majorService.getMajorByName(major));
				s.setDepartment(departmentService
						.getDepartmentByName(department));
				s.setStu_sex(sex);
				s.setStu_regist_time(new Date());
				s.setStu_pw_answer(pw_answer);
				s.setStu_pw_question(pw_question);

				studentService.save(s);

			}
		} else {
			if (teacherService.getById(id) != null) {
				isScuccess = false;
			} else {
				Teacher t = new Teacher();

				t.setTch_id(id);
				t.setTch_password(password);
				t.setTch_name(name);
				t.setDepartment(departmentService
						.getDepartmentByName(department));
				t.setTch_sex(sex);
				t.setTch_regist_time(new Date());
				t.setTch_pw_answer(pw_answer);
				t.setTch_pw_question(pw_question);

				teacherService.save(t);
			}
		}

		registerJson.put("isScuccess", isScuccess);
		return registerJson;
	}

	/**
	 * 通过用户旧密码修改密码
	 * @param id
	 * @param old_password
	 * @param new_password
	 * @return 成功与否
	 */
	@RequestMapping(value = "/modifyPw/{id}/{old_password}/{new_password}.json")
	@ResponseBody
	public JSONObject modifyPwByOld(@PathVariable int id,
			@PathVariable String old_password, @PathVariable String new_password) {
		JSONObject changePwJson = new JSONObject();
		boolean isSuccess = false;
		if (isStudent(id)) {
			Student s = studentService.getById(id);
			if (s.getStu_password().equals(old_password)){
				s.setStu_password(new_password);
				studentService.update(s);
				isSuccess = true;
			}

		} else {
			Teacher t = teacherService.getById(id);
			if(t.getTch_password().equals(old_password)){
				t.setTch_password(new_password);
				teacherService.update(t);
				isSuccess = true;
			}
		}
		changePwJson.put("isSuccess", isSuccess);
		return changePwJson;
	}
	
	/**
	 * 通过密保找回修改密码
	 * 密保问题中不要有“？”，否则编码会有问题
	 * @param id
	 * @param question
	 * @param answer
	 * @param new_password
	 * @return
	 */
	@RequestMapping(value="/modifyPwByQA/{id}/{answer}/{new_password}.json")
	@ResponseBody
	public JSONObject modifyPwByQA(@PathVariable int id, @PathVariable String answer, @PathVariable String new_password){
		JSONObject changeJson = new JSONObject();
		boolean isSuccess = false;
		if(isStudent(id)){
			Student s = studentService.getById(id);
			if( s.getStu_pw_answer().equals(answer)){
				s.setStu_password(new_password);
				studentService.update(s);
				isSuccess = true;
			}
		}else{
			Teacher t = teacherService.getById(id);
			if( t.getTch_pw_answer().equals(answer)){
				t.setTch_password(new_password);
				teacherService.update(t);
				isSuccess = true;
			}
		}
		changeJson.put("isSuccess", isSuccess);
		return changeJson;
	}
	
	/**
	 * 获取密保提示问题
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/modifyPwByQA/{id}.json")
	@ResponseBody
	public JSONObject getQuestionById(@PathVariable int id){
		JSONObject json = new JSONObject();
		String question = null;
		if(isStudent(id)){
			Student s = studentService.getById(id);
			question = s.getStu_pw_question();
		}else{
			Teacher t = teacherService.getById(id);
			question = t.getTch_pw_question();
		}
		json.put("question", question);
		return json;
	}
	
	/**
	 * 登陆检验
	 * @param id
	 * @param password
	 * @return
	 */
	@RequestMapping("/login/{id}/{password}.json")
	@ResponseBody
	public JSONObject login(@PathVariable int id, @PathVariable String password){
		JSONObject loginJson = new JSONObject();
		boolean isSuccess = false;
		if(isStudent(id)){
			Student s = studentService.getById(id);
			if(s.getStu_password().equals(password))
				isSuccess = true;
		}else{
			Teacher t = teacherService.getById(id); 
			if(t.getTch_password().equals(password))
				isSuccess = true;
		}
		loginJson.put("isSuccess", isSuccess);
		return loginJson;
	}
	
	/**
	 * 检测id是否注册过
	 * @param id
	 * @return
	 */
	@RequestMapping(value="/checkId/{id}.json")
	@ResponseBody
	public JSONObject checkIdIsRegistered(@PathVariable int id){
		JSONObject checkIdJson = new JSONObject();
		boolean isRegistered = false;
		if(isStudent(id)){
			Student s = studentService.getById(id);
			if(s != null)
				isRegistered = true;
		}else{
			Teacher t = teacherService.getById(id);
			if( t!= null)
				isRegistered = true;
		}
		checkIdJson.put("isRegistered", isRegistered);
		return checkIdJson;
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
