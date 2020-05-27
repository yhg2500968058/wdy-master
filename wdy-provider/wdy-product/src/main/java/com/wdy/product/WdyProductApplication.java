package com.wdy.product;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@MapperScan("com.wdy.product.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WdyProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(WdyProductApplication.class, args);
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
