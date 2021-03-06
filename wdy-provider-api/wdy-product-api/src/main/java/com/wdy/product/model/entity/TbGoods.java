package com.wdy.product.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wdy.commons.util.validators.Update;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.ibatis.annotations.Insert;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import javax.persistence.Id;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/12
 */
@Data
@Accessors(chain = true)
@TableName("tb_goods")
@Document(indexName = "test")
public class TbGoods implements Serializable {

    private static final long serialVersionUID = -3020905698986378045L;

    /**
     * 主键
     */
    @Id
    @Min(1)
    @NotNull(groups = {Update.class})
    @TableId(value = "id",type = IdType.AUTO)
    private Long id;
    /**
     * 商家id
     */
    @Field
    @TableField("seller_id")
    @NotNull(groups = {Update.class})
    private String sellerId;
    /**
     * SPU名
     */
    @Field
    @TableField("goods_name")
    @NotNull(groups = {Update.class, Insert.class})
    private String goodsName;
    /**
     * 默认SKU
     */
    @Min(1)
    @NotNull(groups = {Update.class})
    @TableField("default_item_id")
    private Long defaultItemId;
    /**
     * 状态 1未审核 2：通过 3驳回
     */
    @TableField("audit_status")
    @NotBlank(groups = {Update.class, Insert.class})
    private String auditStatus;
    /**
     * 是否上架
     */
    @TableField("is_marketable")
    @NotBlank(groups = {Update.class, Insert.class})
    private String isMarketable;
    /**
     * 品牌
     */
    @Min(1)
    @NotNull(groups = {Update.class,Insert.class})
    @TableField("brand_id")
    private Long brandId;
    /**
     * 副标题
     */
    @NotBlank(groups = {Update.class, Insert.class})
    private String caption;
    /**
     * 一级类目
     */
    @Min(1)
    @NotNull(groups = {Update.class,Insert.class})
    @TableField("category1_id")
    private Long category1Id;
    /**
     * 二级类目
     */
    @Min(1)
    @NotNull(groups = {Update.class,Insert.class})
    @TableField("category2_id")
    private Long category2Id;
    /**
     * 三级类目
     */
    @Min(1)
    @NotNull(groups = {Update.class,Insert.class})
    @TableField("category3_id")
    private Long category3Id;
    /**
     * 小图
     */
    @TableField("small_pic")
    @NotBlank(groups = {Update.class, Insert.class})
    private String smallPic;
    /**
     * 商城价
     */
    @NotNull(groups = {Update.class, Insert.class})
    private BigDecimal price;
    /**
     * 分类模板ID
     */
    @Min(1)
    @NotNull(groups = {Update.class})
    @TableField("type_template_id")
    private Long typeTemplateId;
    /**
     * 是否启用规格
     */
    @TableField("is_enable_spec")
    @NotBlank(groups = {Update.class, Insert.class})
    private String isEnableSpec;
    /**
     * 是否删除
     */
    @TableField("is_delete")
    @NotBlank(groups = {Update.class, Insert.class})
    private String isDelete;
}
