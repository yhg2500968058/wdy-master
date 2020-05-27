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
import com.wdy.product.mapper.TbBrandMapper;
import com.wdy.product.model.entity.TbBrand;
import com.wdy.product.service.TbBrandService;
import com.wdy.product.vo.BrandVo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import javax.validation.Validator;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *商品品牌服务
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/2
 */
@Service(version = "1.0.0",timeout = 6000)
@Transactional
public class TbBrandServiceImpl extends ServiceImpl<TbBrandMapper,TbBrand> implements TbBrandService {

    @Autowired
    private Validator validator;

    @Override
    public WrapMapper list() {
       return null;
    }

    /**
     * 条件分页查询
     * @param brand
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Wrapper<PageVO<TbBrand>> findPage(BrandVo brand, Integer page, Integer rows) {
        if (brand == null){
            return listPageVo(page,rows);
        }
       IPage<TbBrand> iPage = this.baseMapper.selectPage(new Page<>(page,rows), new QueryWrapper<TbBrand>()
               .like(StrUtil.isNotBlank(brand.getName()), "name",brand.getName())
               .eq(StrUtil.isNotBlank(brand.getFirstChar()), "first_char",brand.getFirstChar()));
        PageVO pageVO = new PageVO();
        pageVO.setRows(iPage.getRecords());
        pageVO.setTotal(iPage.getTotal());
        return WrapMapper.ok(pageVO);
    }

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    @Override
    public Wrapper<PageVO<TbBrand>> listPageVo(Integer page, Integer rows) {
        if (page == null || page == 0){
            page = com.wdy.commons.page.Page.PAGE_NUM;
        }
        if (rows == null || rows == 0){
            rows = com.wdy.commons.page.Page.PAGE_SIZE;
        }
        IPage<TbBrand> page1= this.baseMapper.selectPage(new Page<>(page,rows),new QueryWrapper<TbBrand>());
        PageVO pageVO = new PageVO();
        pageVO.setRows(page1.getRecords())     ;
        pageVO.setTotal(page1.getTotal());     ;
        return WrapMapper.ok(pageVO);
    }

    /**
     * 添加新的品牌
     * @param tbBrand
     */
    @Override
    public Wrapper save(TbBrand tbBrand) {
       Integer integer = this.baseMapper.insert(tbBrand);
       if (integer != 1){
           throw new BusinessException(ErrorCodeEnum.GL99990500,"添加商品品牌失败");
       }
       return WrapMapper.ok();
    }

    /**
     * 更具id查询
     * @param id
     * @return
     */
    @Override
    public Wrapper<TbBrand> findById(Integer id) {
        TbBrand tbBrand = this.baseMapper.selectById(id);
        if (tbBrand == null){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"未更具id查找到品牌信息");
        }
        return WrapMapper.ok(tbBrand);
    }

    /**
     * 修改品牌信息
     * @param brand
     * @return
     */
    @Override
    public Wrapper updateByTbBrand(TbBrand brand) {

        BeanValidators.validateWithException(validator, brand, Update.class);
        Integer integer = this.baseMapper.updateById(brand);
        if (integer != 1){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"商品品牌修改失败");
        }
        return WrapMapper.ok();
    }

    @Override
    public Wrapper deleteTbBrandInfoById(Long[] ids) {
        Integer integer = this.baseMapper.deleteBatchIds(Arrays.asList(ids));
        if (integer != ids.length){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"商品品牌删除失败");
        }
        return WrapMapper.ok();
    }

    @Override
    public List<Map> selectOptionList() {
        return this.baseMapper.selectBrandAll();

    }
}
