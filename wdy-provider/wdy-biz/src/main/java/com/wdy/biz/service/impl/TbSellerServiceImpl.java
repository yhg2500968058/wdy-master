package com.wdy.biz.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdy.biz.mapper.TbSellerMapper;
import com.wdy.biz.model.entity.TbSeller;
import com.wdy.biz.service.TbSellerService;
import com.wdy.biz.vo.SearchVo;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.util.wrapper.WrapMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;

/**
 * User: yanghongguang
 * Date: 2020/1/2
 * Time: 13:33
 * Description:
 */
@Service(version = "1.0.0",timeout = 6000)
@Transactional
public class TbSellerServiceImpl extends ServiceImpl<TbSellerMapper,TbSeller> implements TbSellerService {

    @Override
    public Wrapper<PageVO<TbSeller>> listPage(Integer pageNum, Integer pageSize) {
        if (pageNum == null || pageNum == 0) {
            pageNum = TbSeller.PAGE_NUM;
        }

        if (pageSize == null || pageSize == 0) {
            pageSize = TbSeller.PAGE_SIZE;
        }

        IPage<TbSeller> iPage = this.baseMapper.selectPage(
                new Page<>(pageNum, pageSize), new QueryWrapper<TbSeller>());
        PageVO<TbSeller> pageVO = new PageVO<>();
        pageVO.setRows(iPage.getRecords());
        pageVO.setTotal(iPage.getTotal());
        return WrapMapper.ok(pageVO);
    }

    @Override
    public TbSeller findByLoginName(String username) {
        TbSeller tbSeller = this.baseMapper.selectOne(new QueryWrapper<TbSeller>()
                .eq("seller_id",username));
        return tbSeller;
    }

    @Override
    public Wrapper<PageVO<TbSeller>> findPage(Integer pageNum, Integer pageSize, SearchVo searchVo) {
        if (searchVo == null){
            return listPage(pageNum,pageSize);
        }
        if (searchVo.getStatus().equals("4")){
            return listPage(pageNum,pageSize);
        }
        if (pageNum == null || pageNum == 0){
            pageNum = TbSeller.PAGE_NUM;
        }
        if (pageSize == null || pageSize == 0){
            pageSize = TbSeller.PAGE_SIZE;
        }
        IPage<TbSeller> iPage = this.baseMapper.selectPage(new Page<>(pageNum,pageSize),
                new QueryWrapper<TbSeller>()
                        .like(StrUtil.isNotBlank(searchVo.getName()),"name",searchVo.getName())
                        .like(StrUtil.isNotBlank(searchVo.getNickName()),"nick_name",searchVo.getNickName())
                        .eq("status",searchVo.getStatus()));

        PageVO pageVO = new PageVO();
        pageVO.setRows(iPage.getRecords());
        pageVO.setTotal(iPage.getTotal());

        return WrapMapper.ok(pageVO);
    }

    @Autowired
    private AmqpTemplate amqpTemplate;

    @Override
    public Wrapper<TbSeller> findOne(String id) {
       TbSeller tbSeller = this.baseMapper.selectOne(new QueryWrapper<TbSeller>().eq("seller_id",id));
        return WrapMapper.ok(tbSeller);
    }

    @Override
    public Wrapper updateBySellerId(String id, String status) {
        TbSeller tbSeller = this.baseMapper.selectById(id);
        tbSeller.setStatus(status);
        this.amqpTemplate.convertAndSend("sellerAuditMsg", JSON.toJSONString(tbSeller));
        Integer integer = this.baseMapper.updateById(tbSeller);
        return WrapMapper.ok();
    }

}
