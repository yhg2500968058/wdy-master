package com.wdy.seckill.consumer;

import com.rabbitmq.client.Channel;
import com.wdy.seckill.producer.OrderMsg;
import com.wdy.seckill.service.TbSeckillGoodsService;
import com.wdy.seckill.service.TbSeckillOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * User: yanghongguang
 * Date: 2020/4/1
 * Time: 17:00
 * Description:
 */
@Component
@Slf4j
public class SeckillConsumer {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private TbSeckillGoodsService tbSeckillGoodsService;

    @Autowired
    private TbSeckillOrderService tbSeckillOrderService;

    @RabbitListener(queues = "seckillMsg")
    @RabbitHandler
    public void seckillConsumer(@Payload OrderMsg msg, Channel channel, @Headers Map<String, Object> header) throws IOException {
        log.info("消费端 seckillMsg：{}",msg);
        String s = this.stringRedisTemplate.opsForValue().get(msg.getId());
        if ("1".equals(s)){
            log.info("重复消费");
            Long deliveryTag = (Long) header.get(AmqpHeaders.DELIVERY_TAG);
            channel.basicAck(deliveryTag,false);
            return;
        }
        //扣库存
        Integer updateRow = this.tbSeckillGoodsService.deductioOfInventory(msg.getSeckillId());
        //添加订单
        Integer addRow = this.tbSeckillOrderService.createOrder(msg.getId(),msg.getSeckillId(),msg.getUserName());

        if (updateRow == 1 && addRow == 1){
            log.info("{}消费成功",msg.getId());
            Long deliveryTag = (Long) header.get(AmqpHeaders.DELIVERY_TAG);
            channel.basicAck(deliveryTag,false);
            this.stringRedisTemplate.opsForValue().set(msg.getId().toString(),"1");
        }else{
            log.info("{}消费失败 重试",msg.getId());
            Long deliveryTag = (Long) header.get(AmqpHeaders.DELIVERY_TAG);
            channel.basicAck(deliveryTag,false);
        }

    }
}
