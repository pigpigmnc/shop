package com.zsc.mnc.shop.model;


import lombok.Data;

@Data
public class Cart {
    private Long id;
    private Long uid;
    private Long pid;
    private String fileUrlPath;
    private String pname;
    private Float simplePrice;
    private int count;
    private Float totalPrice;


}
