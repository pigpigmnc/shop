package com.zsc.mnc.shop.dao;

import com.zsc.mnc.shop.model.Cart;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CartDao {
    Cart queryCartByUidAndPid(long uid, long pid);
    List<Cart> queryCartListByUid(long uid);
    int addCart(Cart cart);
    int updateCart(Cart cart);
    int deleteCart(long id);
    Cart queryCartById(long id);

}
