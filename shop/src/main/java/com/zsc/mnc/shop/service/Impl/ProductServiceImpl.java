package com.zsc.mnc.shop.service.Impl;

import com.zsc.mnc.shop.dao.ProductDao;
import com.zsc.mnc.shop.dao.ProductImageDao;
import com.zsc.mnc.shop.model.Category;
import com.zsc.mnc.shop.model.Product;
import com.zsc.mnc.shop.model.ProductDetails;
import com.zsc.mnc.shop.model.ProductImage;
import com.zsc.mnc.shop.service.ProductService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductDao productDao;

    @Resource
    private ProductImageDao productImageDao;

    @Override
    public int addProduct(Product product) {
       return productDao.addProduct(product);
    }

    public Product queryProductById(long id){
        return productDao.queryProductById(id);
    }

    public List<Product> getAllProduct(){
       return productDao.getAllProduct();
    }

    public int modifyProduct(Product product){
        return productDao.modifyProduct(product);
    }

    public List<Product> fuzzyQuery(String name){
        return productDao.fuzzyQuery(name);
    }

    public int deleteProduct(long id){
        return productDao.deleteProduct(id);
    }

    public int addCategory(Category category){
        return productDao.addCategory(category);
    }

    public List<Category> categoryList(){
        return productDao.categoryList();
    }

    @Override
    public int insertProductImages(List<ProductImage> productImageList) {
        return productDao.insertProductImages(productImageList);
    }

    public  List<ProductDetails> ProductDetails(long id){
        return productDao.ProductDetails(id);
    }

    @Override
    public ProductDetails getProductDetailById(long id) {
        ProductDetails productDetails = new ProductDetails();
        Product product = productDao.getProductDetailById(id);
        //这里的意思是把product的属性直接复制到productDetails
        //只要属性名称和类型相同就能成功复制过去
        BeanUtils.copyProperties(product,productDetails);
        List<String> productImageList = productImageDao.queryPicListByPid(id);
        productDetails.setFileUrlPath(productImageList);
        return productDetails;
    }


}
