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
 * 规格
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/6
 */
@Data
@Accessors(chain = true)
@TableName("tb_specification")
@EqualsAndHashCode(callSuper=true)
public class TbSpecification extends Model<TbSpecification> implements Serializable {

    private static final long serialVersionUID = 5530078831487714838L;

    /**
     * 主键
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 名称
     */
    @TableField("spec_name")
    private String specName;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
