package com.zsc.mnc.shop.service.Impl;

import com.zsc.mnc.shop.dao.UserMapper;
import com.zsc.mnc.shop.model.User;
import com.zsc.mnc.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("userService")
public class UserServiceImpl implements UserService {

    //数据库操作mapper的注入
    @Autowired
    private UserMapper userMapper;

    @Override
    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }
}
