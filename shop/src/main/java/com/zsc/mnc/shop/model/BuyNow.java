package com.zsc.mnc.shop.model;

import lombok.Data;

@Data
public class BuyNow {
    Long uid;
    String address;
    String receiver;
    String mobile;
    String userMessage;
    Long pid;
    Long number;
}

