package com.zsc.mnc.shop.dao;

import com.zsc.mnc.shop.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserDao {
    //这里的@Param("id")中的对应UserMapper.xml中,这个方法的#{id},
    // 之前我说是userId对应xml文件中的#{xxxx}是错误的
    User findUserById(@Param("id") Long userId);
    List<User> getAllUsers();
    int addUser(User user);
    User queryUserByName(String username);
    User queryUser(String username,String password);

}
