package com.dcc.controller;

import com.dcc.service.PlainUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcc.po.PlainUser;
import com.dcc.po.PlainUserCostom;

@Controller
public class PlainUserController {
	@Autowired
	private PlainUserService plainUserService;
	
	@RequestMapping(value="/addPlainUser.do")
	public String addPlainUser(PlainUserCostom costom) throws Exception {
		plainUserService.addPuser(costom);
		return "redirect:/html/users.html";
	}
	
	@RequestMapping("countpuser.do")
	@ResponseBody
	public String countpuser() throws Exception{
		String counts = plainUserService.countPuser();
		return counts;
	}
	
	@RequestMapping(value="pagePuser.do",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String pagePuser(Integer result) throws Exception{
		String Feny = plainUserService.FenyePuser(result);
		return Feny;
	}
	
	@RequestMapping(value="delPUser.do")
	@ResponseBody
	public String delPUser(Integer result) throws Exception{
		String dels = plainUserService.delsUs(result);
		return dels;
	}
	
	@RequestMapping(value="updateuser_puser.do")
	public String updateuser_puser(PlainUser plainUser) throws Exception{
		plainUserService.updatePlainUser(plainUser);
		return "redirect:/html/users.html";
	}
	
	@RequestMapping(value="findidu_puser.do",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String findidu_puser(Integer result) throws Exception{
		String users = plainUserService.findPianU(result);
		return users;
	}
	
	@RequestMapping(value="searchPlainUser.do",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String searchPlainUser(String result) throws Exception{
		String users = plainUserService.searchPlUser(result);
		return users;
	}
	
	@RequestMapping(value="update_DeptU_ser.do")
	public String update_DeptU_ser(PlainUserCostom costom) throws Exception{
		plainUserService.updat_dep_id(costom);
		return "redirect:/html/dept.html";
	}
	

}
