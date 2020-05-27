package com.wdy.seckill.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.seckill.model.dto.Content;
import com.wdy.seckill.model.entity.TbContent;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/19
 */
public interface TbContentService extends IService<TbContent> {
    /**
     * 查询所有并分页
     * @param page
     * @param rows
     * @return
     */
    Wrapper<PageVO<Content>> findPage(Integer page, Integer rows);

    /**
     * 查询实体通过id
     * @param id
     * @return
     */
    Wrapper<TbContent> findById(Integer id);

    /**
     * 修改广告信息
     * @param tbContent
     * @return
     */
    Wrapper updateByContent(TbContent tbContent);

    /**
     * 添加广告
     * @param tbContent
     * @return
     */
    Wrapper save(TbContent tbContent);

    /**
     * 批量删除广告
     * @param longs
     * @return
     */
    Wrapper deleteByContentId(Long[] longs);

    /**
     * 查询首页图广告
     * @param id
     * @return
     */
    Wrapper<List<TbContent>> findByCategoryId(Long id);
}
