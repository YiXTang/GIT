package com.teaplantation.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teaplantation.demo.dto.MyResult;
import com.teaplantation.demo.entity.User;
import com.teaplantation.demo.service.UserService;
import com.teaplantation.demo.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/user/*")
public class UserController {
    @Autowired
    UserService userService;

    private User user=new User();

    @RequestMapping("/index")
    public String index(HttpSession httpSession) {
        httpSession.getAttribute("userLogin");
        return "index";
    }

    @RequestMapping("/register")
    public String register() {
        return "register";
    }
    @RequestMapping("/login")
    public String login() {
        String str="";
        String username=user.getUserName();
        if(username!=null) {
            str="index";
        }else {
            str="login";
        }
        return str;
    }


    @RequestMapping(value = "checkUserName", method= RequestMethod.POST)
    @ResponseBody
    //根据用户名称查询，检查用户名称的唯一性（用户注册）
    public void checkUserName(HttpServletRequest request, HttpServletResponse response) throws IOException{
        System.out.println("前端找到了后端");
        response.setCharacterEncoding("UTF-8");
        //返回json数据，格式为{"valid",true}	表示合法，验证通过。{"valid":false} 表示不合法，验证不通过
        String jsonResult = "";
        String userName = request.getParameter("userName");
        System.out.println("前端传来用户名"+userName);
        //去数据进行唯一性确认
        if (userName!=null) {
            //服务层service调用数据库访问层dao中的searchUserName方法。
            boolean b = userService.selectByUsername(userName).equals("");
            System.out.println(b);
            if (b) {
                //如果名称不存在
                jsonResult = "{\"valid\":true}";
            }else{
                //如果该名称存在
                jsonResult = "{\"valid\":false}";
            }
        } else {
            jsonResult = "{\"valid\":false}";
        }
        //response把jsonResult打到前台
        response.getWriter().write(jsonResult);
    }

//    @RequestMapping("/uregisiter")
//    public String register(HttpServletRequest request) {
//        String username=request.getParameter("username");
//        String password=request.getParameter("password");
//        String password2=request.getParameter("password2");
//        String realname=request.getParameter("realname");
//        String str="";
//        if(password.equals(password2)) {
//            user=userdao.findByName(username);
//            if(user==null) {
//                User user=new User();
//                user.setUsername(username);
//                user.setPassword(password);
//                user.setRealname(realname);
//                userdao.save(user);
//                str="login";
//            }else {
//                str="register";
//            }
//        }else {
//            str="register";
//        }
//        return str;
//    }
//    @RequestMapping("/ulogin")
//    public String login(HttpServletRequest request,HttpSession session) {
//        String username=request.getParameter("username");
//        String password=request.getParameter("password");
//        user=userdao.findBynameAndPassword(username, password);
//        String str="";
//        if(user!=null) {
//            session.setAttribute("userLogin", user);
//            str="index";
//        }else {
//            str="login";
//        }
//        return str;
//    }

}
