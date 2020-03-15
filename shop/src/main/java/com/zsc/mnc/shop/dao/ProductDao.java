package com.zsc.mnc.shop.dao;

import com.zsc.mnc.shop.model.Category;
import com.zsc.mnc.shop.model.Product;
import com.zsc.mnc.shop.model.ProductDetails;
import com.zsc.mnc.shop.model.ProductImage;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Component
public interface ProductDao {
    int addProduct(Product product);
    Product queryProductById(long id);
    List<Product> getAllProduct();
    int modifyProduct(Product product);
    List<Product> fuzzyQuery(String name);
    int deleteProduct(long id);
    int addCategory(Category category);
    List<Category> categoryList();
    List<ProductDetails> ProductDetails(long id);

    //这里的意思就是把productImageList换成list，让xml文件去识别
    int insertProductImages(@Param("list") List<ProductImage> productImageList);
}
