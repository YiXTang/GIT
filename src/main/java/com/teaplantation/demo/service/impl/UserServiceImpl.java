package com.teaplantation.demo.service.impl;

import com.teaplantation.demo.dto.MyResult;
import com.teaplantation.demo.entity.User;
import com.teaplantation.demo.exception.MyException;
import com.teaplantation.demo.mapper.UserMapper;
import com.teaplantation.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    @Override
    public MyResult queryUsers() {
        List<User> userList = userMapper.queryUsers();
        if (userList != null && userList.size() > 0) {
            return MyResult.ok(userList);
        } else {
            return MyResult.notFount("查询失败");
        }
    }

    @Override
    public MyResult selectByPrimaryKey(int uid) {
        if (uid <= 0) {
            return MyResult.notFount("查询学生条件错误");
        } else {
            User user = userMapper.selectByPrimaryKey(uid);
            if (user == null) {
                return MyResult.notFount("查询学生结果为空");
            } else {
                return MyResult.ok(user);
            }
        }
    }

    @Override
    public MyResult insertUser(User user) {
        if(user == null){
            return MyResult.notFount("插入用户为空");
        }else{
            try{
                int effectedNum = userMapper.insert(user);
                if (effectedNum>0){
                    return MyResult.ok();
                }else {
                    return MyResult.notFount("添加用户失败");
                }
            }catch (Exception e){
                throw new MyException("插入学生错误："+e.getMessage());
            }
        }
    }

    @Override
    public MyResult updateUser(User user) {
        if(user == null){
            return MyResult.notFount("插入学生为空");
        }else{
            try{
                int effectedNum = userMapper.updateByPrimaryKey(user);
                if (effectedNum>0){
                    return MyResult.ok();
                }else {
                    return MyResult.notFount("修改学生失败");
                }
            }catch (Exception e){
                throw new MyException("修改学生错误："+e.getMessage());
            }
        }
    }

    @Override
    public MyResult deleteUserByUid(int uid) {
        if(uid>0){
            try{
                int effectedNum = userMapper.deleteByPrimaryKey(uid);
                if(effectedNum>0){
                    return MyResult.ok();
                }
                else{
                    return MyResult.notFount("删除学生失败");
                }

            }catch (Exception e){
                throw new MyException("删除学生错误");
            }
        }
        return MyResult.notFount("删除学生条件错误");
    }


    @Override
    public MyResult updateUserUidToNull(int uid) {
        return null;
    }
}
