package com.wdy.product.model.entity;

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
 * Time: 16:34
 * Description:
 */
@Data
@Accessors(chain = true)
@TableName("t_mq_message_failed")
public class TMqMessageFailed implements Serializable {

    private static final long serialVersionUID = 2982014614531801358L;

    /**
     * 处理失败唯一ID
     */
    @TableId("fail_id")
    private Long faiId;
    /**
     * 消息唯一ID
     */
    @TableField("message_id")
    private Long messageId;
    /**
     * 失败原因 - 简略
     */
    @TableField("fail_title")
    private String failTitle;
    /**
     * 失败原因 - 详细
     */
    @TableField("fail_desc")
    private String failDesc;
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

    public TMqMessageFailed(Long faiId,Long messageId,String failTitle,String failDesc,LocalDateTime createTime,LocalDateTime updateTime){
        this.faiId = faiId;
        this.messageId = messageId;
        this.failTitle = failTitle;
        this.failDesc = failDesc;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public TMqMessageFailed(Long messageId,String failTitle,String failDesc){
        this.messageId = messageId;
        this.failTitle = failTitle;
        this.failDesc = failDesc;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }


}
