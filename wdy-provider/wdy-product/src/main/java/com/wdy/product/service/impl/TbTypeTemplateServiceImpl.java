package com.wdy.product.service.impl;

import cn.hutool.core.util.StrUtil;
import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdy.commons.base.enums.ErrorCodeEnum;
import com.wdy.commons.base.exception.BusinessException;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.util.wrapper.WrapMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.product.mapper.TbTypeTemplateMapper;
import com.wdy.product.model.entity.TbSpecificationOption;
import com.wdy.product.model.entity.TbTypeTemplate;
import com.wdy.product.service.TbSpecificationOptionService;
import com.wdy.product.service.TbTypeTemplateService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/8
 */
@Service(version = "1.0.0")
public class TbTypeTemplateServiceImpl extends ServiceImpl<TbTypeTemplateMapper, TbTypeTemplate> implements TbTypeTemplateService {

    @Autowired
    private TbSpecificationOptionService tbSpecificationOptionService;

    @Override
    public PageVO<TbTypeTemplate> findPageByName(Integer page, Integer rows, String name) {
        if (name == null){
           return findPage(page,rows);
        }
        if (page == null || page == 0){
            page = com.wdy.commons.page.Page.PAGE_NUM;
        }
        if (rows == null || rows == 0){
            rows = com.wdy.commons.page.Page.PAGE_SIZE;
        }
        IPage<TbTypeTemplate> tbTypeTemplateIPage = this.baseMapper.selectPage(new Page<>(page,rows),
                new QueryWrapper<TbTypeTemplate>().like(StrUtil.isNotBlank(name),
                        "name",name));
        PageVO pageVO = new PageVO();
        pageVO.setRows(tbTypeTemplateIPage.getRecords());
        pageVO.setTotal(tbTypeTemplateIPage.getTotal());
        return pageVO;

    }

    @Override
    public PageVO<TbTypeTemplate> findPage(Integer page, Integer rows) {
        if (page == null || page == 0){
            page = com.wdy.commons.page.Page.PAGE_NUM;
        }
        if (rows == null || rows == 0){
            rows = com.wdy.commons.page.Page.PAGE_SIZE;
        }
        IPage<TbTypeTemplate> tbTypeTemplatePageVO = this.baseMapper.selectPage(new Page<>(page,rows),new QueryWrapper<TbTypeTemplate>());
        PageVO pageVO = new PageVO();
        pageVO.setTotal(tbTypeTemplatePageVO.getTotal());
        pageVO.setRows(tbTypeTemplatePageVO.getRecords());
        return pageVO;
    }

    @Override
    public Wrapper save(TbTypeTemplate tbTypeTemplate) {
        Integer integer = this.baseMapper.insert(tbTypeTemplate);
        if (integer != 1){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"添加模板失败");
        }
        return WrapMapper.ok();
    }

    @Override
    public Wrapper deleteByIds(Long[] ids) {
        Integer integer = this.baseMapper.deleteBatchIds(Arrays.asList(ids));
        if (integer != ids.length){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"商品模板删除失败");
        }
        return WrapMapper.ok();
    }

    @Override
    public Wrapper<TbTypeTemplate> findOneById(Integer id) {
        TbTypeTemplate tbTypeTemplate = this.baseMapper.selectOne(new QueryWrapper<TbTypeTemplate>().eq("id",id));
        if (tbTypeTemplate == null){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"通过id没有查询到该模板");
        }
        return WrapMapper.ok(tbTypeTemplate);

    }

    @Override
    public Wrapper updateTypeTemplate(TbTypeTemplate tbTypeTemplate) {
        Integer integer = this.baseMapper.updateById(tbTypeTemplate);
        if (integer != 1){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"修改模板失败");
        }
        return WrapMapper.ok();
    }

    @Override
    public Wrapper<List<Map>> findSpecList(Long id) {

        TbTypeTemplate tbTypeTemplates = this.baseMapper.selectOne(new QueryWrapper<TbTypeTemplate>().eq("id", id));

        String specIds = tbTypeTemplates.getSpecIds();

        List<Map> list = JSON.parseArray(specIds,Map.class);

        for (Map map1 : list) {
            List<TbSpecificationOption> tbSpecificationOptions = this.tbSpecificationOptionService.selectList(new QueryWrapper<TbSpecificationOption>().eq("spec_id",map1.get("id")));

            map1.put("options",tbSpecificationOptions);
        }

        return WrapMapper.ok(list);
    }
}
