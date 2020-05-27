package com.wdy.product.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/3/10
 */
@Configuration
public class RabbitConfig {

    @Bean
    public Queue indexMsg() {
        return new Queue("indexMsg", true);
    }
    @Bean
    public Queue pageMsg(){
        return new Queue("pageMsg",true);
    }

    @Bean Queue test(){
        return new Queue("test",true);
    }
}