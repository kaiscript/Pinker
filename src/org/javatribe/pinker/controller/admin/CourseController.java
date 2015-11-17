package org.javatribe.pinker.controller.admin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.hibernate.SessionFactory;
import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.common.Pager.Order;
import org.javatribe.pinker.entity.Comment;
import org.javatribe.pinker.entity.Course;
import org.javatribe.pinker.entity.Major;
import org.javatribe.pinker.service.CommentService;
import org.javatribe.pinker.service.CourseService;
import org.javatribe.pinker.service.MajorService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yeungkacent
 *
 * 2015年11月13日 下午9:49:46
 */
@Controller
@RequestMapping("/admin/courseInfo")
public class CourseController {

	private SessionFactory sessionFactory;
	private CourseService courseService;
	private CommentService commentService;
	private MajorService majorService;

	/**
	 * 注入 sessionFactory
	 * @param sessionFactory
	 */
	@Resource(name = "sessionFactory")
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public CommentService getCommentService() {
		return commentService;
	}

	@Resource(name = "commentServiceImpl")
	public void setCommentService(CommentService commentService) {
		this.commentService = commentService;
	}

	public CourseService getCourseService() {
		return courseService;
	}

	@Resource(name="courseServiceImpl")
	public void setCourseService(CourseService courseService) {
		this.courseService = courseService;
	}
	
	@Resource(name = "majorServiceImpl")
	public void setMajorService(MajorService majorService) {
		this.majorService = majorService;
	}
	
	/**
	 * 访问学生列表页面
	 * 
	 * @param model
	 * @param pager
	 *            分页变量
	 * @return 跳转到课程列表页面 course1/list
	 */
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String list(Model model, Pager pager)
	{
		if (pager.getTotalCount() == 0) {

			pager = new Pager();
			pager.setPageIndex(1);
			pager.setPageSize(5);
			pager.setHasPreviousPage(false);// 第一页，故设置为无上一页

			Map<String, Order> orderMap = new HashMap<String, Order>();
			orderMap.put("crs_id", Order.asc);// 添加按crs_id排序
			pager.setOrder(Order.asc);
			pager.setOrderMap(orderMap);

		}
		
		pager = courseService.findByPager(pager);
		pager.init();
		model.addAttribute("coursePager", pager);
		
		return "course/list";
	}
	
	
	/**
	 * 
	 * @param pageIndex
	 *            第几页
	 * @param model
	 * @param pager
	 * @return 跳转到课程列表页面 student/list
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public String listByIndexPage(@RequestParam("pageIndex") int pageIndex,
			Model model, Pager pager) {
		if (pager.getTotalCount() == 0) {
			pager = new Pager();
			pager.setPageIndex(1);
			pager.setPageSize(2);
			pager.setHasPreviousPage(false);// 第一页，故设置为无上一页

			Map<String, Order> orderMap = new HashMap<String, Order>();
			orderMap.put("crs_id", Order.asc);// 添加按stu_id排序
			pager.setOrder(Order.asc);
			pager.setOrderMap(orderMap);
		}

		pager.setPageIndex(pageIndex);

		pager = courseService.findByPager(pager);
		pager.init();
		model.addAttribute("coursePager", pager);
		return "course/list";
	}
	
	/**
	 * 访问修改课程信息页面
	 * 
	 * @param crsId
	 *            课程id
	 * @param model
	 * @return 跳转到 修改课程信息页面
	 */
	@RequestMapping("/editCourseInfo/id{crsId}")
	private String editCourseInfo(@PathVariable int crsId, Model model)
	{
		Course course = new Course();
		course = courseService.getById(crsId);
		
		model.addAttribute("course", course);
		//添加评论属性
		model.addAttribute("comment", getCommentList(crsId));
		
		return "course/editCourseInfo";
	}
	
	public List<Comment> getCommentList(int crsId)
	{
		List<Comment> commentList = new ArrayList<Comment>();
		commentList = (List<Comment>)commentService.getCommentsByCourseId(crsId);
		return commentList;
	}
	
	
	/**
	 * 
	 * @param course 课程实体
	 * @param catg_name 门类名称
	 * @param maj_name 专业名称
	 * @param model
	 * @return 返回修改课程信息页面或错误页面
	 */
	@RequestMapping("/updateCourse")
	public String updateCourse(@ModelAttribute("course") Course course, 
			String orginal_crs_id, String crs_name, String crs_desc, 
			String crs_teacher_name, String crs_avg_star, String crs_labels, 
			String catg_name, String maj_name, Model model)
	{
		Course courseFromDB = courseService.getById(course.getCrs_id());
		List<Comment> comments = commentService.getCommentsByCourseId(Integer.valueOf(orginal_crs_id));
		/*Set<Comment> set=new HashSet<Comment>();         
	    set.addAll(comments);*/
		//把对应的评论里面的课程号也一同修改。
		for(int i = 0; i<comments.size(); i++)
		{
			comments.get(i).setCourse(course);
			commentService.update(comments.get(i));
		}
		Major major = majorService.getMajorByName(maj_name);
		
		course.setMajor(major);
		//course.setComments(set);
		course.setCrs_name(crs_name);
		course.setCrs_desc(crs_desc);
		course.setCrs_teacher_name(crs_teacher_name);
		course.setCrs_avg_star(Integer.valueOf(crs_avg_star));
		
		if(courseFromDB == null)
		{
			courseService.save(course);
			Course deleteCourse = courseService.getById(Integer.valueOf(orginal_crs_id));
			
			courseService.delete(deleteCourse);
		}
		else
		{
			sessionFactory.getCurrentSession().evict(courseFromDB);
			courseService.update(course);
		}
		
		model.addAttribute("course", course);
		model.addAttribute("comment", getCommentList(course.getCrs_id()));
		
		return "course/editCourseInfo";
	}
	
}
