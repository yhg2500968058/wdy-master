package com.wdy.product.config;


import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * User: yanghongguang
 * Date: 2020/1/20
 * Time: 23:53
 * Description:
 */
@Data
@Component
public class FtpProperties {
    @Value("${ftp.port}")
    private int port;

    @Value("${ftp.username}")
    private String username;

    @Value("${ftp.password}")
    private String password;

    @Value("${ftp.address}")
    private String address;

    @Value("${ftp.base.path}")
    private String basePath;

    @Value("${ftp.image.base.url}")
    private String imageBaeUrl;
}
