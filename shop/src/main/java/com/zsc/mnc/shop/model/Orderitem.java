package com.zsc.mnc.shop.model;


import lombok.Data;

@Data
public class Orderitem {
    private Long id;
    private Long pid;
    private Long oid;
    private Long uid;
    private Long number;
    private Float simplePrice;
    private Float totalPrice;
}
