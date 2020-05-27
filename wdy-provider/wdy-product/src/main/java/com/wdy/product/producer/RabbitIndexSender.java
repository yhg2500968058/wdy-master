package com.wdy.product.producer;

import com.wdy.commons.base.enums.MSGStatusEnum;
import com.wdy.product.model.entity.TMqMessageLog;
import com.wdy.product.service.TMqMessageLogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
public class RabbitIndexSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private TMqMessageLogService tMqMessageLogService;

    public void sendIndex(TMqMessageLog tMqMessageLog){
        try {
            rabbitTemplate.convertAndSend("indexMsg",tMqMessageLog);
            //消息发送成功 改变状态
            tMqMessageLogService.updateMessageStatus(tMqMessageLog.getMessageId(), MSGStatusEnum.SEND_SUCCESS.getCode());
        } catch (AmqpException e) {
            //消息发送失败 进行后续操作 重试 或者补偿
            log.info("消息发送失败惹....异："+e);
            tMqMessageLogService.updateMessage(tMqMessageLog.getMessageId());
        }
    }

    public void sendTest(){
        rabbitTemplate.convertAndSend("test","hello");
    }
}
