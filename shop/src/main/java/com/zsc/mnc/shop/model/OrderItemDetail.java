package com.zsc.mnc.shop.model;

import lombok.Data;

@Data
public class OrderItemDetail {
    String name;//订单项的商品名称
    Long number;
    Float simplePrice;//商品单价
    Float totalPrice;
}