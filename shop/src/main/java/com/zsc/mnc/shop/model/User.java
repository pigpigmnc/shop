package com.zsc.mnc.shop.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
//这个类是对应数据库中的user表
public class User implements Serializable {
    private Long id;
    private String username;
    private String password;
    private String phone;
    private String address;
    private String email;
    private int status;
    private int role;
    private Date registerDate;
}
