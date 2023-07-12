package com.xmut303.controller;

import com.xmut303.pojo.Admin;
import com.xmut303.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author JokkiePan
 * @vision 1.0
 * @createDate 2023/4/28 14:15
 */

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/login")
    public String login(Admin admin, HttpServletRequest request){
        Admin dbAdmin = adminService.findAdmin(admin);
        if (dbAdmin == null){
            request.setAttribute("msg","用户名或密码错误，登陆失败");
            return "login";
        }else {
            request.getSession().setAttribute("ADMIN_SESSION",admin);
            return "redirect:/admin/main.jsp";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }
}
