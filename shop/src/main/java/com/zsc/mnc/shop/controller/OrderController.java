package com.zsc.mnc.shop.controller;

import com.zsc.mnc.shop.model.*;
import com.zsc.mnc.shop.service.CartService;
import com.zsc.mnc.shop.service.OrderService;
import com.zsc.mnc.shop.service.ProductService;
import com.zsc.mnc.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;
    @Autowired
    private ProductService productService;
    @Autowired
    private CartService cartService;

    @RequestMapping(value = "/addOrder",method = RequestMethod.POST)
    public ResponseResult addOrder(OrderAttach orderAttach){
        ResponseResult result=new ResponseResult();
        Orders order=new Orders();

        order.setUid(orderAttach.getUid());
        order.setAddress(orderAttach.getAddress());
        order.setReceiver(orderAttach.getReceiver());
        order.setMobile(orderAttach.getMobile());
        order.setUserMessage(orderAttach.getUserMessage());

        order.setOrderCode(String.valueOf(System.currentTimeMillis()));
        Date date=new Date();
        order.setCreateDate(date);
        order.setStatus("待付款");

        int n=orderService.addOrder(order);//此处成功插入orders表中


        long orderId=orderService.queryOrderByDate(date.toLocaleString());

        //        接下来要做的是插入该订单的订单项

        List<Long> pidList=orderAttach.getPid();//这里两个36

        Orderitem orderitem=new Orderitem();
        int m=0,a=0;
        int orderPrice=0;
        for(long pid:pidList){//从多选框里把选中的pid遍历出来，然后跟orderitem绑在一起
            Cart cart=cartService.queryCartByUidAndPid(orderAttach.getUid(),pid);
            orderitem.setPid(pid);
            orderitem.setOid(orderId);
            orderitem.setUid(orderAttach.getUid());
            orderitem.setNumber((long) cart.getCount());
            orderitem.setSimplePrice(cart.getSimplePrice());
            orderitem.setTotalPrice(cart.getTotalPrice());

            m=orderService.addOrderItem(orderitem);//逐项插入订单项

            //增加对库存和销量的处理
            if(m==1){
                //库存
                long oldStock=productService.queryOldStock(pid);
                productService.updateStock(pid,oldStock-cart.getCount());
                long oldSaleCount=productService.queryOldSaleCount(pid);
                productService.updateSaleCount(pid,oldSaleCount+cart.getCount());
            }
            //删除对应的购物车！！！！！！！！！！！！
            cartService.deleteCartByUidAndPid(orderAttach.getUid(),pid);
        }
        if(n==1&&m==1){
            orderPrice= (int) orderService.queryOrderPrice(orderId);
            a=orderService.updateOrderPrice(orderPrice,orderId);
        }
        if(a==1){
//            checkPayStation(String.valueOf(System.currentTimeMillis()));//把订单编号传入这个函数里
//                return ResponseDataPay.createBySuccess(WebCts.RESP_SUCCESS,String.valueOf(System.currentTimeMillis()),
//                        String.valueOf(System.currentTimeMillis()), String.valueOf(orderPrice));


//            return ResponseDataPay.createBySuccess(WebCts.RESP_SUCCESS,orderAttach.getUid(),orderid, String.valueOf(order.getOrderCode()),
//                    String.valueOf(order.getOrderCode()), String.valueOf(orderPrice));

            return result;

        }
        else
            //return ResponseDataPay.createByError();


        return result;
    }


    //展示用户的订单列表,所有订单
    @RequestMapping(value = "/orderListByUid",method = RequestMethod.GET)
    public ResponseResult orderListByUid(@RequestParam(defaultValue = "1",required = true,value="pn")Integer pn, @RequestParam("uid") long uid){
        ResponseResult result=new ResponseResult();
        int pageSize=10;
        PageHelper.startPage(pn,pageSize);
        PageInfo<Orders> pageInfo= orderService.queryOrderListByUid(pn,pageSize,uid);
        result.setData(pageInfo);
        return result;
    }
    //后台订单列表
    @RequestMapping(value = "/listAllOrder",method = RequestMethod.GET)
    public ResponseResult listAllOrder(@RequestParam(defaultValue = "1",required = true,value="pn")Integer pn){
        ResponseResult result=new ResponseResult();
        int pageSize=10;
        PageHelper.startPage(pn,pageSize);
        PageInfo<Orders> pageInfo=orderService.getAllOrderList(pn,pageSize);
        result.setData(pageInfo);
        return result;
    }


    //用户订单项的显示
    @RequestMapping(value = "/orderDetail",method = RequestMethod.GET)
    public ResponseResult orderListAndDetail(@RequestParam("uid")long uid,
                                           @RequestParam("id")long id){
        List<OrderItemDetail> orderItemDetailList=orderService.queryOrderItemDetail(uid,id);
        OrderOtherDetail orderOtherDetail=orderService.queryOrderOtherDetail(uid,id);

        OrderDetail orderDetail=new OrderDetail();
        orderDetail.setOrderItemDetailList(orderItemDetailList);
        orderDetail.setOrderOtherDetail(orderOtherDetail);

        ResponseResult result=new ResponseResult();
        result.setData(orderDetail);

        return result;
    }


    //用户自己修改订单状态，将待收货修改为确认收货
    @RequestMapping(value = "/getProduct",method = RequestMethod.GET)
    public ResponseResult getProduct(@RequestParam("orderCode")String orderCode){
        ResponseResult result=new ResponseResult();
        Orders order=orderService.queryOrderByUIdAndCode(orderCode);
        if(order.getStatus()=="已发货"){
            order.setConfirmDate(new Date());
            if(orderService.updateOrderStatus("已确认收货",
                    new Date().toLocaleString(),orderCode)==1){
                result.setMsg("true");
            return result;
            }
            else{
                result.setMsg("false");
            return result;
            }
        }
        else{
            result.setMsg("你的商品还没发货呢");
            return result;
        }
    }

    //后台订单状态修改，改为已发货
    @RequestMapping(value = "/postProduct",method = RequestMethod.GET)
    public ResponseResult postProduct(@RequestParam("orderCode")String orderCode,
                                    @RequestParam("post")String post){
        ResponseResult result=new ResponseResult();
        Orders order=orderService.queryOrderByOrderCode(orderCode);
        order.setStatus("已发货");
        order.setPost(post);
        order.setDeliveryDate(new Date());
        if(orderService.updateOrder(order)==1)
            result.setMsg("成功");
        else
            result.setMsg("失败");
        return result;
    }

    //立即购买商品
    @RequestMapping(value = "/buyNow",method = RequestMethod.GET)
    public ResponseResult buyNow(BuyNow buyNow){
        ResponseResult result=new ResponseResult();
        //用户可以修改和提交的内容有：地址，收货人名字，收货人电话，买家留言，都是order表的字段
        //新建这个订单
        Orders order=new Orders();

        order.setUid(buyNow.getUid());
        order.setAddress(buyNow.getAddress());
        order.setReceiver(buyNow.getReceiver());
        order.setMobile(buyNow.getMobile());
        order.setUserMessage(buyNow.getUserMessage());

        order.setOrderCode(String.valueOf(System.currentTimeMillis()));
        Date date=new Date();
        order.setCreateDate(date);
        order.setStatus("待付款");

        int n=orderService.addOrder(order);//此处成功插入orders表中
        long orderid=orderService.queryOrderByDate(date.toLocaleString());

//        接下来要做的是插入该订单的订单项

        Orderitem orderitem=new Orderitem();
        int m=0,a=0;
        int orderPrice=0;
        orderitem.setPid(buyNow.getPid());
        orderitem.setOid(orderid);
        orderitem.setUid(buyNow.getUid());
        orderitem.setNumber(buyNow.getNumber());

        Product product=productService.queryProductById(buyNow.getPid());


        orderitem.setSimplePrice(product.getPromotePrice());
        orderitem.setTotalPrice(buyNow.getNumber()*product.getPromotePrice());
        m=orderService.addOrderItem(orderitem);//逐项插入订单项

        //增加对库存和销量的处理
        if(m==1){
            //库存
            long oldStock=productService.queryOldStock(buyNow.getPid());
            productService.updateStock(buyNow.getPid(),oldStock-buyNow.getNumber());
            long oldSaleCount=productService.queryOldSaleCount(buyNow.getPid());
            productService.updateSaleCount(buyNow.getPid(),oldSaleCount+buyNow.getNumber());
        }
//        }
        if(n==1&&m==1){
            orderPrice= (int) orderService.queryOrderPrice(orderid);
            a=orderService.updateOrderPrice(orderPrice,orderid);
        }
        if(a==1){
//            return ResponseDataPay.createBySuccess(WebCts.RESP_SUCCESS,buyNow.getUid(),orderid, String.valueOf(order.getOrderCode()),
//                    String.valueOf(order.getOrderCode()), String.valueOf(orderPrice));
            return result;
        }
        else
//            return ResponseDataPay.createByError();
        return result;
    }
    //按订单编号查找订单信息
    @RequestMapping(value = "/findOrderByOrderId",method = RequestMethod.GET)
    public ResponseResult queryOrderByOrderId(@RequestParam("orderCode")String orderCode){
        ResponseResult result=new ResponseResult();
        Orders order=orderService.queryOrderByOrderCode(orderCode);
//        result.setMsg("true");
        result.setData(order);
        return result;
    }




}
