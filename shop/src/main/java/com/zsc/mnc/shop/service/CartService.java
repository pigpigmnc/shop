package com.zsc.mnc.shop.service;
import com.zsc.mnc.shop.model.Cart;

import java.util.List;


public interface CartService {
    Cart queryCartByUidAndPid(long uid,long pid);
    List<Cart> queryCartListByUid(long uid);
    int addCart(Cart cart);
    int updateCart(Cart cart);
    int deleteCart(long id);
    Cart queryCartById(long id);
    void deleteCartByUidAndPid(long uid,long pid);
}
