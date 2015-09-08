package org.javatribe.pinker.controller.admin;


import java.util.List;

import javax.annotation.Resource;

import org.javatribe.pinker.entity.Major;
import org.javatribe.pinker.service.MajorService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kaiscript
 * 2015年8月25日 上午2:24:01
 */

@Controller
@RequestMapping("/major")
public class MajorInfoController {
	
	private MajorService majorService;
	
	@Resource(name="majorServiceImpl")
	public void setMajorService(MajorService majorService) {
		this.majorService = majorService;
	}

	/**
	 * 
	 * @param departmentId
	 * @return 对应系的专业集合
	 */
	@RequestMapping(value="/getMajorSelect/{departmentId}",method =RequestMethod.POST,params="json")
	@ResponseBody
	public List<Major> getMajorJson(@PathVariable int departmentId){
		return (List<Major>)majorService.getMajorsByDepartmentId(departmentId);
	}
	
	
}
