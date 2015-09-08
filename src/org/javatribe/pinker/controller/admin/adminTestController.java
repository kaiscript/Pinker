package org.javatribe.pinker.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class adminTestController {
	
	@RequestMapping("/test")
	public String test(){
		return "test/admintest";
	}
}
