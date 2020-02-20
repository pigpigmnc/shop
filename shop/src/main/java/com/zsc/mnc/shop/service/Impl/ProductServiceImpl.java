package com.zsc.mnc.shop.service.Impl;

import com.zsc.mnc.shop.dao.ProductMapper;
import com.zsc.mnc.shop.model.Product;
import com.zsc.mnc.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public void addProduct(Product product) {
        productMapper.addProduct(product);

    }
}
