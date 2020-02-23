package com.zsc.mnc.shop.model;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResponseResult implements Serializable {

    /**
     * true表示成功请求到数据
     * false表示请求不到数据
     */
    private String msg;

    /**
     * 把真正的数据set进data里头
     */
    private Object data;

    /**
     * 把数据总条数set进total里头
     */
    private Long total;

}
