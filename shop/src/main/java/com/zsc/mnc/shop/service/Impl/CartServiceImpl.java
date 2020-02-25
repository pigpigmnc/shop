package com.zsc.mnc.shop.service.Impl;

import com.zsc.mnc.shop.dao.CartDao;
import com.zsc.mnc.shop.model.Cart;
import com.zsc.mnc.shop.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("cartService")
public class CartServiceImpl implements CartService {
    @Autowired
    private CartDao cartDao;

    public Cart queryCartByUidAndPid(long uid, long pid){
        return cartDao.queryCartByUidAndPid(uid,pid);
    }


    public List<Cart> queryCartListByUid(long uid){
        return cartDao.queryCartListByUid(uid);
    }


    public int addCart(Cart cart){
        return cartDao.addCart(cart);
    }


    public int updateCart(Cart cart){
        return cartDao.updateCart(cart);
    }


    public int deleteCart(long id){
        return cartDao.deleteCart(id);
    }


    public Cart queryCartById(long id){
        return cartDao.queryCartById(id);
    }
}
