package com.zsc.mnc.shop.model;

import lombok.Data;

import java.io.Serializable;

@Data
//这个类是对应数据库中的user表
public class User implements Serializable {
    private Long id;
    private String userName;
    private String password;
}
