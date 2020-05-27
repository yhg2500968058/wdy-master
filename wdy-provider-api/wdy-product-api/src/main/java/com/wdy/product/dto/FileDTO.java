package com.wdy.product.dto;

import lombok.Data;

import java.io.InputStream;
import java.io.Serializable;

@Data
public class FileDTO implements Serializable {

    private static final long serialVersionUID = -139778969814380027L;
    private String name;

    private String originalFilename;


    private  byte[] bytes;

    private InputStream inputStream;

    public FileDTO() {

    }
}
