package com.wdy.task.service;

import com.wdy.product.model.entity.TMqMessageLog;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/3/10
 */

public interface MessageService {

    public boolean isConsumer(Long messageId);

    int updateMessageStatus(TMqMessageLog messageLog);
}
