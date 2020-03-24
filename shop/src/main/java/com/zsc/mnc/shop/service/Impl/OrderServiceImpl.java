package com.zsc.mnc.shop.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zsc.mnc.shop.dao.OrderDao;
import com.zsc.mnc.shop.model.OrderItemDetail;
import com.zsc.mnc.shop.model.OrderOtherDetail;
import com.zsc.mnc.shop.model.Orderitem;
import com.zsc.mnc.shop.model.Orders;
import com.zsc.mnc.shop.service.OrderService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {

    @Autowired
     private OrderDao orderDao;

    public int addOrder(Orders order){
        return orderDao.addOrder(order);
    }

    public long queryOrderByDate(String createDate){
        return orderDao.queryOrderByDate(createDate);
    }

    public int addOrderItem(Orderitem orderitem){
        return orderDao.addOrderItem(orderitem);
    }

    public float queryOrderPrice(@Param("oid") long oid){
        return orderDao.queryOrderPrice(oid);
    }

    public int updateOrderPrice(float orderPrice,long id){
        return orderDao.updateOrderPrice(orderPrice,id);
    }

    public PageInfo<Orders> queryOrderListByUid(int page, int size, @Param("uid")long uid){
        PageHelper.startPage(page,size);
        PageHelper.orderBy("id desc");
        PageInfo<Orders> pageInfo=new PageInfo<Orders>(orderDao.queryOrderListByUid(uid));
        return pageInfo;

    }
    public PageInfo<Orders> getAllOrderList(int page,int size){
        PageHelper.startPage(page,size);
        PageHelper.orderBy("id desc");
        PageInfo<Orders> pageInfo=new PageInfo<Orders>(orderDao.getAllOrderList());
        return pageInfo;
    }

    public List<OrderItemDetail> queryOrderItemDetail(long uid, long id) {
        return orderDao.selectOrderItemDetail(uid,id);
    }

    @Override
    public OrderOtherDetail queryOrderOtherDetail(long uid, long id) {
        return orderDao.selectOrderOtherDetail(uid,id);
    }

    public Orders queryOrderByUIdAndCode(String orderCode) {
        return orderDao.selectOrderByUIdAndCode(orderCode);
    }

    public int updateOrderStatus(String status, String confirmDate, String orderCode) {
        return orderDao.selectUpdateOrderStatus(status,confirmDate,orderCode);
    }

    public int updateOrder(Orders order) {
        return orderDao.selectUpdateOrder(order);
    }

    public Orders queryOrderByOrderCode(String orderCode) {
        return orderDao.selectOrderByOrderCode(orderCode);
    }



}
