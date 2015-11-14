package org.javatribe.pinker.controller.admin;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.common.Pager.Order;
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
 * @author kaiscript 2015年8月25日 上午2:24:01
 */

@Controller
@RequestMapping("/admin/student")
public class StudentInfoController {
	
	private SessionFactory sessionFactory;
	
	private StudentService studentService;
	private DepartmentService departmentService;
	private MajorService majorService;
	
	/**
	 * 注入 sessionFactory
	 * @param sessionFactory
	 */
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Resource(name = "studentServiceImpl")
	public void setStudentService(StudentService studentService) {
		this.studentService = studentService;
	}

	@Resource(name = "departmentServiceImpl")
	public void setDepartmentService(DepartmentService departmentService) {
		this.departmentService = departmentService;
	}

	@Resource(name = "majorServiceImpl")
	public void setMajorService(MajorService majorService) {
		this.majorService = majorService;
	}

	@RequestMapping("/index")
	public String index() {
		return "redirect:/admin/student/list";
	}

	/**
	 * 访问学生列表页面
	 * 
	 * @param model
	 * @param pager
	 *            分页变量
	 * @return 跳转到学生列表页面 student/list
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public String list(Model model, Pager pager) {
		if (pager.getTotalCount() == 0) {

			pager = new Pager();
			pager.setPageIndex(1);
			pager.setPageSize(4);
			pager.setHasPreviousPage(false);// 第一页，故设置为无上一页

			Map<String, Order> orderMap = new HashMap<String, Order>();
			orderMap.put("stu_id", Order.asc);// 添加按stu_id排序
			pager.setOrder(Order.asc);
			pager.setOrderMap(orderMap);

		}

		pager = studentService.findByPager(pager);
		pager.init();
		model.addAttribute("studentPager", pager);
		return "student/list";
	}

	/**
	 * 
	 * @param pageIndex
	 *            第几页
	 * @param model
	 * @param pager
	 * @return 跳转到学生列表页面 student/list
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String listByIndexPage(@RequestParam("pageIndex") int pageIndex,
			Model model, Pager pager) {
		if (pager.getTotalCount() == 0) {
			pager = new Pager();
			pager.setPageIndex(1);
			pager.setPageSize(4);
			pager.setHasPreviousPage(false);// 第一页，故设置为无上一页

			Map<String, Order> orderMap = new HashMap<String, Order>();
			orderMap.put("stu_id", Order.asc);// 添加按stu_id排序
			pager.setOrder(Order.asc);
			pager.setOrderMap(orderMap);
		}

		pager.setPageIndex(pageIndex);

		pager = studentService.findByPager(pager);
		pager.init();
		model.addAttribute("studentPager", pager);
		return "student/list";
	}

	/**
	 * 访问修改学生信息页面
	 * 
	 * @param stuId
	 *            学生id
	 * @param model
	 * @return 跳转到 修改学生信息页面
	 */
	@RequestMapping(value = "/editStudent/id{stuId}")
	public String editStudentInfo(@PathVariable int stuId, Model model) {
		Student student = new Student();
		student = studentService.getById(stuId);

		model.addAttribute("student", student);
		model.addAttribute("department", getDepartmentList());
		model.addAttribute("major", getMajorList(student.getDepartment().getDept_id()));
		model.addAttribute("question", new Question().getQuestions());
		return "student/editStudentInfo";
	}

	/**
	 * 
	 * @param student 学生实体
	 * @param departmentId 系id
	 * @param majorId 专业id
	 * @param registTime 注册时间
	 * @param model
	 * @return 返回修改学生信息页面或错误页面
	 */
	@RequestMapping(value = "/updateStudent")
	public String updateStudent(@ModelAttribute("student") Student student,
			String orginal_stu_id,String departmentId,String majorId,
			String registTime, String stu_head_img,
			Model model) {
		Student studentFormDB = studentService.getById(student.getStu_id()); 
		//在数据库查找是否 有被修改的学生实体的存在
		Department department = departmentService.getDepartmentById(Integer.valueOf(departmentId));
		Major major = majorService.getMajorById(Integer.valueOf(majorId));
		Date date = FormatTrans.stringToDate(registTime);
		student.setDepartment(department);
		student.setMajor(major);
		student.setStu_regist_time(date);
		student.setStu_head_img(stu_head_img);
		
		
		//如果学生为空，说明要修改的 属性包含了学号，数据库找不到
		if (studentFormDB == null) {
			//更新新的学生实体
			studentService.save(student);
			Student deletedStudent = studentService.getById(Integer.valueOf(orginal_stu_id));
			//删除旧的学号的学生数据
			studentService.delete(deletedStudent);

		} 
		else{
			sessionFactory.getCurrentSession().evict(studentFormDB);
			studentService.update(student);
			/*a different object with the same identifier value 
			was already associated with the session: [org.javatribe.pinker.entity.Student#131544111]
			由于从数据库取出来的PO和从该页面传过来的学生PO拥有同个持久化标识identifier value。
			update的时候会抛以上错误。使用session.evict(Object object),使其成为游离态，便可以update想要的PO的了*/
		}
		model.addAttribute("student", student);
		model.addAttribute("major", getMajorList(student.getDepartment().getDept_id()));
		model.addAttribute("department", getDepartmentList());
		model.addAttribute("question", new Question().getQuestions());
		return "student/editStudentInfo";
	}

	/**
	 * 取得系集合
	 * 
	 * @return 系集合
	 */
	public List<Department> getDepartmentList() {
		List<Department> departmentsList = new ArrayList<Department>();
		departmentsList = (List<Department>) departmentService.getAllList();
		return departmentsList;
	}

	/**
	 * 根据系id获取对应系的专业集合
	 * 
	 * @param departmentId 系id
	 * @return 专业集合
	 */
	public List<Major> getMajorList(int departmentId) {
		List<Major> majorList = new ArrayList<Major>();
		majorList = (List<Major>) majorService
				.getMajorsByDepartmentId(departmentId);
		return majorList;
	}

}
