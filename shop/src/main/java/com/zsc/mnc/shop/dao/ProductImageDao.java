package com.zsc.mnc.shop.dao;

import com.zsc.mnc.shop.model.ProductImage;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface ProductImageDao {
    int addProductImage(ProductImage productImage);
    List<String> queryPicListByPid(long pid);
    List<String> queryProductImageUrlById(long id);
}
