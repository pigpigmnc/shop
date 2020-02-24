package com.zsc.mnc.shop.service;

import com.zsc.mnc.shop.model.User;

import java.util.List;

public interface UserService {
    //这里是service的方法定义
    User findUserById(Long id);
    List<User> getAllUsers();
    int addUser(User user);
    User queryUserByName(String username);
    User queryUser(String username,String password);
    int modifyUser(User user);
    int deleteUser(long id);
    List<User> queryUserByStatus(int status);

}
