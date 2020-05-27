package com.wdy.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdy.commons.base.constant.Constants;
import com.wdy.commons.base.enums.ItemStautsEnum;
import com.wdy.commons.base.enums.MSGStatusEnum;
import com.wdy.commons.base.enums.TypeEnum;
import com.wdy.commons.base.exception.BusinessException;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.util.id.SnowflakeIdWorker;
import com.wdy.commons.util.wrapper.WrapMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.product.mapper.TMqMessageLogMapper;
import com.wdy.product.mapper.TbGoodsMapper;
import com.wdy.product.model.dto.GoodDTD;
import com.wdy.product.model.dto.Goods;
import com.wdy.product.model.entity.*;
import com.wdy.product.producer.RabbitIndexSender;
import com.wdy.product.producer.RabbitPageSender;
import com.wdy.product.service.*;
import com.wdy.product.vo.SearchVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/12
 */
@Service(version = "1.0.0")
@Transactional
@Slf4j
public class TbGoodsServiceImpl extends ServiceImpl<TbGoodsMapper, TbGoods> implements TbGoodsService {

    @Autowired
    private TbItemCatService tbItemCatService;
    private SnowflakeIdWorker snowflakeIdWorker = new SnowflakeIdWorker(0, 0);
    @Autowired
    private TMqMessageLogMapper tMqMessageLogMapper;
    @Autowired
    private RabbitIndexSender rabbitIndexSender;
    @Autowired
    private RabbitPageSender rabbitPageSender;
    @Autowired
    private TbGoodsDescService tbGoodsDescService;
    @Autowired
    private TbBrandService tbBrandService;
    @Autowired
    private TbItemService tbItemService;

    @Override
    public Wrapper<PageVO<TbGoods>> gatherSave(IPage<TbGoods> iPage) {
        List<TbGoods> tbGoodsList = iPage.getRecords();

        List<Goods> tbGoods = new ArrayList<>();

        for (TbGoods tbGood : tbGoodsList) {
            Goods goods = new Goods();
            goods.setCategory1IdName(tbItemCatService.findById(tbGood.getCategory1Id()).getResult().getName());
            goods.setCategory2IdName(tbItemCatService.findById(tbGood.getCategory2Id()).getResult().getName());
            goods.setCategory3IdName(tbItemCatService.findById(tbGood.getCategory3Id()).getResult().getName());
            BeanUtils.copyProperties(tbGood, goods);
            tbGoods.add(goods);
        }
        PageVO pageVO = new PageVO();
        pageVO.setRows(tbGoods);
        pageVO.setTotal(iPage.getTotal());

        return WrapMapper.ok(pageVO);
    }

    @Override
    public Wrapper<PageVO<Goods>> search(Integer page, Integer rows, Goods goods, String username) {
        Integer page1 = (page - 1) * rows;
        goods.setGoodsName("%" + goods.getGoodsName() + "%");
        username = "yijia";
        List<Goods> tbGoods = this.baseMapper.search(page1, rows, goods, username);
        PageVO<Goods> pageVO = new PageVO<Goods>();
        pageVO.setRows(tbGoods);
        pageVO.setTotal(this.baseMapper.searchTotal(username, goods));
        return WrapMapper.ok(pageVO);
    }

    @Override
    public Wrapper updateStatus(Long[] ids, String status) {

        if (ItemStautsEnum.AUDIT_PASS.getCode().equals(status)) {
            TMqMessageLog pageMsg = new TMqMessageLog(snowflakeIdWorker.nextId(), TypeEnum.CREATE_PAGE.getCode(),
                    JSON.toJSONString(ids), 0, MSGStatusEnum.SENDING.getCode(), LocalDateTime.now().plusMinutes(Constants.TRY_TIMEOUT));
            Integer integer = tMqMessageLogMapper.insert(pageMsg);
            if (integer != 1) {
                throw new BusinessException(500, "pageMsg消息入库失败");
            }
            TMqMessageLog indexMsg = new TMqMessageLog(snowflakeIdWorker.nextId(), TypeEnum.CREATE_INDEX.getCode(),
                    JSON.toJSONString(ids), 0, MSGStatusEnum.SENDING.getCode(), LocalDateTime.now().plusMinutes(Constants.MAX_TRY_COUNT));
            Integer integer1 = tMqMessageLogMapper.insert(indexMsg);
            if (integer1 != 1) {
                throw new BusinessException(500, "indexMsg消息入库失败");
            }

            try {
                rabbitIndexSender.sendIndex(indexMsg);
            } catch (Exception e) {
                log.error("sendIndex error", e);
                tMqMessageLogMapper.updateMessage(indexMsg.getMessageId());
            }
            try {
                rabbitPageSender.sendPage(pageMsg);
            } catch (Exception e) {
                log.error("sendPage error", e);
                tMqMessageLogMapper.updateMessage(pageMsg.getMessageId());
            }

        }
        return null;
    }

    @Override
    public Wrapper save(GoodDTD goods) {
        goods.getGoods().setAuditStatus("0");
        baseMapper.insert(goods.getGoods());
        goods.getGoodsDesc().setGoodsId(goods.getGoods().getId());
        tbGoodsDescService.insert(goods.getGoodsDesc());
        setItemList(goods);
        return null;
    }


    private void setItemList(GoodDTD goods){
        if ("1".equals(goods.getGoods().getIsEnableSpec())){
           List<TbItem> tbItems = (List<TbItem>) JSONArray.parseArray(goods.getItemList(), TbItem.class);

            for (TbItem tbItem : tbItems) {
                String title = goods.getGoods().getGoodsName();
                Map<String,String> map = JSON.parseObject(tbItem.getSpec(),Map.class);
                for (String s : map.keySet()) {
                    title += "" + map.get(s);
                    log.info(title);
                }
                tbItem.setTitle(title);
                setValue(goods,tbItem);
               boolean integer = this.tbItemService.insert(tbItem);
               if (integer){
                   log.info("规格添加成功");
               }
            }
        }else {
            TbItem tbItem = new TbItem();
             tbItem.setTitle(goods.getGoods().getGoodsName());

             tbItem.setPrice(goods.getGoods().getPrice());

             tbItem.setNum(999);

             tbItem.setStatus("0");

             tbItem.setIsDefault("1");
             tbItem.setSpec("{}");
             setValue(goods,tbItem);
             tbItemService.insert(tbItem);
        }
    }

    private void setValue(GoodDTD goodDTD,TbItem item){

        List<Map> mapList = JSON.parseArray(goodDTD.getGoodsDesc().getItemImages(), Map.class);

        if ((mapList.size() > 0)){
            item.setImage((String) mapList.get(0).get("url"));
        }
        //保存三级分类
        item.setCategoryId(goodDTD.getGoods().getCategory3Id());
        //创建时间
        item.setCreateTime(LocalDateTime.now());
        //更新时间
        item.setUpdateTime(LocalDateTime.now());
        //商品id
        item.setGoodsId(goodDTD.getGoods().getId());
        item.setSellerId(goodDTD.getGoods().getSellerId());

        TbItemCat tbItemCat = tbItemCatService.selectOne(new QueryWrapper<TbItemCat>().eq("id",goodDTD.getGoods().getCategory3Id()));
        item.setCategory(tbItemCat.getName());

        TbBrand tbBrand = this.tbBrandService.selectOne(new QueryWrapper<TbBrand>().eq("id",goodDTD.getGoods().getBrandId()));
        item.setBrand(tbBrand.getName());
        item.setSeller(tbBrand.getName());
    }


    @Override
    public Wrapper<PageVO<Goods>> findPageOrByNameSearch(Integer page, Integer rows, SearchVo searchVo) {

        Integer page1 = (page - 1) * rows;
        String name = "";
        if (searchVo == null) {
            name = null;
        }
        if (searchVo != null) {
            name = "%" + searchVo.getName() + "%";
        }

        List<Goods> tbGoodsIPage = this.baseMapper.findPage(page1, rows, name);
        PageVO pageVO = new PageVO();
        pageVO.setRows(tbGoodsIPage);
        pageVO.setTotal(this.baseMapper.findTotal(0L, name));
        return WrapMapper.ok(pageVO);
    }


}
