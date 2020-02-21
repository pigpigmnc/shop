package com.zsc.mnc.shop.controller;

import com.zsc.mnc.shop.model.Product;
import com.zsc.mnc.shop.model.ResponseResult;
import com.zsc.mnc.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public ResponseResult addProduct(Product product){
         int a;
        ResponseResult result = new ResponseResult();
        product.setCreateDate(new Date());
        //int a=productService.addProduct(product);
        //result.setTotal((long) a);
        if(productService.addProduct(product)==1) {
            //a = productService.addProduct(product);

            result.setMsg(true);
            //result.setTotal((long) a);
        }
        else
             result.setMsg(false);
        List<Product> list=new ArrayList<>();
        list=productService.allProduct();
        result.setData(list);
        return result;

    }


}
