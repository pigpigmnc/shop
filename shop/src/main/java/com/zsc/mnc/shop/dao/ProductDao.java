package com.zsc.mnc.shop.dao;

import com.zsc.mnc.shop.model.Category;
import com.zsc.mnc.shop.model.Product;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public interface ProductDao {
    int addProduct(Product product);
    List<Product> getAllProduct();
    int modifyProduct(Product product);
    List<Product> fuzzyQuery(String name);
    int deleteProduct(long id);
    int addCategory(Category category);
    List<Category> categoryList();

}
