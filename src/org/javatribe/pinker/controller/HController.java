package org.javatribe.pinker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author Mars
 * 2015骞�鏈�8鏃�涓嬪崍9:10:47
 */

@Controller
@RequestMapping("/h")
public class HController {
	@RequestMapping("/hello")
	public String hello() {
		return "admin/hello";
	}

	@RequestMapping("index")
	public String index() {
		return "admin/index";
	}
	
}
