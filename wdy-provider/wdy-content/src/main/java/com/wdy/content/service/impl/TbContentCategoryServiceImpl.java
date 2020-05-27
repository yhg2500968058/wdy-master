package com.wdy.content.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdy.commons.base.enums.ErrorCodeEnum;
import com.wdy.commons.base.exception.BusinessException;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.util.wrapper.WrapMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.content.mapper.TbContentCategoryMapper;
import com.wdy.seckill.model.dto.SearchVo;
import com.wdy.seckill.model.entity.TbContentCategory;
import com.wdy.seckill.service.TbContentCategoryService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/20
 */
@Service(version = "1.0.0",timeout = 6000)
@Transactional
public class TbContentCategoryServiceImpl extends ServiceImpl<TbContentCategoryMapper, TbContentCategory> implements TbContentCategoryService {
    @Override
    public Wrapper<List<TbContentCategory>> findAllCategory() {
        List<TbContentCategory>tbContentCategories = this.baseMapper.selectList(new QueryWrapper<TbContentCategory>());
        return WrapMapper.ok(tbContentCategories);
    }

    @Override
    public Wrapper<PageVO<TbContentCategory>> findContentCategoryPage(Integer page, Integer rows, SearchVo searchVo) {
        
        if (searchVo.getName() == null){
            return findPage(page,rows);
        }
        IPage<TbContentCategory> tbContentCategoryIPage = this.baseMapper.selectPage(new Page<>(page,rows),
                new QueryWrapper<TbContentCategory>()
                .like(StrUtil.isNotBlank(searchVo.getName()),
                        "name",searchVo.getName()));

        PageVO<TbContentCategory> tbContentCategoryPageVO = new PageVO<TbContentCategory>();
        tbContentCategoryPageVO.setRows(tbContentCategoryIPage.getRecords());
        tbContentCategoryPageVO.setTotal(tbContentCategoryIPage.getTotal());
        return WrapMapper.ok(tbContentCategoryPageVO);
    }
    @Override
    public Wrapper<PageVO<TbContentCategory>> findPage(Integer page, Integer rows) {
       IPage<TbContentCategory> tbContentCategoryIPage = this.baseMapper.selectPage(new Page<>(page,rows),
               new QueryWrapper<TbContentCategory>());

        PageVO<TbContentCategory> tbContentCategoryPageVO = new PageVO<TbContentCategory>();
        tbContentCategoryPageVO.setRows(tbContentCategoryIPage.getRecords());
        tbContentCategoryPageVO.setTotal(tbContentCategoryIPage.getTotal());
        System.out.println(tbContentCategoryPageVO);
        return WrapMapper.ok(tbContentCategoryPageVO);
    }

    @Override
    public Wrapper save(TbContentCategory tbContentCategory) {
        Integer integer = this.baseMapper.insert(tbContentCategory);
        if (integer != 1){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"添加广告类型："+tbContentCategory.getName()+"失败");
        }
        return WrapMapper.ok();
    }

    @Override
    public Wrapper deleteByIds(Long[] ids) {
        Integer integer = this.baseMapper.deleteBatchIds(Arrays.asList(ids));
        if (ids.length != integer){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"删除广告类型失败");
        }
        return WrapMapper.ok();
    }

    @Override
    public Wrapper<TbContentCategory> findById(Integer id) {
       TbContentCategory tbContentCategory = this.baseMapper.selectById(id);
       if (tbContentCategory == null){
           throw new BusinessException(ErrorCodeEnum.GL99990500,"查询失败");
       }
        return WrapMapper.ok(tbContentCategory);
    }

    @Override
    public Wrapper updateByContentCategory(TbContentCategory tbContentCategory) {
       Integer integer = this.baseMapper.updateById(tbContentCategory);
       if (integer != 1){
           throw new BusinessException(ErrorCodeEnum.GL99990500,"修改失败");
       }
        return WrapMapper.ok();
    }
}
