package org.javatribe.pinker.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.javatribe.pinker.entity.Admin;
import org.javatribe.pinker.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/validate")
// 如果是/admin ,是否会被再次拦截呢，完成后试验下
public class AdminInfoController {

	private AdminService adminService;

	@Resource(name = "adminServiceImpl")
	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	@RequestMapping(value = "/check", method = RequestMethod.POST, params = "json")
	@ResponseBody
	public Map<String, String> validate(@RequestParam String account,
			@RequestParam String password, HttpServletRequest request) {
		Map<String, String> map = new HashMap<String, String>();
		Admin admin = adminService.getAdminByUsername(account);
			if (admin != null && password.equals(admin.getAdmin_password())) {
				request.getSession().setAttribute("user", account); // 管理员验证成功则设置session
				map.put("success", "true");
				return map;
			}
		map.put("success", "false");
		return map;
	}

	@RequestMapping("/login")
	public String validateAdminInfo() {

		return "redirect:/admin/student/list";
	}

	@RequestMapping("/exit")
	public String exit(HttpServletRequest request) {
		request.getSession().setAttribute("user", null);
		return "login";
	}
}
