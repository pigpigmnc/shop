package com.zsc.mnc.shop.controller;

import com.zsc.mnc.shop.model.ResponseResult;
import com.zsc.mnc.shop.model.User;
import com.zsc.mnc.shop.service.UserService;
import org.apache.ibatis.annotations.Param;
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

    @RequestMapping(value = "/modifyUser", method = RequestMethod.POST)
    public ResponseResult modifyUser(User user){
        ResponseResult result=new ResponseResult();
        if(userService.queryUserByName(user.getUsername()) ==null){
        if(userService.modifyUser(user)>0)
            result.setMsg("修改成功");
        }
        else result.setMsg("修改失败，用户名已存在");
        return result;
    }


    @RequestMapping(value = "/deleteUser", method = RequestMethod.POST)
    public ResponseResult deleteUser(@Param("id")long id){
        ResponseResult result=new ResponseResult();
        int a=userService.deleteUser(id);
        if(a>0){
            result.setMsg("删除成功");
            result.setTotal((long)a);
        }
        else result.setMsg("删除失败");
        return result;
    }


    @RequestMapping(value = "/queryUserByStatus", method = RequestMethod.POST)
    public ResponseResult queryUserByStatus(@Param("status")int status){
        ResponseResult result=new ResponseResult();
        List<User> list=new ArrayList<>();
        list=userService.queryUserByStatus(status);
        if(list.size()>0){
            result.setMsg("查询成功");
            result.setData(list);
            result.setTotal((long)list.size());
        }else {
            result.setMsg("暂无满足条件的用户");
            result.setTotal((long) 0);
        }

        return result;

    }


}