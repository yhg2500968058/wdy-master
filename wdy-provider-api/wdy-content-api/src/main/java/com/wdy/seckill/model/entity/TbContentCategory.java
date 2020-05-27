package com.wdy.seckill.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.wdy.commons.util.validators.Insert;
import com.wdy.commons.util.validators.Update;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * User: yanghongguang
 * Date: 2020/1/14
 * Time: 16:12
 * Description:
 */
@Data
@Accessors(chain = true)
@TableName("tb_content_category")
@EqualsAndHashCode(callSuper=true)
public class TbContentCategory extends Model<TbContentCategory> implements Serializable {

    /**
     * 类目id
     */
    @Min(1)
    @NotNull(groups = {Update.class})
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;

    /**
     *分类名称
     */
    @NotBlank(groups = {Update.class, Insert.class})
    private String name;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
