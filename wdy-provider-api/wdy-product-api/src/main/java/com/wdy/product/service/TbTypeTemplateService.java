package com.wdy.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.product.model.entity.TbTypeTemplate;

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
public interface TbTypeTemplateService extends IService<TbTypeTemplate> {
    /**
     * 分页查询；条件查询
     * @param page
     * @param rows
     * @param name
     * @return
     */
    PageVO<TbTypeTemplate> findPageByName(Integer page, Integer rows, String name);

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    PageVO<TbTypeTemplate> findPage(Integer page,Integer rows);

    /**
     * 添加模板
     * @param tbTypeTemplate
     * @return
     */
    Wrapper save(TbTypeTemplate tbTypeTemplate);

    /**
     * 删除商品模板
     * @param ids
     * @return
     */
    Wrapper deleteByIds(Long[] ids);

    /**
     * 通过id查询实体
     * @param id
     * @return
     */
    Wrapper<TbTypeTemplate> findOneById(Integer id);

    /**
     * 修改商品模板
     * @param tbTypeTemplate
     * @return
     */
    Wrapper updateTypeTemplate(TbTypeTemplate tbTypeTemplate);

    /**
     *
     * @param id
     * @return
     */
    Wrapper<List<Map>> findSpecList(Long id);
}
