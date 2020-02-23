package com.zsc.mnc.shop.controller;

import com.zsc.mnc.shop.model.ResponseResult;
import com.zsc.mnc.shop.model.User;
import com.zsc.mnc.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;


//    @RequestMapping(value = "/register", method = RequestMethod.POST)
//    public ResponseResult register(User user){
//        ResponseResult result=new ResponseResult();
//        user.setRegisterDate(new Date());
//        int a=userService.register(user);
//        if(a>0)
//            result.setMsg(String.valueOf(true));
//        else result.setMsg(String.valueOf(false));
//
//        return result;
//    }





















    @RequestMapping(value = "/userList", method = RequestMethod.POST)
    public ResponseResult getAllUser() {
        ResponseResult result=new ResponseResult();
        List<User> list = new ArrayList<>();
        list = userService.getAllUsers();
        if(list.size()>0){
            result.setMsg(String.valueOf(true));
            result.setData(list);
            result.setTotal((long)list.size());
        }
        else result.setMsg(String.valueOf(false));
        return result;
    }
}