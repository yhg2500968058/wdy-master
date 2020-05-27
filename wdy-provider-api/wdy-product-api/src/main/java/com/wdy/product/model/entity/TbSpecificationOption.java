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
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/6
 */
@Data
@Accessors(chain = true)
@TableName("tb_specification_option")
@EqualsAndHashCode(callSuper=true)
public class TbSpecificationOption extends Model<TbSpecificationOption> {


    //private static final long serialVersionUID = -690443518439364200L;
    /**
     * 规格项id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 规格项名称
     */
    @TableField("option_name")
    private String optionName;
    /**
     * 规格id
     */
    @TableField("spec_id")
    private Long specId;
    /**
     * 排序值
     */
    private Integer orders;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
