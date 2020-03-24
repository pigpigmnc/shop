package com.zsc.mnc.shop.service;

import com.github.pagehelper.PageInfo;
import com.zsc.mnc.shop.model.OrderItemDetail;
import com.zsc.mnc.shop.model.OrderOtherDetail;
import com.zsc.mnc.shop.model.Orderitem;
import com.zsc.mnc.shop.model.Orders;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderService {
    int addOrder(Orders order);
    long queryOrderByDate(String createDate);
    int addOrderItem(Orderitem orderitem);
    float queryOrderPrice(@Param("oid") long oid);
    int updateOrderPrice(float orderPrice,long id);
    PageInfo<Orders> queryOrderListByUid(int page, int size, @Param("uid")long uid);
    PageInfo<Orders> getAllOrderList(int page,int size);

    List<OrderItemDetail> queryOrderItemDetail(long uid, long id);
    OrderOtherDetail queryOrderOtherDetail(long uid, long id);

    Orders queryOrderByUIdAndCode(String orderCode);
    int updateOrderStatus(String status, String confirmDate, String orderCode);

    Orders queryOrderByOrderCode(String orderCode);
    int updateOrder(Orders order);




}
