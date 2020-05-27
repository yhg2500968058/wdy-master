package com.wdy.seckill.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.seckill.model.entity.TbSeckillGoods;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: yanghongguang
 * Date: 2020/2/10
 * Time: 16:42
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SeckillTest {

    private static final Logger logger = LoggerFactory.getLogger(SeckillTest.class);

    @Autowired
    private TbSeckillGoodsServiceImpl tbSeckillGoodsService;
    @Autowired
    private Configuration configuration;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private ObjectMapper mapper;

    @Test
    public void test1() {
        Wrapper<List<TbSeckillGoods>> tbSeckillGoodsServiceAll = this.tbSeckillGoodsService.findAll();
        System.out.println(tbSeckillGoodsServiceAll);
    }

    @Test
    public void testSet(){
        redisTemplate.boundHashOps("test").put("name1","niubi");
        System.out.println("完成");
    }
   /* @Test
    public void testGet(){
        //TODO 
        Object tbSeckillGoods =  redisTemplate.boundHashOps("seckillHash").get(1L);
       TbSeckillGoods seckillGoods = JSON.parseArray(tbSeckillGoods.toString(),TbSeckillGoods.class);
        TbSeckillGoods tbSeckillGoods1 = (TbSeckillGoods) tbSeckillGoods;
       // Map<Integer,TbSeckillGoods> integerTbSeckillGoodsMap = (Map<Integer, TbSeckillGoods>) redisTemplate.boundHashOps("seckillHash").get(1);
        System.out.println(tbSeckillGoods1);
        System.out.println("完成");
    }*/
    //TbSeckillGoods(id=1, goodsId=149187842867960, itemId=null, title=秒杀精品女装, smallPic=http://47.100.37.176:9999/develop/2020/04/03/1585893816184028.jpg, price=100.00, costPrice=0.01, sellerId=qiandu, createTime=2020-03-31T14:02:50, checkTime=2017-10-14T21:07:51, status=1, startTime=2020-04-14T14:36:42, endTime=2020-04-20T00:00, num=10, stockCount=10, introduction=清仓大打折)
    @Test
    public void start() throws IOException, TemplateException {
        String path = "E:\\za\\软件工作区\\wdy\\wdy-master\\wdy-ui\\seckill\\";
        Template template = configuration.getTemplate("seckill-item.ftl");
        List<TbSeckillGoods> tbSeckillGoods = this.tbSeckillGoodsService.findSeckillGoods();

        for (TbSeckillGoods tbSeckillGood : tbSeckillGoods) {
            this.redisTemplate.boundHashOps("seckillHash").put(tbSeckillGood.getId(), tbSeckillGood);
            Map<String, Object> dataModel = new HashMap<>();
            dataModel.put("seckill", tbSeckillGood);
            Writer out = new FileWriter(path + tbSeckillGood.getId() + ".html");
            template.process(dataModel,out);
            out.close();
        }    }

    @Test
    public void get() {
        //this.redisTemplate.delete("seckillHash");
        String s = "333";
        s = "555";
        System.out.println(s);
    }

    @Test
    public void getAll(){
        Map<Integer,TbSeckillGoods> seckillHash = this.redisTemplate.boundHashOps("seckillHash").entries();
        for (Integer aLong : seckillHash.keySet()) {
            System.out.println(aLong);
            System.out.println(seckillHash.get(aLong));
        }
    }
}
