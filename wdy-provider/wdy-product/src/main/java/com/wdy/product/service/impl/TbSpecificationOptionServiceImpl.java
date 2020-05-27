package com.wdy.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdy.commons.base.enums.ErrorCodeEnum;
import com.wdy.commons.base.exception.BusinessException;
import com.wdy.product.mapper.TbSpecificationOptionMapper;
import com.wdy.product.model.entity.TbSpecificationOption;
import com.wdy.product.service.TbSpecificationOptionService;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/7
 */
@Service(version = "1.0.0")
public class TbSpecificationOptionServiceImpl extends ServiceImpl<TbSpecificationOptionMapper, TbSpecificationOption> implements TbSpecificationOptionService {

    @Override
    public void saveOption(TbSpecificationOption tbSpecificationOption) {
        this.baseMapper.insert(tbSpecificationOption);
    }

    @Override
    public List<TbSpecificationOption> findOptionById(Long id) {

        List<TbSpecificationOption> tbSpecificationOptions = this.baseMapper.selectList(new QueryWrapper<TbSpecificationOption>().eq("spec_id",id));
        if (tbSpecificationOptions == null){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"商品规格查询失败");
        }
        return tbSpecificationOptions;

    }

    @Override
    public void deleteOptionById(Long id) {
        if (StrUtil.isEmpty(id.toString())){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"删除商品规格选项失败id为空，无法删除");
        }
       Integer integer = this.baseMapper.delete(new QueryWrapper<TbSpecificationOption>().eq("spec_id",id));
    }

    @Override
    public void deleteSpecificationOption(Long[] ids) {
        for (Long id : ids) {
        Integer integer = this.baseMapper.delete(new QueryWrapper<TbSpecificationOption>().eq("spec_id",id));
            if (integer != ids.length){
                throw new BusinessException(ErrorCodeEnum.GL99990500,"删除商品规格选项失败");
            }
        }

    }
}
