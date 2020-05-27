package com.wdy.seckill.producer;

import lombok.Data;

import java.io.Serializable;

/**
 * User: yanghongguang
 * Date: 2020/4/1
 * Time: 16:24
 * Description:
 */
@Data
public class OrderMsg implements Serializable {
    private static final long serialVersionUID = -7600569238224446481L;
    private Long id;
    private Long seckillId;
    private String userName;

    public OrderMsg(Long id, Long seckillId, String userName) {
        this.id = id;
        this.seckillId = seckillId;
        this.userName = userName;
    }
}
