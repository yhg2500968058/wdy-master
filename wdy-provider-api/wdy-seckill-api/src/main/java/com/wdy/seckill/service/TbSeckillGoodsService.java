package com.wdy.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.seckill.model.entity.TbSeckillGoods;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/3/24
 */
public interface TbSeckillGoodsService extends IService<TbSeckillGoods> {
    /**
     * 查询所有秒杀商品
     * @return
     */
    Wrapper<List<TbSeckillGoods>> findAll();

    /**
     * 查询剩余库存
     * @param id
     * @return
     */
    Wrapper getStockById(Long id);

    /**
     * 开始秒杀
     * @param id
     * @param 猛字楼
     * @return
     */
    Wrapper startSeckill(Long id, String 猛字楼);


   boolean checkStartSeckill(Long id);

    /**
     * 扣库存
     * @param seckillId
     * @return
     */
    Integer deductioOfInventory(Long seckillId);

    /**
     * 数据库查询秒杀所有秒杀商品
     * @return
     */
    List<TbSeckillGoods> findSeckillGoods();
}
