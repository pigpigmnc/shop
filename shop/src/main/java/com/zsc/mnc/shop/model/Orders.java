package com.zsc.mnc.shop.model;

import lombok.Data;

import java.util.Date;

@Data
public class Orders {
    private Long id;
    private String orderCode;
    private String address;
    private String post;
    private String receiver;
    private String mobile;
    private String userMessage;
    private Date createDate;
    private Date payDate;
    private Date deliveryDate;
    private Date confirmDate;
    private Long uid;
    private String status;
    private Float orderPrice;
}
