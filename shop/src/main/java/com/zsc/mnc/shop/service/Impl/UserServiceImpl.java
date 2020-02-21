package com.zsc.mnc.shop.service.Impl;

import com.zsc.mnc.shop.dao.UserDao;
import com.zsc.mnc.shop.model.User;
import com.zsc.mnc.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("userService")
public class UserServiceImpl implements UserService {

    //数据库操作mapper的注入
    @Autowired
    private UserDao userDao;

    @Override
    public User findUserById(Long id) {
        return userDao.findUserById(id);
    }

    public List<User> getAllUsers(){
        return userDao.getAllUsers();
    }

}
