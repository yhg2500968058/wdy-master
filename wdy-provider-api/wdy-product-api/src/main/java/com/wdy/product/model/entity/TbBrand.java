package com.wdy.product.model.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.wdy.commons.util.validators.Insert;
import com.wdy.commons.util.validators.Update;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


/**
 * User: yanghongguang
 * Date: 2020/1/2
 * Time: 16:10
 * Description:品牌实体类
 */
@Data
@Accessors(chain = true)
@TableName("tb_brand")
@EqualsAndHashCode(callSuper=true)
public class TbBrand extends Model<TbBrand> {

    /**
     * id
     */
    @Min(1)
    @NotNull(groups = Update.class)
    @TableField("id")
    private Long id;
    /**
     * 品牌名称
     */
    @NotBlank(groups={Update.class, Insert.class})
    private String name;
    /**
     * 品牌首字母
     */
    @NotBlank(groups={Update.class, Insert.class})
    @TableField("first_char")
    @Length(max = 1,groups = {Update.class,Insert.class})
    private String firstChar;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
