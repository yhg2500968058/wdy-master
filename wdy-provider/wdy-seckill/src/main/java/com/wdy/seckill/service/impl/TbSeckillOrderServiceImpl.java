package com.wdy.seckill.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdy.commons.base.enums.OrderStautsEnum;
import com.wdy.seckill.mapper.TbSeckillOrderMapper;
import com.wdy.seckill.model.entity.TbSeckillGoods;
import com.wdy.seckill.model.entity.TbSeckillOrder;
import com.wdy.seckill.service.TbSeckillGoodsService;
import com.wdy.seckill.service.TbSeckillOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * User: yanghongguang
 * Date: 2020/4/1
 * Time: 17:18
 * Description:
 */
@Service(version = "1.0.0")
@Transactional
public class TbSeckillOrderServiceImpl extends ServiceImpl<TbSeckillOrderMapper, TbSeckillOrder> implements TbSeckillOrderService {

    @Autowired
    private TbSeckillGoodsService tbSeckillGoodsService;


    @Override
    public Integer createOrder(Long id, Long seckillId, String userName) {
        TbSeckillGoods seckillGoods = this.tbSeckillGoodsService.selectById(seckillId);
        TbSeckillOrder seckillOrder = new TbSeckillOrder();
        seckillOrder.setId(id);
        seckillOrder.setSeckillId(seckillId);
        seckillOrder.setCreateTime(LocalDateTime.now());
        seckillOrder.setUserId(userName);
        seckillOrder.setSellerId(seckillGoods.getSellerId());
        seckillOrder.setMoney(seckillGoods.getCostPrice());
        seckillOrder.setSellerId(OrderStautsEnum.PRE_PAYMENT.getCode());
        return this.baseMapper.insert(seckillOrder);
    }
}
