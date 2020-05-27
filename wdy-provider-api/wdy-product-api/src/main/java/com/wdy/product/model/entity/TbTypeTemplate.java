package com.wdy.product.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * User: yanghongguang
 * Date: 2020/1/8
 * Time: 16:07
 * Description:
 */
@Data
@Accessors(chain = true)
@TableName("tb_type_template")
@EqualsAndHashCode(callSuper=true)
public class TbTypeTemplate extends Model<TbTypeTemplate> {

    private static final long serialVersionUID = 1335614679611784298L;
    /**
     * 模板id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 模板名称
     */
    private String name;
    /**
     * 关联规格
     */
    @TableField("spec_ids")
    private String specIds;
    /**
     * 关联品牌
     */
    @TableField("brand_ids")
    private String brandIds;
    /**
     * 自定义属性
     */
    @TableField("custom_attribute_items")
    private String customAttributeItems;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
