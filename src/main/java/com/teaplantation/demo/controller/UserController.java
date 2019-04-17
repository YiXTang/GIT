package com.teaplantation.demo.controller;

import com.teaplantation.demo.dto.MyResult;
import com.teaplantation.demo.entity.User;
import com.teaplantation.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
@RequestMapping("/user/*")
public class UserController {
    @Autowired
    UserService userService;

    private User user=new User();

    @RequestMapping("/register")
    public String index(HttpSession httpSession) {
        httpSession.getAttribute("register");
        return "register";
    }


    @RequestMapping(value = "checkUserName", method= RequestMethod.POST)
    @ResponseBody
    //根据用户名称查询，检查用户名称的唯一性（用户注册）
    public MyResult checkUserName(HttpServletRequest request, HttpServletResponse response) throws IOException{
        System.out.println("前端找到了后端");
        response.setCharacterEncoding("UTF-8");
        MyResult result = null;
        String userName = request.getParameter("userName");
        System.out.println("前端传来用户名"+userName);
        //去数据进行唯一性确认
        if (userName!=null) {
            //服务层service调用数据库访问层dao中的selectByUsername方法。
            boolean b = (userService.selectByUsername(userName)).isOK();
            System.out.println(b);
            result = userService.selectByUsername(userName);
        } else {
            result = userService.selectByUsername(userName);
        }
        return result;
    }

    @RequestMapping("/login")
    public String login(HttpServletRequest request, HttpServletResponse response) throws IOException{
        System.out.println("登录页找到了后端");
        response.setCharacterEncoding("UTF-8");
        String str="";
        MyResult result;
        String username=request.getParameter("userName");
        String password=request.getParameter("password");
        result=userService.selectByUsername(username);
        user=(User) result.getData();
        if(user.getPassword().equals(password)&&user.getStatus().equals(1)){
            str="/index";
        }
        else {
            str="/login";
        }
        return str;
    }

    @RequestMapping(value = "register", method= RequestMethod.POST)
    @ResponseBody
    //根据用户名称查询，检查用户名称的唯一性（用户注册）
    public String register(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        System.out.println("按钮-前端找到了后端");
        response.setCharacterEncoding("UTF-8");

        String str="";
        MyResult result = null;
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat( " yyyy-MM-dd HH:mm:ss " );
        String nowTime = sdf.format(date);

        user.setUserName(request.getParameter("userName"));
        user.setNickName(user.getUserName());
        user.setMobile("");
        user.setEmail(request.getParameter("userEmail"));
        user.setPassword(request.getParameter("password"));
        //System.out.println( user.getUserName()+" "+user.getEmail()+" "+user.getPassword());
        user.setCreateTime(sdf.parse(nowTime));
        user.setUpdateTime(sdf.parse(nowTime));
        user.setStatus((byte)1);
        result=userService.insertUser(user);
        if(result.isOK()){
            System.out.println(userService.selectByUsername(request.getParameter("userName")).getMsg());
            result=userService.selectByUsername(request.getParameter("userName"));
            str="/login";
        }else {
            result=userService.selectByUsername(request.getParameter("userName"));
            str="/register";
        }
        return str;
    }
}
