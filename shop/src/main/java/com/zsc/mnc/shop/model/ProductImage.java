package com.zsc.mnc.shop.model;


import lombok.Data;

@Data
public class ProductImage {
    private Long id;
    private Long pid;
    private String filename;
    private String fileUrlPath;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getFileUrlPath() {
        return fileUrlPath;
    }

    public void setFileUrlPath(String fileUrlPath) {
        this.fileUrlPath = fileUrlPath;
    }
}
