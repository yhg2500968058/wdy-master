package com.wdy.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdy.product.model.entity.TMqMessageLog;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/3/9
 */
public interface TMqMessageLogService extends IService<TMqMessageLog> {
    void updateMessageStatus(Long messageId, int code);

    void updateMessage(Long messageId);
}
