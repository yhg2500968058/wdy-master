package com.wdy.seckill.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * User: yanghongguang
 * Date: 2020/3/7
 * Time: 16:28
 * Description:
 */
@Data
@Accessors(chain = true)
@TableName("tb_seckill_order")
@EqualsAndHashCode(callSuper=true)
public class TbSeckillOrder extends Model<TbSeckillOrder> {

    private static final long serialVersionUID = 6198301750039998983L;
    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 秒杀商品ID
     */
    @TableField("seckill_id")
    private Long seckillId;
    /**
     * 支付金额
     */
    private BigDecimal money;
    /**
     * 用户
     */
    @TableField("user_id")
    private String userId;
    /**
     * 商家
     */
    @TableField("seller_id")
    private String sellerId;
    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;
    /**
     * 支付时间
     */
    @TableField("pay_time")
    private LocalDateTime payTime;
    /**
     * 状态
     */
    private String status;
    /**
     * 收货人地址
     */
    @TableField("receiver_address")
    private String receiverAddress;
    /**
     * 收货人电话
     */
    @TableField("receiver_mobile")
    private String receiverMobile;
    /**
     * 收货人
     */
    private String receiver;
    /**
     * 交易流水
     */
    @TableField("transaction_id")
    private String transactionId;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
