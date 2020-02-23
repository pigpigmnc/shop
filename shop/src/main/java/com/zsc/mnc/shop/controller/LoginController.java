package com.zsc.mnc.shop.controller;

import com.zsc.mnc.shop.model.ResponseResult;
import com.zsc.mnc.shop.model.User;
import com.zsc.mnc.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController //表示该controller返回的不是页面,是json格式数据
@RequestMapping(value = "/login")
public class LoginController {

    @Autowired
    private UserService userService;

    //用postman发送POST请求到http://localhost:8088/check/login?id=1会看到结果
//    @RequestMapping(value = "/login",method = RequestMethod.POST)
//    public ResponseResult login(@RequestParam("id")Long id){
        //这个ResponseResult是为了统一返回的结果格式,
        // 其中的msg字段表示是否能请求到一个结果
        // data字段表示这个接口返回的数据是什么
        // total字段表示这个接口返回的数据总条数是多少
//        ResponseResult result = new ResponseResult();
        //先默认不能请求到结果
       // result.setMsg(String.valueOf(false));
        //通过service层去查找用户
       // User user = userService.findUserById(id);
        //查找到结果,msg改为true,同时把数据user放到data字段中
//        if(user!=null){
//            result.setMsg(String.valueOf(true));
//            result.setData(user);
//        }
//        return result;
//    }


    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseResult login(@RequestParam String username, @RequestParam String password){
        ResponseResult result=new ResponseResult();
        User user=userService.queryUser(username,password);
        if(user==null)
        {
            result.setMsg("用户名或密码错误");
            return result;//为空就直接拦截 返回给前端msg="fail"}
        }
        else
        {
            if(user.getStatus()==0)
            {
                result.setMsg("该用户已被停用");
                return result;//为空就直接拦截 返回给前端msg="fail"}
            }
            else {
                try {
                    result.setMsg("登录成功");
                    return result;
                } catch (Exception e) {
                    e.printStackTrace();
                    result.setMsg("系统出现错误!");
                    return result;//为空就直接拦截 返回给前端msg="fail"}
                }
            }

        }

    }



    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseResult register(@RequestParam("username")String username,@RequestParam("password")String password,
                                  @RequestParam("phone")String phone,@RequestParam("email")String email)
    {
        try{
            User users=userService.queryUserByName(username);
            if(users==null)
            {
                User user=new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                user.setPhone(phone);
                user.setRole(1);
                user.setStatus(1);
                user.setRegisterDate(new Date());
                userService.addUser(user);
                ResponseResult result=new ResponseResult();
                result.setMsg("注册成功");
                return  result;
            }
            else
            {
                ResponseResult result=new ResponseResult();
                result.setMsg("注册失败");
                return  result;
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            ResponseResult result=new ResponseResult();
            result.setMsg("系统出现错误!");
            return result;
        }


    }






}
