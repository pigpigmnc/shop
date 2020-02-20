package com.zsc.mnc.shop.model;


import lombok.Data;

@Data
public class ProductImage {
    private Long id;
    private Long pid;
    private String filename;
    private String fileUrlPath;
}
