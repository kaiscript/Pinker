package org.javatribe.pinker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
@RequestMapping("/test")
public class HController {
	@RequestMapping(value="/hello",method=RequestMethod.GET)
	public String hello() {
		return "test/hello";
	}

	@RequestMapping("/index")
	public String index() {
		return "test/index";
	}
	
}
