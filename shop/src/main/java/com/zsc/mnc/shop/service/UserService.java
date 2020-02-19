package com.zsc.mnc.shop.service;

import com.zsc.mnc.shop.model.User;

public interface UserService {
    //这里是service的方法定义
    User findUserById(Long id);
}
