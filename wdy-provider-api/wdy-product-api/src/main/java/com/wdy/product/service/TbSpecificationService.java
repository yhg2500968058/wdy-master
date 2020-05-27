package com.wdy.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.product.model.entity.TbSpecification;
import com.wdy.product.vo.SearchVo;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/6
 */
public interface TbSpecificationService extends IService<TbSpecification> {
    /**
     * 查询所有商品规格
     */
    List<TbSpecification> findAllTbSpecification();

    /**
     * 搜索；分页查询
     * @param page
     * @param rows
     * @param searchVo
     * @return
     */
    Wrapper<PageVO<TbSpecification>> findPage(Integer page, Integer rows, SearchVo searchVo);

    /**
     * 分页查询
     * @param page
     * @param rows
     * @return
     */
    Wrapper<PageVO<TbSpecification>> pageQuery(Integer page, Integer rows);

    /**
     * 查询商品规格
     * @param pojoSpecificationName
     * @return
     */
    TbSpecification findByName(String pojoSpecificationName);

    /**
     * 添加商品规格并且返回主键
     * @param pojoSpecificationName
     * @return
     */
    Long save(TbSpecification pojoSpecificationName);

    /**
     * 修改商品规格
     * @param tbSpecification
     */
    void updateByTbSpecification(TbSpecification tbSpecification);

    /**
     * 删除商品规格
     * @param ids
     */
    void deleteSpecification(Long[] ids);

    /**
     * 查询所有商品规格
     * @return
     */
    List<Map> selectOptionList();
}
