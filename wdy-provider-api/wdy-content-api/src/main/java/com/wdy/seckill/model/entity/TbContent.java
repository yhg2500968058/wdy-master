package com.wdy.seckill.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
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
 * Date: 2020/1/19
 * Time: 15:27
 * Description:
 */
@Data
@Accessors(chain = true)
@TableName("tb_content")
@EqualsAndHashCode(callSuper=true)
public class TbContent extends Model<TbContent> implements Serializable {

    private static final long serialVersionUID = -3422227296785465556L;

    public TbContent(Long id,String title,String url){
        this.id = id;
        this.title = title;
        this.url = url;
    }

    @Min(1)
    @NotNull(groups = {Update.class})
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 内容类目id
     */
    @Min(1)
    @NotNull(groups = {Update.class, Insert.class})
    @TableField("category_id")
    private Long categoryId;
    /**
     * 内容标题
     */
    @NotBlank(groups = {Update.class,Insert.class})
    private String title;
    /**
     * 链接
     */
    @NotBlank(groups = {Update.class,Insert.class})
    private String url;
    /**
     * 图片绝对路径
     */
    @NotBlank(groups = {Update.class,Insert.class})
    private String pic;
    /**
     * 状态
     */
    private String status;
    /**
     * 排序
     */
    @Min(1)
    @NotNull(groups = {Update.class,Insert.class})
    @TableField("sort_order")
    private Long sortOrder;

    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
