package com.zsc.mnc.shop.service;

import com.zsc.mnc.shop.model.Category;
import com.zsc.mnc.shop.model.Product;
import com.zsc.mnc.shop.model.ProductDetails;
import com.zsc.mnc.shop.model.ProductImage;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {
    int addProduct(Product product);
    Product queryProductById(long id);
    List<Product> getAllProduct();
    int modifyProduct(Product product);
    List<Product> fuzzyQuery(String name);
    int deleteProduct(long id);
    int addCategory(Category category);
    List<Category> categoryList();
    int insertProductImages(List<ProductImage> productImageList);
    List<ProductDetails> ProductDetails(long id);
}
