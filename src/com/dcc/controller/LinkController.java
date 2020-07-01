package com.dcc.controller;

import com.dcc.service.LinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dcc.po.Link;

@Controller
public class LinkController {
	@Autowired
	private LinkService linkService;
	
	@RequestMapping("/add_link.do")
	public String add_link(Link link) throws Exception {
		linkService.addLink(link);
		return "redirect:/html/link.html";
	}
		
	@RequestMapping("/count_link.do")
	@ResponseBody
	public String count_link(Link link) throws Exception {
		String nums = linkService.countLink();
		return nums;
	}
	
	@RequestMapping(value="/findPage_link.do",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String findPage_link(Integer Fenye) throws Exception {
		String fy = linkService.fenyeLink(Fenye);
		return fy;
	}
	
	@RequestMapping("/del_link.do")
	@ResponseBody
	public String del_link(Integer result) throws Exception {
		String dels = linkService.deleteLink(result);
		return dels;
	}
	
	@RequestMapping(value="/select_link.do",produces="text/html;charset=UTF-8;")
	@ResponseBody
	public String select_link(Integer id) throws Exception {
		String lin = linkService.findIdLi(id);
		return lin;
	}
	
	@RequestMapping("/update_link.do")
	public String update_link(Link link) throws Exception {
		linkService.updateLin(link);
		return "redirect:/html/link.html";
	}

}
