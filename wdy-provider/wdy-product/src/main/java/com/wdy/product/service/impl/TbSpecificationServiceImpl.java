package com.wdy.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdy.commons.base.enums.ErrorCodeEnum;
import com.wdy.commons.base.exception.BusinessException;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.util.validators.BeanValidators;
import com.wdy.commons.util.validators.Update;
import com.wdy.commons.util.wrapper.WrapMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.product.mapper.TbSpecificationMapper;
import com.wdy.product.model.entity.TbSpecification;
import com.wdy.product.service.TbSpecificationService;
import com.wdy.product.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/6
 */
@Service(version = "1.0.0")
public class TbSpecificationServiceImpl extends ServiceImpl<TbSpecificationMapper, TbSpecification> implements TbSpecificationService {
    @Autowired
    private Validator validator;

    @Override
    public List<TbSpecification> findAllTbSpecification() {
        return this.baseMapper.selectList(new QueryWrapper<TbSpecification>());
    }


    @Override
    public Wrapper<PageVO<TbSpecification>> findPage(Integer page, Integer rows, SearchVo searchVo) {
        if (searchVo == null){
            return pageQuery(page,rows);
        }
              IPage<TbSpecification> iPage =  this.baseMapper.selectPage(new Page<>(page,rows), new QueryWrapper<TbSpecification>()
                .like(StrUtil.isNotBlank(searchVo.getName()),"spec_name",searchVo.getName()));
        PageVO pageVO = new PageVO();
             pageVO.setRows(iPage.getRecords());
             pageVO.setTotal(iPage.getTotal());
        return WrapMapper.ok(pageVO);
    }

    @Override
    public Wrapper<PageVO<TbSpecification>> pageQuery(Integer page, Integer rows) {
        if (page == null || page == 0){
                page = com.wdy.commons.page.Page.PAGE_NUM;
        }
        if (rows == null || rows == 0){
            rows = com.wdy.commons.page.Page.PAGE_SIZE;
        }
        IPage<TbSpecification> tbSpecificationPageVO = this.baseMapper.selectPage(new Page<>(page,rows),new QueryWrapper<TbSpecification>());

        PageVO pageVO = new PageVO();
        pageVO.setTotal(tbSpecificationPageVO.getTotal());
        pageVO.setRows(tbSpecificationPageVO.getRecords());
        return WrapMapper.ok(pageVO);
    }

    @Override
    public TbSpecification findByName(String pojoSpecificationName) {
        return this.baseMapper.selectOne(new QueryWrapper<TbSpecification>().eq("spec_name",pojoSpecificationName));
    }

    @Override
    public Long save(TbSpecification tbSpecification) {
       Long aLong = this.baseMapper.addOptionReturnKey(tbSpecification);
        if (aLong != 1){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"添加商品规格失败");
        }
        return tbSpecification.getId();
    }

    @Override
    public void updateByTbSpecification(TbSpecification tbSpecification) {
        BeanValidators.validateWithException(validator, tbSpecification, Update.class);
            Integer integer = this.baseMapper.updateById(tbSpecification);
            if (integer != 1){
                throw new BusinessException(ErrorCodeEnum.GL99990500,"修改商品规格名称失败");
            }
    }

    @Override
    public void deleteSpecification(Long[] ids) {
       Integer integer = this.baseMapper.deleteBatchIds(Arrays.asList(ids));
       if (integer != ids.length){
           throw new BusinessException(ErrorCodeEnum.GL99990500,"删除商品规格失败");
       }
    }

    @Override
    public List<Map> selectOptionList() {
        return this.baseMapper.selectOptionList();
    }
}
