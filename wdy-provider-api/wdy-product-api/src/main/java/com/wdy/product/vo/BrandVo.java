package com.wdy.product.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * User: yanghongguang
 * Date: 2020/1/3
 * Time: 15:19
 * Description:条件查询
 */
@Data
public class BrandVo implements Serializable {
    /**
     * 品牌名字
     */
    private String name;
    /**
     * 品牌首字母
     */
    private String firstChar;

}
