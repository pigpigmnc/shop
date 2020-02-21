package com.zsc.mnc.shop.service;

import com.zsc.mnc.shop.model.Product;

import java.util.List;

public interface ProductService {
    int addProduct(Product product);
    List<Product> getAllProduct();
    int modifyProduct(Product product);
}
