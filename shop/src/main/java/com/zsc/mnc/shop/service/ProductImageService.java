package com.zsc.mnc.shop.service;

import com.zsc.mnc.shop.model.ProductImage;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ProductImageService {
    int addProductImage(ProductImage productImage);
    List<String> queryPicListByPid(long pid);
}
