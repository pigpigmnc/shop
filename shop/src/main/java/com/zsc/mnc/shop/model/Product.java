package com.zsc.mnc.shop.model;

import lombok.Data;

import java.util.Date;



@Data
public class Product {
    private Long id;
    private String name;
    private String subTitle;
    private Float originalPrice;
    private Float promotePrice;
    private Long stock;
    private Long cid;
    private Date createDate;
    private Long saleCount;




}
