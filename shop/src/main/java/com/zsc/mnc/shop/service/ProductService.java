package com.zsc.mnc.shop.service;

import com.zsc.mnc.shop.model.Product;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {
    int addProduct(Product product);
    List<Product> getAllProduct();
    int modifyProduct(Product product);
    List<Product> fuzzyQuery(String name);
}
