package com.dcc.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.dcc.dao.MapperAdminUser;
import com.dcc.po.AdminUser;
import com.dcc.util.Time;

@Service
public class AdminUserService {

	@Autowired//注入
	private MapperAdminUser mapperAdminUser;

	public String loginAuser(String users, String password) throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		AdminUser adminUser = mapperAdminUser.loginAdminUser(users, password);
		if(adminUser!=null) {
			map.put("state", "1");
			String jsonUser = JSONObject.toJSONString(adminUser);
			System.out.println(jsonUser);
			Cookie cookies = new Cookie("admin_user", java.net.URLEncoder.encode(jsonUser, "UTF-8"));//序列化
			//利用SpringMVC提供的在非controller层获取response对象的方法
			HttpServletResponse response =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
			request.getSession().setAttribute("admin_user", jsonUser);
			response.addCookie(cookies);
		}else {
			map.put("state", "-1");
		}
		return JSON.toJSONString(map);
	}
	

	@Transactional
	public String updateUserMessage(AdminUser adminUser){
		Map<String, String> map = new HashMap<String, String>();
		if(adminUser == null) {
			map.put("state", "1");
		}else{
			try {
				mapperAdminUser.updateUSerMessages(adminUser);
				String jsonUser = JSONObject.toJSONString(mapperAdminUser.selectFindUser(adminUser.getId(),""));
				Cookie cookies = new Cookie("admin_user", java.net.URLEncoder.encode(jsonUser, "UTF-8"));//序列化
				//利用SpringMVC提供的在非controller层获取response对象的方法
				HttpServletResponse response =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
				response.addCookie(cookies);
				map.put("state", "1");
			} catch (Exception e) {
				e.printStackTrace();
				map.put("state", "-1");
			}
		}
		
		return JSON.toJSONString(map);
	}


	@Transactional
	public String updatePassword(AdminUser adminUser,String pass) throws Exception {
		AdminUser ad = mapperAdminUser.selectFindUser(adminUser.getId(),"");//根据查原密码
		Map<String, String> map = new HashMap<String, String>();
		if(ad.getPassword().equals(pass)) {//如果查询出的原密码与用户输入的原密码相同，则更新
			mapperAdminUser.updatePassword(adminUser);
			map.put("state", "1");
		}else if(!ad.getPassword().equals(pass)){
			map.put("state", "-2");
		}else {
			map.put("state", "-1");
		}
		return JSON.toJSONString(map);
	}


	public void signout() throws Exception {
		HttpServletResponse response =((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getResponse();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session =  request.getSession();
		session.removeAttribute("admin_user");
		session.invalidate();
		 Cookie[] cookies = request.getCookies();
		 //循环删除cookie
			for(Cookie ck : cookies) {
				ck.setMaxAge(0);
				response.addCookie(ck);
			}
	}


	public String selectAdminU() throws Exception {
		List<AdminUser> lists = mapperAdminUser.selectAdminUser();
		return JSON.toJSONString(lists);
	}


	public String addUserAdmin(AdminUser adminUser,String result) throws Exception {
		Map<String, String> map = Maps.newHashMap();
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		HttpSession session = request.getSession();
		String keysy = session.getAttribute("sess_captcha").toString();
		if(!result.equalsIgnoreCase(keysy)) {  //验证码忽略大小写
			map.put("state", "0");//验证码错误
		}else {
			if(mapperAdminUser.selectFindUser(null,adminUser.getUsers()) !=null) {
				map.put("state","2"); //用户已注册！
				return JSON.toJSONString(map);
			}
			adminUser.setDates(Time.getTime2());

			int num = mapperAdminUser.addAdminUser(adminUser);
			if(num>0) {
				map.put("state", "1");//注册成功
			}else {
				map.put("state", "-1");
			}
		}
		return JSON.toJSONString(map);
	}

}
