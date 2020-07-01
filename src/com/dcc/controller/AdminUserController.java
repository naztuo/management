package com.dcc.controller;

import com.dcc.service.AdminUserService;
import com.dcc.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.dcc.po.AdminUser;


@Controller
public class AdminUserController {
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private LogService logService;

	
	@RequestMapping("/update_AdminUser.do")
	@ResponseBody
	public String update_AdminUser(AdminUser adminUser) throws Exception {
		String updates = adminUserService.updateUserMessage(adminUser);
		return updates;
	}
	
	
	@RequestMapping("/updatepass.do")
	@ResponseBody
	public String updatepass(AdminUser adminUser,String pass) throws Exception {
		String updates = adminUserService.updatePassword(adminUser, pass);
		return updates;
	}
	
	@RequestMapping("/signout.do")
	public String signout() throws Exception {
		adminUserService.signout();
		return "redirect:login.html";
	}

	//登录日志
	@RequestMapping(value="/selectIp.do",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String selectIp() throws Exception {
		String logs = logService.getLog();
		return logs;
	}
	
	@RequestMapping(value="/selectAd_AdminUser.do",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String selectAd_AdminUser() throws Exception {
		String users = adminUserService.selectAdminU();
		return users;
	}
	


}
