package org.javatribe.pinker.controller.admin;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.common.Question;
import org.javatribe.pinker.entity.Department;
import org.javatribe.pinker.entity.Major;
import org.javatribe.pinker.entity.Student;
import org.javatribe.pinker.service.DepartmentService;
import org.javatribe.pinker.service.MajorService;
import org.javatribe.pinker.service.StudentService;
import org.javatribe.pinker.util.FormatTrans;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author kaiscript
 * 2015年8月25日 上午2:24:01
 */

@Controller
@RequestMapping("/admin/student")
public class StudentInfoController {
	
	private StudentService studentService;
	private DepartmentService departmentService;
	private MajorService majorService;
	
	@Resource(name="studentServiceImpl")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}
	
	
	@Resource(name="departmentServiceImpl")
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}
	
	@Resource(name="majorServiceImpl")
	public void setMajorService(MajorService majorService) {
		this.majorService = majorService;
	}


	@RequestMapping("/index")
	public String index(){
		return "redirect:/admin/student/list";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model ,Pager pager){
		if(pager.getTotalCount()==0){
			pager=new Pager();
			pager.setPageIndex(1);
			pager.setPageSize(2);
			pager.setHasPreviousPage(false);//第一页，故设置为无上一页
		}
		
		pager =studentService.findByPager(pager);
		pager.init();
		model.addAttribute("studentPager", pager);
		return "admin/list";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public String listByIndexPage(@RequestParam("pageIndex") int pageIndex,Model model ,Pager pager){
		if(pager.getTotalCount()==0){
			pager=new Pager();
			pager.setPageIndex(1);
			pager.setPageSize(2);
			pager.setHasPreviousPage(false);//第一页，故设置为无上一页
		}
		
		pager.setPageIndex(pageIndex);
		
		pager =studentService.findByPager(pager);
		pager.init();
		model.addAttribute("studentPager", pager);
		return "admin/list";
	}
	
	@RequestMapping(value="/editStudent/id{stuId}")
	public String editStudentInfo(@PathVariable int stuId,Model model){
		Student student =new Student();
		student =studentService.getById(stuId);
		
		
		model.addAttribute("student",student);
		model.addAttribute("department", getDepartmentList());
		model.addAttribute("major", getMajorList(student.getDepartment().getDept_id()));
		model.addAttribute("question", new Question().getQuestions());
		return "admin/editStudentInfo";
	}
	
	@RequestMapping(value="/updateStudent")
	public String updateStudent(@ModelAttribute("student")Student student ,@RequestParam("departmentId") String departmentId
			,@RequestParam("majorId") String majorId,@RequestParam("registTime") String time,Model model){
		
		Department department =departmentService.getDepartmentById(Integer.valueOf(departmentId));
		Major major =majorService.getMajorById(Integer.valueOf(majorId));
		Date date=FormatTrans.stringToDate(time);
		student.setDepartment(department);
		student.setMajor(major);
		student.setStu_regist_time(date);
		
		if(studentService.update(student)){
			student = studentService.getById(student.getStu_id());
			model.addAttribute("student", student);
			model.addAttribute("department", getDepartmentList());
			model.addAttribute("major", getMajorList(student.getDepartment().getDept_id()));
			model.addAttribute("question", new Question().getQuestions());
			return "admin/editStudentInfo";
		}
		return "error";
	}
	
	
	public List<Department> getDepartmentList(){
		List<Department> departmentsList =new ArrayList<Department>();
		departmentsList =(List<Department>)departmentService.getAllList();
		return departmentsList;
	}
	
	public List<Major> getMajorList(int departmentId){
		List<Major> majorList=new ArrayList<Major>();
		majorList=(List<Major>)majorService.getMajorsByDepartmentId(departmentId);
		return majorList;
	}
	
}
