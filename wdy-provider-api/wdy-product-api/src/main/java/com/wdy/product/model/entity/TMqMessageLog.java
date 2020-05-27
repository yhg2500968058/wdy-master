package com.wdy.product.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * User: yanghongguang
 * Date: 2020/3/9
 * Time: 16:28
 * Description:
 */
@Data
@Accessors(chain = true)
@TableName("t_mq_message_log")
public class TMqMessageLog implements Serializable {

    private static final long serialVersionUID = -8578171770113708014L;
    /**
     * 消息唯一ID
     */
    @TableId(value = "message_id",type = IdType.AUTO)
    private Long messageId;
    /**
     * 业务类型
     */
    private Integer type;
    /**
     * 消息内容
     */
    private String message;
    /**
     * 重复次数
     */
    @TableField("try_count")
    private Integer tryCount;
    /**
     * 消息投递状态 0投递中 1投递成功 2投递失败 4处理中
     */
    private Integer status;
    /**
     * 下一次重试时间 或超时时间
     */
    @TableField("next_retry")
    private LocalDateTime nextRetry;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;

    @TableField(exist = false)
    private TMqMessageFailed tMqMessageFailed;

    public TMqMessageLog(Long messageId,Integer type,String message,Integer tryCount,Integer status,LocalDateTime nextRetry){
        this.messageId = messageId;
        this.type = type;
        this.message = message;
        this.tryCount = tryCount;
        this.status = status;
        this.nextRetry = nextRetry;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }


}
