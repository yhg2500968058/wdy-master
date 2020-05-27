package com.wdy.biz;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;

@MapperScan("com.wdy.biz.mapper")
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class WdyBizApplication {

    public static void main(String[] args) {
        SpringApplication.run(WdyBizApplication.class, args);
    }

    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    @Bean
    public Queue sellerAuditMsg() {
        return new Queue("sellerAuditMsg", true);
    }
}
