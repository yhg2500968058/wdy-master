package com.wdy.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.seckill.model.dto.SearchVo;
import com.wdy.seckill.model.entity.TbContentCategory;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/20
 */
public interface TbContentCategoryService extends IService<TbContentCategory> {
    /**
     * 查询所有广告类型
     * @return
     */
    Wrapper<List<TbContentCategory>> findAllCategory();

    /**
     * 查询所有广告分页
     * @param page
     * @param rows
     * @param searchVo
     * @return
     */
    Wrapper<PageVO<TbContentCategory>> findContentCategoryPage(Integer page, Integer rows, SearchVo searchVo);

    /**
     * 分页
     * @param page
     * @param rows
     * @return
     */
    Wrapper<PageVO<TbContentCategory>> findPage(Integer page,Integer rows);

    /**
     * 添加广告类型
     * @param tbContentCategory
     * @return
     */
    Wrapper save(TbContentCategory tbContentCategory);

    /**
     * 删除广告类型
     * @param ids
     * @return
     */
    Wrapper deleteByIds(Long[] ids);

    /**
     * 查询实体
     * @param id
     * @return
     */
    Wrapper<TbContentCategory> findById(Integer id);

    /**
     * 修改广告类型
     * @param tbContentCategory
     * @return
     */
    Wrapper updateByContentCategory(TbContentCategory tbContentCategory);
}
