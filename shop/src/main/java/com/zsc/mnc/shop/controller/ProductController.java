package com.zsc.mnc.shop.controller;

import com.zsc.mnc.shop.model.Product;
import com.zsc.mnc.shop.model.ResponseResult;
import com.zsc.mnc.shop.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public ResponseResult addProduct(Product product){
        ResponseResult result = new ResponseResult();
        product.setCreateDate(new Date());
        int a=productService.addProduct(product);
        if(a>0) {
            result.setMsg(String.valueOf(true));
            result.setTotal((long) a);
        }
        else result.setMsg(String.valueOf(false));

//        List<Product> list=new ArrayList<>();
//        list=productService.getAllProduct();
//        result.setData(list);
        return result;

    }


    @RequestMapping(value = "/productList",method = RequestMethod.POST)
    public ResponseResult getAllProduct(){
        ResponseResult result=new ResponseResult();
        List<Product> list=new ArrayList<>();
        list=productService.getAllProduct();
        if(list.size()>0){
            result.setMsg(String.valueOf(true));
            result.setTotal((long)list.size());
            result.setData(list);
        }
        else result.setMsg(String.valueOf(false));

        return result;

    }


    @RequestMapping(value = "/modifyProduct",method = RequestMethod.POST)
    public ResponseResult modifyProduct(Product product){
        ResponseResult result=new ResponseResult();
        int a=productService.modifyProduct(product);
        if(a>0) {
            result.setMsg(String.valueOf(true));
            result.setTotal((long) a);
        }
        else result.setMsg(String.valueOf(false));
        return result;

    }


    @RequestMapping(value = "/fuzzyQuery",method = RequestMethod.POST)
    public ResponseResult fuzzyQuery(@RequestParam("name")String name){
        ResponseResult result=new ResponseResult();
        List<Product> list=new ArrayList<>();
        list=productService.fuzzyQuery(name);
        if(list.size()>0){
            result.setMsg(String.valueOf(true));
            result.setTotal((long)list.size());
            result.setData(list);
        }
        else {result.setMsg(String.valueOf(false));
              result.setTotal((long) 0);
        }

        return result;

    }


    @RequestMapping(value = "/deleteProduct",method = RequestMethod.POST)
    public ResponseResult deleteProduct(@Param("id")long id){
        ResponseResult result=new ResponseResult();
        int a=productService.deleteProduct(id);
        if(a>0){
            result.setMsg(String.valueOf(true));

            result.setTotal((long) a);
        }
        else result.setMsg(String.valueOf(false));

        return result;
    }





}
