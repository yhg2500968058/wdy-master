package com.wdy.task.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wdy.product.model.entity.TbGoods;
import com.wdy.product.model.entity.TbGoodsDesc;
import com.wdy.product.model.entity.TbItem;
import com.wdy.product.model.entity.TbItemCat;
import com.wdy.task.mapper.TbGoodsDescMapper;
import com.wdy.task.mapper.TbGoodsMapper;
import com.wdy.task.mapper.TbItemCatMapper;
import com.wdy.task.mapper.TbItemMapper;
import com.wdy.task.service.PageService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * User: yanghongguang
 * Date: 2020/3/11
 * Time: 17:17
 * Description:
 */
@Service
@Slf4j
public class PageServiceImpl implements PageService {
    @Autowired
    private Configuration Configuration;
    @Autowired
    private TbGoodsDescMapper GoodsDescMapper;
    @Autowired
    private TbGoodsMapper goodsMapper;
    @Autowired
    private TbItemCatMapper itemCatMapper;
    @Autowired
    private TbItemMapper itemMapper;
    @Value("${page.path}")
    private String path;
    @Override
    public boolean ListenerPageHtml(JSONArray jsonArray) {
        try {
            Template template = Configuration.getTemplate("item.ftl");
            for (Object id : jsonArray) {
                Long goodsId = Long.parseLong(id.toString());
                //创建数据模型
                Map<String,Object> dataMap = new HashMap<>();
                //商品主表数据
                TbGoods tbGoods = this.goodsMapper.selectById(goodsId);
                dataMap.put("goods",tbGoods);
                TbGoodsDesc tbGoodsDesc = this.GoodsDescMapper.selectOne(new QueryWrapper<TbGoodsDesc>().eq("goods_id",goodsId));
                dataMap.put("goodsDesc",tbGoodsDesc);
                TbItemCat itemCat1 = this.itemCatMapper.selectById(tbGoods.getCategory1Id());
                TbItemCat itemCat2 = this.itemCatMapper.selectById(tbGoods.getCategory2Id());
                TbItemCat itemCat3 = this.itemCatMapper.selectById(tbGoods.getCategory3Id());
                dataMap.put("itemCat1",itemCat1 ==null ? null : itemCat1.getName());
                dataMap.put("itemCat2",itemCat2 ==null ? null : itemCat2.getName());
                dataMap.put("itemCat3",itemCat3 ==null ? null : itemCat3.getName());
                //读取SKU列表
                List<TbItem> tbItems =this.itemMapper.selectList(new QueryWrapper<TbItem>().eq("goods_id",goodsId));

                dataMap.put("itemList",tbItems);
                Writer out = new FileWriter(path + goodsId + ".html");
                template.process(dataMap,out);
                out.close();
                return true;
            }
        } catch (IOException | TemplateException e) {
            log.info("页面静态或失败："+e);
            return false;
        }
       return true;
    }
}
