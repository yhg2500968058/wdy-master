package com.wdy.seckill.producer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * User: yanghongguang
 * Date: 2020/4/1
 * Time: 16:29
 * Description:
 */
@Component
@Slf4j
public class RabbitSeckillSender {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendSeckill(OrderMsg orderMsg) {

        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);

        CorrelationData correlationData = new CorrelationData(orderMsg.getId() + "");
        rabbitTemplate.convertAndSend("seckillMsg", orderMsg, correlationData);
    }

    //回调函数： confirm 确认
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            String messageId = correlationData.getId();
            log.info("correlationData:{},id" + messageId, correlationData);
            if (ack) {
                log.info("消息发送成功");
            } else {
                log.error("消息发送失败，，需要进行异常处理");
            }
        }
    };

    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            log.info("return exchange: {}, routingKey: {}, replyCode: {}, replyText: {}",
                    exchange, routingKey, replyCode, replyText);
        }
    };
}
