package com.dcc.controller.login;

import com.dcc.po.AdminUser;
import com.dcc.service.AdminUserService;
import com.dcc.util.Cores;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


@Controller
public class LoginController {

    @Autowired
    private AdminUserService adminUserService;

    @RequestMapping("login.html")
    public String login() {
        return "login/login";
    }


    @RequestMapping("register.html")
    public String register() {
        return "login/register";
    }

    @RequestMapping("/dologin")
    @ResponseBody
    public String LoginAdminUser(String users,String password) throws Exception {
        String logins = adminUserService.loginAuser(users, password);
        return logins;
    }

    @RequestMapping("/getCode.do")
    public void getCode(HttpServletResponse response, HttpServletRequest request) {
        response.setContentType("image/jpg");// 设置相应类型,告诉浏览器输出的内容为图片
        response.setHeader("Pragma", "No-cache");// 设置响应头信息，告诉浏览器不要缓存此内容
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expire", 0);
        Cores randomValidateCode = new Cores();
        try {
            randomValidateCode.getRandcode(request, response);// 输出图片方法
            HttpSession session = request.getSession();
            String keysy = session.getAttribute("sess_captcha").toString();
            System.out.println(keysy);
        } catch (Exception e) {
            System.out.println(e.toString());
        }
    }

    @RequestMapping(value="/registerUser.do")
    @ResponseBody
    public String registerUser(AdminUser adminUser, String result) throws Exception {
        String res = adminUserService.addUserAdmin(adminUser,result);
        return res;
    }
}
