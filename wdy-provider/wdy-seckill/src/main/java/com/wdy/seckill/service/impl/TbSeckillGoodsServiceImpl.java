package com.wdy.seckill.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdy.commons.util.id.SnowflakeIdWorker;
import com.wdy.commons.util.wrapper.WrapMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.seckill.mapper.TbSeckillGoodsMapper;
import com.wdy.seckill.model.entity.TbSeckillGoods;
import com.wdy.seckill.producer.OrderMsg;
import com.wdy.seckill.producer.RabbitSeckillSender;
import com.wdy.seckill.service.TbSeckillGoodsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * User: yanghongguang
 * Date: 2020/3/24
 * Time: 18:54
 * Description:
 */
@Service(version = "1.0.0", timeout = 6000)
@Transactional
@Slf4j
public class TbSeckillGoodsServiceImpl extends ServiceImpl<TbSeckillGoodsMapper, TbSeckillGoods> implements TbSeckillGoodsService {

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RabbitSeckillSender rabbitSeckillSender;

    @Override
    public Wrapper<List<TbSeckillGoods>> findAll() {
        List<TbSeckillGoods> tbSeckillGoods = null;
        Map<Long, TbSeckillGoods> seckillGoodsMap = this.redisTemplate.boundHashOps("seckillHash").entries();
        if (seckillGoodsMap == null || seckillGoodsMap.values().isEmpty()) {
            log.info("从数据库中取数据");
            tbSeckillGoods = this.baseMapper.selectList(new QueryWrapper<TbSeckillGoods>());

            tbSeckillGoods.forEach(seckillGoods -> {
                this.redisTemplate.boundHashOps("seckillHash").put(seckillGoods.getId(),
                        seckillGoods.toString());
            });

        } else {
            log.info("从redis中取数据");
            tbSeckillGoods = new ArrayList<>(seckillGoodsMap.values());
        }
        return WrapMapper.ok(tbSeckillGoods);
    }

    @Override
    public Wrapper getStockById(Long id) {
        TbSeckillGoods seckillGoods = (TbSeckillGoods) this.redisTemplate.boundHashOps("seckillHash").get(id);
        //TbSeckillGoods seckillGoods = (TbSeckillGoods) object;
        if (seckillGoods == null) {
            seckillGoods = this.baseMapper.selectOne(new QueryWrapper<TbSeckillGoods>().eq("id", id));
            if (seckillGoods != null) {
                this.redisTemplate.boundHashOps("seckillHash").put(seckillGoods.getId(), seckillGoods);
            }
        }
        return WrapMapper.ok(seckillGoods.getStockCount());
    }

    //TODO 秒杀下订单
    @Override
    public Wrapper startSeckill(Long id, String name) {
        if (!checkStartSeckill(id)) {
            return WrapMapper.wrap(600, "该秒杀商品不在活动范围之内，请注意时间！");
        }
        log.info("开始获取锁资源...");
        String lockKey = "BM_MARKET_SECKILL_" + id;
        SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0, 0);
        String lockVal = String.valueOf(snowflakeIdWorker.nextId());
        try {
            if (!this.stringRedisTemplate.opsForValue().setIfAbsent(lockKey, lockVal)) {
                log.info("获取锁资源失败！快速返回");
                return WrapMapper.wrap(600, "很抱歉，然太多了，换个动作再试试憋~~");
            }
            log.info("获取锁资源");
            if (stringRedisTemplate.hasKey("BM_MARKET_SECKILL_LIMIT_" + id + "_" + name)) {
                log.info("已经检测到用户重复购买...");
                return WrapMapper.wrap(600, "您正在参与该活动，不能重复购买~~");
            } else {
                TbSeckillGoods seckillGoods = (TbSeckillGoods) this.redisTemplate.boundHashOps("seckillHash").get(id);
                //获取库存
                int surplusStock = seckillGoods.getStockCount() == null ? 0 : seckillGoods.getStockCount();
                //扣除库存
                surplusStock--;
                if (surplusStock >= 0) {
                    seckillGoods.setStockCount(surplusStock);
                    redisTemplate.boundHashOps("seckillHash").put(id, seckillGoods);
                    stringRedisTemplate.opsForValue().set("BM_MARKET_SECKILL_LIMIT_" + id + "_" + name, "true", 3600 * 24, TimeUnit.SECONDS);

                    Long onlyId = snowflakeIdWorker.nextId();
                    this.stringRedisTemplate.opsForValue().set(String.valueOf(onlyId), "0");
                    OrderMsg orderMsg = new OrderMsg(onlyId, id, name);
                    this.rabbitSeckillSender.sendSeckill(orderMsg);
                    return WrapMapper.ok(onlyId);
                } else {
                    log.info("商品库存不足");
                    return WrapMapper.wrap(600, "秒杀失败，商品已经售空");
                }
            }
        } catch (Exception e) {
            log.info("秒杀失败");
            return WrapMapper.wrap(600, "秒杀失败商品已售空");
        } finally {
            log.info("释放锁资源");
            String currentValue = stringRedisTemplate.opsForValue().get(lockKey);
            if (currentValue.equals(lockVal) && StrUtil.isBlank(currentValue)) {
                stringRedisTemplate.delete(lockKey);
            }
        }
    }

    @Override
    public boolean checkStartSeckill(Long id) {
        TbSeckillGoods seckillGoods = (TbSeckillGoods) this.redisTemplate.boundHashOps("seckillHash").get(id);
        LocalDateTime startTime = seckillGoods.getStartTime();
        LocalDateTime endTime = seckillGoods.getEndTime();
        LocalDateTime nowTime = LocalDateTime.now();
        return nowTime.isAfter(startTime) && nowTime.isBefore(endTime);
    }

    @Override
    public Integer deductioOfInventory(Long seckillId) {
        TbSeckillGoods seckillGoods = this.baseMapper.selectById(seckillId);
        seckillGoods.setStockCount(seckillGoods.getStockCount() - 1);
        return this.baseMapper.updateById(seckillGoods);
    }

    @Override
    public List<TbSeckillGoods> findSeckillGoods() {
        List<TbSeckillGoods> tbSeckillGoods = this.baseMapper.selectList(new QueryWrapper<TbSeckillGoods>());
        return tbSeckillGoods;
    }
}
