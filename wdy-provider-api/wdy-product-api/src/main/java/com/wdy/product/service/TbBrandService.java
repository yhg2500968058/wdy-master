package com.wdy.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.util.wrapper.WrapMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.product.model.entity.TbBrand;
import com.wdy.product.vo.BrandVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *商品
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/2
 */
public interface TbBrandService extends IService<TbBrand> {
    /**
     * 获取所有商品品牌列表
     * @return
     */
    WrapMapper list();
    /**
     * 分页； 按条件查询
     * @param brand
     * @param page
     * @param rows
     * @return
     */
    Wrapper<PageVO<TbBrand>> findPage(BrandVo brand, Integer page, Integer rows);

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    Wrapper<PageVO<TbBrand>> listPageVo(Integer page, Integer rows);

    /**
     * 添加新的品牌
     * @param tbBrand
     */
    Wrapper save(TbBrand tbBrand);

    /**
     * 更具品牌id查询实体
     * @param id
     * @return
     */
    Wrapper<TbBrand> findById(Integer id);

    /**
     * 修改品牌信息
     * @param brand
     * @return
     */
    Wrapper updateByTbBrand(TbBrand brand);

    /**
     * 删除商品品牌信息
     * @param ids
     * @return
     */
    Wrapper deleteTbBrandInfoById(Long[] ids);

    /**
     * 查询所有品牌
     * @return
     */
    List<Map> selectOptionList();
}
