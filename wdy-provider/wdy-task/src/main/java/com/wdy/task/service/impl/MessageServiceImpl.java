package com.wdy.task.service.impl;

import com.wdy.commons.base.enums.MSGStatusEnum;
import com.wdy.product.model.entity.TMqMessageLog;
import com.wdy.task.mapper.TMqMessageLogMapper;
import com.wdy.task.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

/**
 * User: yanghongguang
 * Date: 2020/3/11
 * Time: 16:42
 * Description:
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private TMqMessageLogMapper tMqMessageLogMapper;

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public boolean isConsumer(Long messageId) {
        return this.tMqMessageLogMapper.selectById(messageId).getStatus().equals(MSGStatusEnum.PROCESSING_SUCCESS);
    }

    @Override
    public int updateMessageStatus(TMqMessageLog messageLog) {
        String sql = "update t_mq_message_log set update_time = now(), status = ? where message_id = ?";
        int i = jdbcTemplate.update(sql,messageLog.getStatus(),messageLog.getMessageId());
        return i;
    }
}
