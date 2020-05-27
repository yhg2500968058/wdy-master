package com.wdy.product.mapper;

import com.wdy.commons.core.mybatis.MyMapper;
import com.wdy.product.model.entity.TMqMessageLog;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/3/9
 */
@Mapper
public interface TMqMessageLogMapper extends MyMapper<TMqMessageLog> {
    void updateMessageStatus(@Param("messageId") Long messageId, @Param("code") int code);

    void updateMessage(@Param("messageId") Long messageId);
}
