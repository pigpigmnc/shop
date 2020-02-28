package com.zsc.mnc.shop.controller;

import com.zsc.mnc.shop.model.Category;
import com.zsc.mnc.shop.model.Product;
import com.zsc.mnc.shop.model.ResponseResult;
import com.zsc.mnc.shop.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    //添加鲜花种类，如玫瑰等
    @RequestMapping(value = "/addCategory",method = RequestMethod.POST)
    public ResponseResult addCategory(Category category){
        ResponseResult result=new ResponseResult();
        if(productService.addCategory(category)>0)
            result.setMsg("添加成功");
        else result.setMsg("添加失败");
        return result;
    }

    //鲜花种类list
    @RequestMapping(value = "/categoryList",method = RequestMethod.POST)
    public ResponseResult categoryList(){
        ResponseResult result=new ResponseResult();
        List<Category> list=productService.categoryList();
        if(list.size()>0){
            result.setMsg("查询成功");
            result.setData(list);
            result.setTotal((long)list.size());
        }else {
            result.setMsg("查询失败");
        }
        return result;
    }






}
