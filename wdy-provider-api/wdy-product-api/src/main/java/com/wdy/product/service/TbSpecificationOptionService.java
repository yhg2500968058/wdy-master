package com.wdy.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdy.product.model.entity.TbSpecificationOption;

import java.util.List;

/**
 * <p>
 * 商品规格类型
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/7
 */
public interface TbSpecificationOptionService extends IService<TbSpecificationOption> {
    /**
     * 添加商品规格类型
     * @param tbSpecificationOption
     */
    void saveOption(TbSpecificationOption tbSpecificationOption);

    /**
     * 查询商品规格类型 通过id
     * @param id
     * @return
     */
    List<TbSpecificationOption> findOptionById(Long id);

    /**
     * 通过id删除商品规格所有选项
     * @param id
     */
    void deleteOptionById(Long id);

    /**
     * 删除商品规格所有选项
     * @param ids
     */
    void deleteSpecificationOption(Long[] ids);
}
