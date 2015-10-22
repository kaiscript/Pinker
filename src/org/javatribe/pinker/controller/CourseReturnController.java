package org.javatribe.pinker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author kaiscript
 * 2015年10月21日 下午8:05:33
 */
@Controller
@RequestMapping("/course")
public class CourseReturnController {
	
	@RequestMapping(value="/getElectiveCourse/{elective_category}",method=RequestMethod.POST)
	public void getElectiveCourse(@PathVariable int elective_category){
		
	}
}
