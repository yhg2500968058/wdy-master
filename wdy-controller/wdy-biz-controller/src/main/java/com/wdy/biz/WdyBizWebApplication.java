package com.wdy.biz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class WdyBizWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WdyBizWebApplication.class, args);
    }

}
