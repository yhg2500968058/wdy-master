package com.wdy.product.text;

import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.product.model.dto.Goods;
import com.wdy.product.producer.RabbitIndexSender;
import com.wdy.product.service.impl.TbGoodsServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * User: yanghongguang
 * Date: 2020/2/10
 * Time: 16:42
 * Description:
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class Test1 {

    private static final Logger logger = LoggerFactory.getLogger(Test1.class);

    @Autowired
    private TbGoodsServiceImpl tbGoodsService;

    @Autowired
    private RabbitIndexSender rabbitIndexSender;
    @Test
    public void test1(){
        Goods goods = new Goods();
        goods.setAuditStatus("0");
        goods.setGoodsName("华为手机");
        Wrapper<PageVO<Goods>> pageVOWrapper = tbGoodsService.search(1, 10, goods, "yijia");
        System.out.println(pageVOWrapper);
    }
    @Test
    public void test2(){
        Long[] longs = new Long[]{149187842868007L};
        System.out.println("1");
        System.out.println("1");
        tbGoodsService.updateStatus(longs,"2");
        System.out.println("2");
    }

    @Test
    public void testTest(){
        String a = "abcc";

        System.out.println(a.lastIndexOf(a.charAt(0)));
        for (int i = 0; i < a.length(); i++) {
           // System.out.println(a.lastIndexOf(a.charAt(i)));
            if (i!=a.lastIndexOf(a.charAt(i))) {
                System.out.println(a.charAt(i));
                System.out.println("false");
                break;
            }
        }

        System.out.println("true");
    }

   static int test1(int i) {
        i += 1;
        return i;
   }

   static int test2(int i) {
        i += 2;
        return i;

   }
   @Test
   public void ternary() {
        boolean boo = false;
        int c = boo ? test1(1) : test2(2);
       System.out.println(c);
   }

    @Test
    public void test(){
        rabbitIndexSender.sendTest();
    }

}
