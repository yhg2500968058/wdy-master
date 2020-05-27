package com.wdy;

//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableDubbo
//@MapperScan("com.wdy.user.mapper")
@SpringBootApplication
public class WdyUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(WdyUserApplication.class, args);
    }
}
