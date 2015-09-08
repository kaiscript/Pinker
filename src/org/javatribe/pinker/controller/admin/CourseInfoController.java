package org.javatribe.pinker.controller.admin;

import javax.annotation.Resource;

import org.javatribe.pinker.common.Pager;
import org.javatribe.pinker.service.CourseService;
import org.javatribe.pinker.service.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author Mars
 *
 *2015年8月29日 上午11:51:31
 */

@Controller
@RequestMapping("/admin/course")
public class CourseInfoController {
	
	private CourseService courseSerivce;
	
	@Resource(name="courseServiceImpl")
	public void setCourseSerivce(CourseService courseSerivce) {
		this.courseSerivce = courseSerivce;
	}

	@RequestMapping(value="/list",method=RequestMethod.GET)
	public String list(Model model, Pager pager){
		if(pager.getTotalCount()==0){
			pager = new Pager();
			pager.setPageIndex(1);
			pager.setPageSize(1);
			pager.setHasPreviousPage(false);
		}
		
		pager = courseSerivce.findByPager(pager);
		pager.init();
		model.addAttribute("coursePager", pager);
		return "admin/courseList";
	}
	
	@RequestMapping(value="/list",method=RequestMethod.POST)
	public String listByIndexPage(@RequestParam("pageIndex") int pageIndex, Model model, Pager pager){
		if(pager.getTotalCount()==0){
			pager = new Pager();
			pager.setPageIndex(1);
			pager.setPageSize(1);
			pager.setHasPreviousPage(false);
		}
		
		pager.setPageIndex(pageIndex);
		pager = courseSerivce.findByPager(pager);
		pager.init();
		model.addAttribute("coursePager", pager);
		return "admin/courseList";
	}
	
	
	
}
