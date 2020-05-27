package com.wdy.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * User: yanghongguang
 * Date: 2020/1/7
 * Time: 11:39
 * Description:
 */
@Data
public class SpecificationVo implements Serializable {


    private static final long serialVersionUID = 862901427529020152L;
    /**
     * 规格名称
     */
    private String name;
    /**
     * 规格类型 排序
     */
    private String[] a;

}
