package com.wdy.product.producer;

import com.wdy.commons.base.enums.MSGStatusEnum;
import com.wdy.product.model.entity.TMqMessageLog;
import com.wdy.product.service.TMqMessageLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.support.CorrelationData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/3/9
 */
@Component
@Slf4j
public class RabbitPageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TMqMessageLogService mqMessageLogService;

    public void sendPage(TMqMessageLog pageMsg) {

        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnCallback(returnCallback);

        CorrelationData correlationData = new CorrelationData(pageMsg.getMessageId()+"");
        rabbitTemplate.convertAndSend("pageMsg",pageMsg,correlationData);
    }

    /**
     * 消息确认 回调函数 成功
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = new RabbitTemplate.ConfirmCallback() {
        @Override
        public void confirm(CorrelationData correlationData, boolean ack, String cause) {
            log.info("correlationData:{}",correlationData);
            String messageId = correlationData.getId();
            if (ack){
                //confirm 返回成功 则更行状态
                mqMessageLogService.updateMessageStatus(Long.parseLong(messageId), MSGStatusEnum.SEND_SUCCESS.getCode());
            } else {
              log.error("pageMsg消息发送失败惹...");
              mqMessageLogService.updateMessage(Long.parseLong(messageId));
            }
        }
    };
    /**
     * 回调函数 return返回 失败
     */
    final RabbitTemplate.ReturnCallback returnCallback = new RabbitTemplate.ReturnCallback() {
        @Override
        public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
            log.info("return exchange: {}, routingKey: {}, replyCode: {}, replyText: {}",exchange,routingKey,replyCode,replyText);
        }
    };
}
