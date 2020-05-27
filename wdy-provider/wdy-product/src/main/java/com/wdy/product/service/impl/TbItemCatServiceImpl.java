package com.wdy.product.service.impl;

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
import com.wdy.product.mapper.TbItemCatMapper;
import com.wdy.product.model.entity.TbItemCat;
import com.wdy.product.service.TbItemCatService;
import com.wdy.product.vo.SearchVo;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/10
 */
@Service(version = "1.0.0")
@Transactional
public class TbItemCatServiceImpl extends ServiceImpl<TbItemCatMapper, TbItemCat> implements TbItemCatService {
    @Override
    public Wrapper<PageVO<TbItemCat>> findPage(Integer page, Integer rows, SearchVo searchVo) {
        if (page == null || page == 0){
            page = com.wdy.commons.page.Page.PAGE_NUM;
        }
        if (rows == null || rows == 0){
            rows = com.wdy.commons.page.Page.PAGE_SIZE;
        }
        IPage<TbItemCat> tbItemCatIPage = this.baseMapper.selectPage(new Page<>(page,rows),new QueryWrapper<TbItemCat>().eq("parent_id",searchVo.getName()));
        PageVO pageVO = new PageVO();
        pageVO.setRows(tbItemCatIPage.getRecords());
        pageVO.setTotal(tbItemCatIPage.getTotal());
        return WrapMapper.ok(pageVO);
    }

    @Override
    public Wrapper<TbItemCat> findById(Long id) {
        checkNotNull(id);
        TbItemCat tbItemCats =  this.baseMapper.selectById(id);
        if (tbItemCats == null){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"未找到实体");
        }
        return WrapMapper.ok(tbItemCats);
    }

    @Override
    public Wrapper save(TbItemCat tbItemCat) {
        Integer integer = this.baseMapper.insert(tbItemCat);
        if (integer != 1){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"添加商品类型失败");
        }
        return WrapMapper.ok();
    }

    @Override
    public Wrapper updateByTbItemCat(TbItemCat tbItemCat) {
        Integer integer = this.baseMapper.updateById(tbItemCat);
        if (integer != 1){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"修改商品类型失败");
        }
        return WrapMapper.ok();
    }

    @Override
    public Wrapper deleteByIds(Long[] ids) {
        Integer integer = this.baseMapper.deleteBatchIds(Arrays.asList(ids));
        if (integer != ids.length){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"删除商品类型失败");
        }
        return WrapMapper.ok();
    }

    @Override
    public Wrapper<List<TbItemCat>> findBySonId(Long[] id) {
        List<TbItemCat> tbItemCats1 = null;
        for (Long aLong : id) {
         tbItemCats1 = this.selectList(new QueryWrapper<TbItemCat>().eq("parent_id",aLong));
        if (tbItemCats1.size() == 0){
        return null;
         }
        }
        return WrapMapper.ok(tbItemCats1);
    }

    @Override
    public Wrapper<List<TbItemCat>> findByParentId(Long id) {
        List<TbItemCat> tbItemCats = this.baseMapper.selectList(new QueryWrapper<TbItemCat>().eq("parent_id", id));
        if (tbItemCats == null){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"没查到该类型");
        }
        return WrapMapper.ok(tbItemCats);
    }
}
