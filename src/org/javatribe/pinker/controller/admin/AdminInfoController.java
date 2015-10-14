package org.javatribe.pinker.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.javatribe.pinker.common.LoginConstant;
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
			@RequestParam String password, HttpServletRequest request, HttpServletResponse response) {
		Map<String, String> map = new HashMap<String, String>();
		Admin admin = adminService.getAdminByUsername(account);
		String getAuto = request.getParameter("autoLogin");// 自动登录 checkbox 值
		request.getSession().setAttribute("user", admin);
		
		System.out.println("controller:autoLogin"+request.getSession().getAttribute("autoLogin"));
		
		if (admin != null && password.equals(admin.getAdmin_password())) {
			Cookie cookie = setCookieSetting(request,admin,getAuto);
			request.getSession().setAttribute("autoLogin", "1"+getAuto);
			LoginConstant.ifFirstLogin = false;
			map.put("success", "true"); //json :key为success,value为true
			response.addCookie(cookie); //返回cookie到客户端
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
	
	/**
	 * 设置是否自动登录的cookie
	 * @param request
	 * @param admin
	 * @param autoLogin
	 */
	public Cookie setCookieSetting(HttpServletRequest request,Admin admin,String autoLogin){
		request.getSession().setAttribute("user", admin); // 管理员验证成功则设置session
		Cookie cookie;
		if(autoLogin.equals("1")){
			cookie = new Cookie("autoLogin",request.getSession().getId()+"AL1");
			cookie.setMaxAge(24*3600); //保存24小时
//			System.out.println("1:cookie:"+cookie.getValue());
//			System.out.println("name:"+cookie.getName());
		}
		else{
			cookie = new Cookie("autoLogin",request.getSession().getId()+"AL0");
//			System.out.println("0:cookie:"+cookie.getValue());
//			System.out.println("name:"+cookie.getName());
		}
		
		return cookie;
	}
	
}
