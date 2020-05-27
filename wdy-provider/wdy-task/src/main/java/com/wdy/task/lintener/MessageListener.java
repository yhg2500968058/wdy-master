package com.wdy.task.lintener;

import com.alibaba.fastjson.JSONArray;
import com.rabbitmq.client.Channel;
import com.wdy.commons.base.enums.MSGStatusEnum;
import com.wdy.product.model.entity.TMqMessageFailed;
import com.wdy.product.model.entity.TMqMessageLog;
import com.wdy.task.mapper.TMqMessageFailedMapper;
import com.wdy.task.service.MessageService;
import com.wdy.task.service.PageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * User: yanghongguang
 * Date: 2020/3/10
 * Time: 17:33
 * Description:
 */
@Component
@Slf4j
public class MessageListener {

    @Autowired
    private MessageService messageService;
    @Autowired
    private PageService pageService;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private TMqMessageFailedMapper tMqMessageFailedService;

    /*@RabbitListener(queues = "indexMsg")
    @RabbitHandler
    public void indexMsg(@Payload TMqMessageLog name){
        System.out.println(name+"1111");
    }*/

    @RabbitListener(queues = "pageMsg")
    @RabbitHandler
    public void pageMsgListener(@Payload TMqMessageLog messageLog,
                        Channel channel,
                        @Headers Map<String, Object> headers) throws Exception {
        log.info("-----------------------------商品详情页面-----------------------------");
        log.info("消费端 pageMsg：{}", messageLog);

        if (messageService.isConsumer(messageLog.getMessageId())) {
            log.info("pageMsg：{} 重复消费", messageLog.getMessageId());
            Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
            channel.basicAck(deliveryTag, false);
            return;
        }
        ;
        messageLog.setStatus(MSGStatusEnum.PROCESSING_IN.getCode());
        int status = messageService.updateMessageStatus(messageLog);
        if (status != 0) {
            JSONArray jsonArray = JSONArray.parseArray(messageLog.getMessage());
            if (this.pageService.ListenerPageHtml(jsonArray)) {
                Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
                channel.basicAck(deliveryTag, false);
                messageLog.setStatus(MSGStatusEnum.PROCESSING_SUCCESS.getCode());
                messageService.updateMessageStatus(messageLog);
                log.info("pageMsg:{} 消费成功", messageLog.getMessageId());
            } else {
                if (!stringRedisTemplate.hasKey("PAGEMSG:" + messageLog.getMessageId())) {
                    stringRedisTemplate.opsForValue()
                            .set("PAGEMSG:" + messageLog.getMessageId(),
                                    "1", 1, TimeUnit.DAYS);
                }
                String num = stringRedisTemplate.opsForValue().get("PAGEMSG:" + messageLog.getMessageId());
                log.info("pageMsg:{} 消费失败，重试第{}次", messageLog.getMessageId(), num);
                if (num.equals("3")) {
                    Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
                    channel.basicAck(deliveryTag, false);
                    messageLog.setStatus(MSGStatusEnum.PROCESSING_FAILED.getCode());
                    messageService.updateMessageStatus(messageLog);
                    TMqMessageFailed failed = new TMqMessageFailed(messageLog.getMessageId(),
                            "消息处理失败", "已达到最大重试次数，但是还是处理失败");
                    tMqMessageFailedService.insert(failed);
                }
                stringRedisTemplate.opsForValue().increment("PAGEMSG:" + messageLog.getMessageId(), 1);
                Long deliveryTag = (Long) headers.get(AmqpHeaders.DELIVERY_TAG);
                channel.basicNack(deliveryTag, false, true);
            }
        }
    }
   /* @RabbitListener(queues = "test")
    @RabbitHandler
    public void test(String name){
        System.out.println(name+ new Date());
    }*/
}
