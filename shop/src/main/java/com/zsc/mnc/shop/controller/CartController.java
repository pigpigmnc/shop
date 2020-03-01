package com.zsc.mnc.shop.controller;

import com.zsc.mnc.shop.model.Cart;
import com.zsc.mnc.shop.model.Product;
import com.zsc.mnc.shop.model.ResponseResult;
import com.zsc.mnc.shop.service.CartService;
import com.zsc.mnc.shop.service.ProductImageService;
import com.zsc.mnc.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/cart")
public class CartController {
    @Autowired
    private CartService cartService;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImageService productImageService;


    @RequestMapping(value = "/addCart",method = RequestMethod.POST)
    public ResponseResult addCart(@RequestParam("uid")long uid,
                                  @RequestParam("pid")long pid,
                                  @RequestParam("count")int count){
        ResponseResult result=new ResponseResult();
        int n=0;
        Cart oldCart=cartService.queryCartByUidAndPid(uid,pid);
        if(oldCart!=null){
            long oldCount=oldCart.getCount();
            long newCount=oldCount+count;
            oldCart.setCount((int) newCount);
            cartService.updateCart(oldCart);
            n=1;
        }
        else{
            //根据商品ID找到商品的图片路径，商品名称，商品单价
            Product product=productService.queryProductById(pid);
            List<String> productImageList=productImageService.queryPicListByPid(pid);
            Cart cart=new Cart();
            cart.setUid(uid);
            cart.setPid(pid);
            cart.setFileUrlPath(productImageList.get(0));
            cart.setPname(product.getName());
            cart.setSimplePrice(product.getPromotePrice());
            cart.setCount(count);
            cart.setTotalPrice(count*product.getPromotePrice());
            n=cartService.addCart(cart);
        }
        if(n==1)
            result.setMsg("添加成功");
        else
            result.setMsg("添加失败");

        return result;
    }




    @RequestMapping(value = "/cartList",method =RequestMethod.POST )
    public ResponseResult cartList(long uid){
        ResponseResult result=new ResponseResult();
        List<Cart> list=cartService.queryCartListByUid(uid);
        if(list.size()>0){
            result.setMsg("查询成功");
            result.setData(list);
            result.setTotal((long)list.size());
        }else {
            result.setMsg("该用户购物车中尚无商品");
            result.setData("空");
        }
        return result;
    }

    @RequestMapping(value = "deleteCart",method = RequestMethod.POST)
    public ResponseResult deleteCart(long id){
        ResponseResult result=new ResponseResult();
        if(cartService.deleteCart(id)>0)
            result.setMsg("删除成功");
        else result.setMsg("删除失败");

        return result;

    }




}
