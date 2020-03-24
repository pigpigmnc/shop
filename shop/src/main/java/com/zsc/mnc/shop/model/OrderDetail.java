package com.zsc.mnc.shop.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDetail {
    OrderOtherDetail orderOtherDetail;
    List<OrderItemDetail> orderItemDetailList;
}

