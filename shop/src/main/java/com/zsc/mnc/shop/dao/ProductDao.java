package com.zsc.mnc.shop.dao;

import com.zsc.mnc.shop.model.Product;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductDao {
   public int addProduct(Product product);
   public List<Product> allProduct();
}
