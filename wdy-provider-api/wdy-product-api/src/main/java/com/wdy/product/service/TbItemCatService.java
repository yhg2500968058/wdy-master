package com.wdy.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.product.model.entity.TbItemCat;
import com.wdy.product.vo.SearchVo;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/10
 */
public interface TbItemCatService extends IService<TbItemCat> {
    /**
     * 分页管理 ；分页
     * @param page
     * @param rows
     * @return
     */
    Wrapper<PageVO<TbItemCat>> findPage(Integer page, Integer rows, SearchVo searchVo);

    /**
     * 通过id查找实体
     * @param id
     * @return
     */
    Wrapper<TbItemCat> findById(Long id);

    /**
     * 添加商品类型
     * @param tbItemCat
     * @return
     */
    Wrapper save(TbItemCat tbItemCat);

    /**
     * 修改商品类型
     * @param tbItemCat
     * @return
     */
    Wrapper updateByTbItemCat(TbItemCat tbItemCat);

    /**
     * 删除商品类型
     * @param ids
     * @return
     */
    Wrapper deleteByIds(Long[] ids);

    /**
     * 查找子类
     * @param id
     * @return
     */
    Wrapper<List<TbItemCat>> findBySonId(Long[] id);

    /**
     * 通过id查找类型
     * @param id
     * @return
     */
    Wrapper<List<TbItemCat>> findByParentId(Long id);
}
