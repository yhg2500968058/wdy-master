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
 * @since 2020/1/10
 */

@Data
@Accessors(chain = true)
@TableName("tb_item_cat")
@EqualsAndHashCode(callSuper=true)
public class TbItemCat extends Model<TbItemCat> {

    private static final long serialVersionUID = 2504619983074825825L;
    /**
     * 类目id
     */
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 父类目ID=0时，代表的时一级的类mu
     */
    @TableField("parent_id")
    private Long parentId;
    /**
     * 类目名称
     */
    private String name;
    /**
     * 类型ID
     */
    @TableField("type_id")
    private Long typeId;


    @Override
    protected Serializable pkVal() {
        return id;
    }
}
