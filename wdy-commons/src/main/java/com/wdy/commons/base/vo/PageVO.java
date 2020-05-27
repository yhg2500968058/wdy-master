package com.wdy.commons.base.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * User: yanghongguang
 * Date: 2020/1/2
 * Time: 13:16
 * Description:
 */
@Data
public class PageVO<T> implements Serializable {

    private static final long serialVersionUID = 5817538213649817388L;
    /**
     * 总记录数
     */
    private Long total;
    /**
     * 内容
     */
    private List<T> rows;
}
