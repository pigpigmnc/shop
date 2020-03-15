package com.zsc.mnc.shop.model;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class ProductDetails implements Serializable {
    Long id;
    String name;
    String subTitle;
    Float originalPrice;
    Float promotePrice;
    Long stock;
    Long cid;
    Long saleCount;
    Date createDate;
    List<String> fileUrlPath;

}
