package com.zsc.mnc.shop.dao;

import com.github.pagehelper.PageInfo;
import com.zsc.mnc.shop.model.OrderItemDetail;
import com.zsc.mnc.shop.model.OrderOtherDetail;
import com.zsc.mnc.shop.model.Orderitem;
import com.zsc.mnc.shop.model.Orders;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface OrderDao {
    int addOrder(Orders order);
    long queryOrderByDate(String createDate);
    int addOrderItem(Orderitem orderitem);
    float queryOrderPrice(@Param("oid") long oid);
    int updateOrderPrice(float orderPrice,long id);
    List<Orders> queryOrderListByUid(@Param("uid") long uid);
    List<Orders> getAllOrderList();

    List<OrderItemDetail> selectOrderItemDetail(@Param("uid")long uid, @Param("id")long id);
    OrderOtherDetail selectOrderOtherDetail(@Param("uid")long uid, @Param("id")long id);

    Orders selectOrderByUIdAndCode(@Param("orderCode")String orderCode);
    int selectUpdateOrderStatus(@Param("status")String status,
                                       @Param("confirmDate")String confirmDate,
                                       @Param("orderCode")String orderCode);

    int selectUpdateOrder(Orders order);

    Orders selectOrderByOrderCode(@Param("orderCode") String orderCode);





















}
