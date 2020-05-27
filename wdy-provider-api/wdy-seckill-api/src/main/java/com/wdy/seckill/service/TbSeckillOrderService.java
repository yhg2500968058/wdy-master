package com.wdy.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdy.seckill.model.entity.TbSeckillOrder;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/4/1
 */
public interface TbSeckillOrderService extends IService<TbSeckillOrder> {
    Integer createOrder(Long id, Long seckillId, String userName);
}
