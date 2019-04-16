package com.teaplantation.demo.service;

import com.teaplantation.demo.dto.MyResult;
import com.teaplantation.demo.entity.User;

public interface UserService {
    MyResult queryUsers();
    MyResult selectByUsername(String username);
    MyResult selectByPrimaryKey(int uid);
    MyResult insertUser(User user);
    MyResult updateUser(User user);
    MyResult deleteUserByUid(int uid);
    MyResult updateUserUidToNull(int uid);
}
