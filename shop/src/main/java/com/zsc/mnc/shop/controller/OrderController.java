package com.zsc.mnc.shop.controller;

import com.zsc.mnc.shop.service.CartService;
import com.zsc.mnc.shop.service.OrderService;
import com.zsc.mnc.shop.service.ProductService;
import com.zsc.mnc.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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




}
