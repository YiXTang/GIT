package com.teaplantation.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.teaplantation.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
@RestController
public class UserController {
    @Autowired
    UserService userService;
    @GetMapping(value = "/user/login")
    public Map<String,Object> login(int uid){
        ObjectMapper mapper = new ObjectMapper();
        return null;
    }
    @GetMapping(value = "/log")
    public void login(){
        System.out.println("hello");
    }
}
