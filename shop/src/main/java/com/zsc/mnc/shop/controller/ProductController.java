package com.zsc.mnc.shop.controller;

import com.zsc.mnc.shop.model.*;
import com.zsc.mnc.shop.service.ProductImageService;
import com.zsc.mnc.shop.service.ProductService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping(value = "/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductImageService productImageService;

    @RequestMapping(value = "/addProduct",method = RequestMethod.POST)
    public ResponseResult addProduct(Product product){
        ResponseResult result = new ResponseResult();
        product.setCreateDate(new Date());
        int a=productService.addProduct(product);
        if(a>0) {
            result.setMsg(String.valueOf(true));
            result.setTotal((long) a);
        }
        else result.setMsg(String.valueOf(false));

//        List<Product> list=new ArrayList<>();
//        list=productService.getAllProduct();
//        result.setData(list);
        return result;

    }


    @RequestMapping(value = "/productList",method = RequestMethod.POST)
    public ResponseResult getAllProduct(){
        ResponseResult result=new ResponseResult();
        List<Product> list=new ArrayList<>();
        list=productService.getAllProduct();
        if(list.size()>0){
            result.setMsg(String.valueOf(true));
            result.setTotal((long)list.size());
            result.setData(list);
        }
        else result.setMsg(String.valueOf(false));

        return result;

    }


    @RequestMapping(value = "/modifyProduct",method = RequestMethod.POST)
    public ResponseResult modifyProduct(Product product){
        ResponseResult result=new ResponseResult();
        int a=productService.modifyProduct(product);
        if(a>0) {
            result.setMsg(String.valueOf(true));
            result.setTotal((long) a);
        }
        else result.setMsg(String.valueOf(false));
        return result;

    }


    @RequestMapping(value = "/fuzzyQuery",method = RequestMethod.POST)
    public ResponseResult fuzzyQuery(@RequestParam("name")String name){
        ResponseResult result=new ResponseResult();
        List<Product> list=new ArrayList<>();
        list=productService.fuzzyQuery(name);
        if(list.size()>0){
            result.setMsg(String.valueOf(true));
            result.setTotal((long)list.size());
            result.setData(list);
        }
        else {result.setMsg(String.valueOf(false));
              result.setTotal((long) 0);
        }

        return result;

    }


    @RequestMapping(value = "/deleteProduct",method = RequestMethod.POST)
    public ResponseResult deleteProduct(@Param("id")long id){
        ResponseResult result=new ResponseResult();
        int a=productService.deleteProduct(id);
        if(a>0){
            result.setMsg(String.valueOf(true));

            result.setTotal((long) a);
        }
        else result.setMsg(String.valueOf(false));

        return result;
    }

    //添加鲜花种类，如玫瑰等
    @RequestMapping(value = "/addCategory",method = RequestMethod.POST)
    public ResponseResult addCategory(Category category){
        ResponseResult result=new ResponseResult();
        if(productService.addCategory(category)>0)
            result.setMsg("添加成功");
        else result.setMsg("添加失败");
        return result;
    }

    //鲜花种类list
    @RequestMapping(value = "/categoryList",method = RequestMethod.POST)
    public ResponseResult categoryList(){
        ResponseResult result=new ResponseResult();
        List<Category> list=productService.categoryList();
        if(list.size()>0){
            result.setMsg("查询成功");
            result.setData(list);
            result.setTotal((long)list.size());
        }else {
            result.setMsg("查询失败");
        }
        return result;
    }


    @RequestMapping("/uploadPictures")
//    public ResponseImageUrl uploadPictures(@RequestParam("file") CommonsMultipartFile file) {
    public ResponseResult uploadPictures(@RequestParam("pid")long pid,
                                         @RequestParam("files") MultipartFile[] files) {
        ResponseResult result = new ResponseResult();
        int n=0;
        String fileName,fileUrlPath;

        List<ProductImage> imageList = new ArrayList<>();
        //判断文件是否为空
        if(files.length!=0){
            for (MultipartFile file:files) {
                String uuid = UUID.randomUUID().toString().trim();
                String fileN=file.getOriginalFilename();
                int index=fileN.indexOf(".");
                fileName=uuid+fileN.substring(index);
                File fileMkdir;
                try {
//            fileMkdir=new File("D:\\mall-images");
                    fileMkdir=new File("D:\\Program Files\\Tomcat 8.5\\webapps\\ROOT\\mall-images");

                    if(!fileMkdir.exists()) {
                        fileMkdir.mkdir();
                    }
                    //定义输出流 将文件保存在D盘    file.getOriginalFilename()为获得文件的名字
                    FileOutputStream os = new FileOutputStream(fileMkdir.getPath()+"\\"+fileName);
                    InputStream in = file.getInputStream();
                    int b;
                    while((b=in.read())!=-1){ //读取文件
                        os.write(b);
                    }
                    os.flush(); //关闭流
                    in.close();
                    os.close();
                    System.out.println(fileMkdir.toString());
                } catch (Exception e) {
                    result.setMsg("上传失败");
                    return result;
                }
                //访问路径为http://localhost:8081/+fileurlpath
                fileUrlPath="mall-images/"+fileName;
                ProductImage productImage=new ProductImage();
                productImage.setPid(pid);
                productImage.setFilename(fileName);
                productImage.setFileUrlPath(fileUrlPath);
//                //把它放到一个列表里头，一次性批量插入
                imageList.add(productImage);

            }
            n=productService.insertProductImages(imageList);
            if(n>0){
                result.setMsg("上传成功！");
                result.setData(imageList);
                result.setTotal(Long.valueOf(n));
                return result;
            }
            else{
                result.setMsg("上传失败");
                return result;
            }
        }else{
            result.setMsg("上传文件为空");
            return result;
        }
    }

    @RequestMapping(value = "/picList",method = RequestMethod.POST)
    public ResponseResult queryPicListByPid(long pid){
        ResponseResult result=new ResponseResult();
        List<String> list=productImageService.queryPicListByPid(pid);
        if(list.size()>0){
            result.setTotal((long)list.size());
            result.setData(list);
            result.setMsg("查询成功");
        }else {
            result.setMsg("查询失败");
            result.setData("暂无图片");
            result.setTotal((long)0);
        }
        return result;
    }




    @RequestMapping(value = "/productDetails",method = RequestMethod.POST)
    public ResponseResult productDetails(long id){
        ResponseResult result=new ResponseResult();
        Product product=productService.queryProductById(id);
        if(product!=null){
        List<String> productImageList=productImageService.queryPicListByPid(product.getId());
        ProductDetails productDetails=new ProductDetails();
        productDetails.setId(product.getId());
        productDetails.setName(product.getName());
        productDetails.setSubTitle(product.getSubTitle());
        productDetails.setOriginalPrice(product.getOriginalPrice());
        productDetails.setPromotePrice(product.getPromotePrice());
        productDetails.setStock(product.getStock());
        productDetails.setCid(product.getCid());
        productDetails.setSaleCount(product.getSaleCount());
        productDetails.setCreateDate(product.getCreateDate());
        productDetails.setFileUrlPath(productImageList);
        result.setData(productDetails);
        result.setMsg("查询成功");
        }else {
            result.setMsg("查询失败，该商品不存在");
        }
        return result;
    }

//    @RequestMapping(value = "/productDetails",method = RequestMethod.POST)
//    public ResponseResult productDetails(long id){
//        ResponseResult result=new ResponseResult();
//        List<ProductDetails> list=productService.ProductDetails(id);
//        if(list.size()>0){
//            result.setMsg("查询成功");
//            result.setData(list);
//        }else {
//            result.setMsg("查询失败");
//        }
//        return result;
//    }



}
