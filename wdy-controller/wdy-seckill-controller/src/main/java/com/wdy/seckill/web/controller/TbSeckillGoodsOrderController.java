package com.wdy.seckill.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wdy.commons.core.controller.BaseController;
import com.wdy.commons.util.wrapper.WrapMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.seckill.service.TbSeckillGoodsService;
import com.wdy.seckill.web.limit.AccessLimitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * User: yanghongguang
 * Date: 2020/3/31
 * Time: 14:10
 * Description:
 */
@RestController
@RequestMapping("/seckillOrder")
public class TbSeckillGoodsOrderController extends BaseController {

    @Resource
    private AccessLimitService accessLimitService;

    @Reference(version = "1.0.0")
    private TbSeckillGoodsService tbSeckillGoodsService;
    @GetMapping("/{id}")
    public Wrapper createSeckillOrder(@PathVariable("id") Long id){
        if(accessLimitService.tryAcquire()){
             logger.info("开始秒杀");
             return this.tbSeckillGoodsService.startSeckill(id,"猛字楼");
        }else {
            logger.info("秒杀失败！");
            return WrapMapper.error("活动太火爆了！，已经售罄了");
        }
    }
}
