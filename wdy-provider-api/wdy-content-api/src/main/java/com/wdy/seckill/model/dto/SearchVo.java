package com.wdy.seckill.model.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 *  条件搜索
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/6
 */
@Data
public class SearchVo implements Serializable {

    private static final long serialVersionUID = 604618889801344119L;
    /**
     * 条件1
     */
    private String name;
    /**
     * 条件2
     */
    private String conditionTwo;
}
