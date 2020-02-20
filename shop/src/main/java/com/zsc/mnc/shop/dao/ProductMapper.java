package com.zsc.mnc.shop.dao;

import com.zsc.mnc.shop.model.Product;
import org.springframework.stereotype.Component;

@Component
public interface ProductMapper {
   public void addProduct(Product product);
}
