package com.zsc.mnc.shop.service.Impl;

import com.zsc.mnc.shop.dao.ProductDao;
import com.zsc.mnc.shop.model.Product;
import com.zsc.mnc.shop.service.ProductService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductDao productDao;

    @Override
    public void addProduct(Product product) {
        productDao.addProduct(product);

    }
}
