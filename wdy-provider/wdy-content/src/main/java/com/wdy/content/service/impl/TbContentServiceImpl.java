package com.wdy.content.service.impl;


import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdy.commons.base.enums.ErrorCodeEnum;
import com.wdy.commons.base.exception.BusinessException;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.util.validators.BeanValidators;
import com.wdy.commons.util.validators.Insert;
import com.wdy.commons.util.wrapper.WrapMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.content.mapper.TbContentMapper;
import com.wdy.seckill.model.dto.Content;
import com.wdy.seckill.model.entity.TbContent;
import com.wdy.seckill.service.TbContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <p>
 * 广告管理
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/19
 */
@Service(version = "1.0.0")
@Transactional
public class TbContentServiceImpl extends ServiceImpl<TbContentMapper, TbContent> implements TbContentService {

    @Autowired
    private Validator validator;

    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public Wrapper<PageVO<Content>> findPage(Integer page, Integer rows) {
        Integer pages = (page-1) * rows;
        List<Content> tbContents = baseMapper.findAllContent(pages,rows);
        PageVO<Content> pageVO = new PageVO<Content>();
        pageVO.setTotal(baseMapper.findTotal());
        pageVO.setRows(tbContents);
        return WrapMapper.ok(pageVO);
    }

    @Override
    public Wrapper<TbContent> findById(Integer id) {
        TbContent tbContent = baseMapper.selectById(id);
        if (tbContent == null){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"查询广告实体失败ID"+id);
        }
        return WrapMapper.ok(tbContent);
    }

    @Override
    public Wrapper updateByContent(TbContent tbContent) {
        if (tbContent.getStatus().equals("true")){
            tbContent.setStatus("1");
        }else {
            tbContent.setStatus("0");
        }
        BeanValidators.validateWithException(validator,tbContent,Insert.class);
        Long cId = baseMapper.selectById(tbContent.getId()).getCategoryId();
        redisTemplate.boundHashOps("content").delete(cId);
        Integer integer = this.baseMapper.updateById(tbContent);
        if (integer != 1){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"修改广告信息失败:"+tbContent);
        }
        return WrapMapper.ok();
    }

    @Override
    public Wrapper save(TbContent tbContent) {
        BeanValidators.validateWithException(validator,tbContent, Insert.class);
        if (tbContent.getStatus().equals("true")){
            tbContent.setStatus("1");
        }else {
            tbContent.setStatus("0");
        }
        Integer integer = this.baseMapper.insert(tbContent);
        redisTemplate.boundHashOps("content").delete(tbContent.getCategoryId());
        if (integer != 1){
           throw new BusinessException(ErrorCodeEnum.GL99990500,"添加广告失败："+tbContent.getTitle());
        }
        return WrapMapper.ok();
    }

    @Override
    public Wrapper deleteByContentId(Long[] longs) {
        checkNotNull(longs);
        for (Long aLong : Arrays.asList(longs)) {
            Long cid = this.baseMapper.selectById(aLong).getCategoryId();
            redisTemplate.boundHashOps("content").delete(cid);
        }
        Integer i = this.baseMapper.deleteBatchIds(Arrays.asList(longs));
        if (i != longs.length){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"删除广告失败");
        }
        return WrapMapper.ok();
    }

    @Override
    public Wrapper<List<TbContent>> findByCategoryId(Long id) {
       List<TbContent> list = (List<TbContent>) redisTemplate.boundHashOps("content").get(id);
       if (list == null){
           System.out.println("数据库查询到redis");;
           list = this.baseMapper.selectList(new QueryWrapper<TbContent>()
                   .eq("category_id",id)
                   .eq("status","1")
                   .orderBy(true,true,"sort_order"));
               redisTemplate.boundHashOps("content").put(id,list);
       }else{
           System.out.println("从redis 中取数据");
       }
        return WrapMapper.ok(list);
    }


}
