package com.teaplantation.demo.service.impl;

import com.teaplantation.demo.mapper.UserMapper;
import com.teaplantation.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public String selectUserByUid(int uid) {
        return null;
    }
}
