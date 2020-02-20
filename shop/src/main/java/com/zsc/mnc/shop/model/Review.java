package com.zsc.mnc.shop.model;

import lombok.Data;

import java.util.Date;


@Data
public class Review {
    private Long id;
    private String content;
    private Long uid;
    private Long pid;
    private Date createDate;
}
