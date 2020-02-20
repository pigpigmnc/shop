package com.zsc.mnc.shop.controller;

import com.zsc.mnc.shop.model.Product;
import com.zsc.mnc.shop.model.ResponseResult;
import com.zsc.mnc.shop.model.User;
import com.zsc.mnc.shop.service.ProductService;
import com.zsc.mnc.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping(value = "/youlike")
public class ProductController {

    @Autowired
    private ProductService productService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseResult addProduct(Product product){
        ResponseResult result = new ResponseResult();
        product.setCreateDate(new Date());
        productService.addProduct(product);

        return result;

    }
}
