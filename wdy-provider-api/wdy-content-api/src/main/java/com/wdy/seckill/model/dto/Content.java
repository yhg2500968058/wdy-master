package com.wdy.seckill.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * User: yanghongguang
 * Date: 2020/1/19
 * Time: 15:27
 * Description:
 */
@Data
public class Content implements Serializable {

    private static final long serialVersionUID = -3422227296785465556L;

    private Long id;
    /**
     * 内容类目id
     */

    private String categoryId;
    /**
     * 内容标题
     */

    private String title;
    /**
     * 链接
     */
    private String url;
    /**
     * 图片绝对路径
     */
    private String pic;
    /**
     * 状态
     */
    private String status;
    /**
     * 排序
     */
    private Long sortOrder;

}
