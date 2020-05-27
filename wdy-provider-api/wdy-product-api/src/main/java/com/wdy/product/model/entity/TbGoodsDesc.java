package com.wdy.product.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.Id;
import java.io.Serializable;

/**
 * User: yanghongguang
 * Date: 2020/3/13
 * Time: 14:00
 * Description:
 */
@Data
@Accessors(chain = true)
@TableName("tb_goods_desc")
public class TbGoodsDesc implements Serializable {

    private static final long serialVersionUID = 3594568378062058470L;
    /**
     * SPU_ID
     */
    @Id
    @TableField(value = "goods_id")
    private Long goodsId;
    /**
     * 描述
     */
    private String introduction;
    /**
     * 规格结果集，所有规格， 包含isSelected
     */
    @TableField("specification_items")
    private String specificationItems;
    /**
     * 自定义属性（参数结果集）
     */
    @TableField("custom_attribute_items")
    private String customAttributeItems;

    @TableField("item_images")
    private String itemImages;
    /**
     * 包装列表
     */
    @TableField("package_list")
    private String packageList;
    /**
     * 售后服务
     */
    @TableField("sale_service")
    private String saleService;
}


