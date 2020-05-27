package com.wdy.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdy.product.mapper.TMqMessageLogMapper;
import com.wdy.product.model.entity.TMqMessageLog;
import com.wdy.product.service.TMqMessageLogService;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/3/9
 */
@Service
public class TMqMessageLogServiceImpl extends ServiceImpl<TMqMessageLogMapper, TMqMessageLog> implements TMqMessageLogService {
    @Override
    public void updateMessageStatus(Long messageId, int code) {
        this.baseMapper.updateMessageStatus(messageId,code);
    }

    @Override
    public void updateMessage(Long messageId) {
        this.baseMapper.updateMessage(messageId);
    }
}
