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
 * Time: 16:08
 * Description:秒杀商品表
 */
@Data
@Accessors(chain = true)
@TableName("tb_seckill_goods")
@EqualsAndHashCode(callSuper=true)
public class TbSeckillGoods extends Model<TbSeckillGoods>  {

    private static final long serialVersionUID = 4184522408599159654L;
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * spu ID
     */
    @TableField("goods_id")
    private Long goodsId;
    /**
     * sku ID
     */
    @TableField("item_id")
    private Long itemId;
    /**
     * 标题
     */
    private String title;
    /**
     * 商品图片
     */
    @TableField("small_pic")
    private String smallPic;
    /**
     * 原价格
     */
    private BigDecimal price;
    /**
     * 秒杀价格
     */
    @TableField("cost_price")
    private BigDecimal costPrice;
    /**
     * 商家Id
     */
    @TableField("seller_id")
    private String sellerId;
    /**
     * 添加日期
     */
    @TableField("create_time")
    private LocalDateTime createTime;
    /**
     * 审核日期
     */
    @TableField("check_time")
    private LocalDateTime checkTime;
    /**
     * 审核状态
     */
    private String status;
    /**
     * 开始日期
     */
    @TableField("start_time")
    private LocalDateTime startTime;
    /**
     * 结束时间
     */
    @TableField("end_time")
    private LocalDateTime endTime;
    /**
     * 秒杀商品数
     */
    private Integer num;
    /**
     * 剩余库存数
     */
    @TableField("stock_count")
    private Integer stockCount;
    /**
     * 描述
     */
    private String introduction;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
