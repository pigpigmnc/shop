package com.zsc.mnc.shop.controller;

import com.zsc.mnc.shop.model.ResponseResult;
import com.zsc.mnc.shop.model.User;
import com.zsc.mnc.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //表示该controller返回的不是页面,是json格式数据
@RequestMapping(value = "/check")
public class LoginController {

    @Autowired
    private UserService userService;

    //用postman发送POST请求到http://localhost:8088/check/login?id=1会看到结果
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseResult login(@RequestParam("id")Long id){
        //这个ResponseResult是为了统一返回的结果格式,
        // 其中的msg字段表示是否能请求到一个结果
        // data字段表示这个接口返回的数据是什么
        // total字段表示这个接口返回的数据总条数是多少
        ResponseResult result = new ResponseResult();
        //先默认不能请求到结果
        result.setMsg(false);
        //通过service层去查找用户
        User user = userService.findUserById(id);
        //查找到结果,msg改为true,同时把数据user放到data字段中
        if(user!=null){
            result.setMsg(true);
            result.setData(user);
        }
        return result;
    }

}
