package org.javatribe.pinker.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatribe.pinker.entity.Admin;
import org.javatribe.pinker.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author kaiscript 2015年9月28日 下午4:29:22
 */
@Controller
@RequestMapping("/validate")
public class AdminInfoController {

	private AdminService adminService;

	@Resource(name = "adminServiceImpl")
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST, params = "json")
	@ResponseBody
	public Map<String, String> validate(@RequestParam String account,
			@RequestParam String password, HttpServletRequest request, HttpServletResponse response) throws Exception{
		Map<String, String> map = new HashMap<String, String>();
		Admin admin = adminService.getAdminByUsername(account);
		String getAuto = request.getParameter("autoLogin");// 记住我 checkbox 值，0没记住，1记住我
		request.getSession().setAttribute("user", admin);
//		Cookie cookie = new Cookie("autoLogin",request.getSession().getId()+"AL"+getAuto);
//		cookie.setPath("/");
//		cookie.setMaxAge(24*3600);
//		response.addCookie(cookie);
		
		if (admin != null && password.equals(admin.getAdmin_password())) {
			request.getSession().setAttribute("autoLogin", getAuto);
			
			map.put("success", "true"); //json :key为success,value为true
			return map;
		}
		map.put("success", "false");
		return map;
	}
	
	@RequestMapping("/login")
	public String validateAdminInfo(HttpServletRequest request) {
		return "redirect:/admin/student/list";
	}

	@RequestMapping("/exit")
	public String exit(HttpServletRequest request) {
		request.getSession().setAttribute("user", null);
		return "login";
	}
	
}
