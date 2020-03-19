package com.zsc.mnc.shop.service.Impl;

import com.zsc.mnc.shop.dao.ProductImageDao;
import com.zsc.mnc.shop.model.ProductImage;
import com.zsc.mnc.shop.service.ProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service("productImageService")
public class ProductImageServiceImpl implements ProductImageService {

    @Autowired
    private ProductImageDao productImageDao;

    public int addProductImage(ProductImage productImage) {
        return productImageDao.addProductImage(productImage);
    }


    public List<String> queryPicListByPid(long pid) {
        return productImageDao.queryPicListByPid(pid);
    }
}
