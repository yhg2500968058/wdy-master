package com.wdy.product.model.dto;

import lombok.Data;

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
public class Goods implements Serializable {

    private static final long serialVersionUID = -3020905698986378045L;

    /**
     * 主键
     */
    private Long id;
    /**
     * 商家id
     */
    private String sellerId;
    /**
     * SPU名
     */
    private String goodsName;
    /**
     * 默认SKU
     */
    private Long defaultItemId;
    /**
     * 状态
     */
    private String auditStatus;
    /**
     * 是否上架
     */
    private String isMarketable;
    /**
     * 品牌
     */
    private Long brandId;
    /**
     * 副标题
     */
    private String caption;
    /**
     * 一级类目
     */
    private String category1IdName;
    /**
     * 二级类目
     */
    private String category2IdName;
    /**
     * 三级类目
     */
    private String category3IdName;
    /**
     * 小图
     */
    private String smallPic;
    /**
     * 商城价
     */
    private BigDecimal price;
    /**
     * 分类模板ID
     */
    private Long typeTemplateId;
    /**
     * 是否启用规格
     */
    private String isEnableSpec;
    /**
     * 是否删除
     */
    private String isDelete;
}
