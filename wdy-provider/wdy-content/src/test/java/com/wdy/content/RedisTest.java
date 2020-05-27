package com.wdy.content;

import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.content.service.impl.TbContentServiceImpl;
import com.wdy.seckill.model.entity.TbContent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

/**
 * User: yanghongguang
 * Date: 2020/2/10
 * Time: 16:42
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    private static final Logger logger = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private TbContentServiceImpl contentService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void testGet(){
        Wrapper<List<TbContent>> byCategoryId = contentService.findByCategoryId(1L);
        System.out.println(byCategoryId);
    }
    @Test
    public void testSet(){
        redisTemplate.boundHashOps("test").put("name","niubi");
        System.out.println("完成");
    }
    @Test
    public void testSet1(){
        System.out.println(redisTemplate.opsForHash().get("666","sdf"));
        System.out.println("666");
    }



    @Test
    public void get() {
        // TODO 测试线程安全
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        IntStream.range(0, 1000).forEach(i ->
                executorService.execute(() -> stringRedisTemplate.opsForValue().increment("kk", 1))
        );
        stringRedisTemplate.opsForValue().set("k1", "v1");
        final String k1 = stringRedisTemplate.opsForValue().get("k1");
        logger.info("[字符缓存结果] - [{}]", k1);
        // TODO 以下只演示整合，具体Redis命令可以参考官方文档，Spring Data Redis 只是改了个名字而已，Redis支持的命令它都支持
        String key = "battcn:user:1";
        //redisCacheTemplate.opsForValue().set(key, new TbContent(1L, "u1", "pa"));
        // TODO 对应 String（字符串）
      //  final TbContent user = (TbContent) redisCacheTemplate.opsForValue().get(key);
      //  logger.info("[对象缓存结果] - [{}]", user);
    }
}
