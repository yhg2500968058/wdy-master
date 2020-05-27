package com.wdy.product.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.product.model.dto.GoodDTD;
import com.wdy.product.model.dto.Goods;
import com.wdy.product.model.entity.TbGoods;
import com.wdy.product.vo.SearchVo;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/12
 */
public interface TbGoodsService extends IService<TbGoods> {

    /**
     * 分页；条件查询
     * @param page
     * @param rows
     * @param searchVo
     * @return
     */
    Wrapper<PageVO<Goods>> findPageOrByNameSearch(Integer page, Integer rows, SearchVo searchVo);

    /**
     * 添加
     * @param iPage
     * @return
     */
    Wrapper<PageVO<TbGoods>> gatherSave(IPage<TbGoods> iPage);

    /**
     *商家管理 查询商家所有商品
     * @param page
     * @param rows
     * @param goods
     * @param principal
     * @return
     */
    Wrapper<PageVO<Goods>> search(Integer page, Integer rows, Goods goods, String principal);

    /**
     * 修改商品状态
     * @param ids
     * @param status
     * @return
     */
    Wrapper updateStatus(Long[] ids, String status);

    /**
     * 商家添加商品基本信息
     * @param goods
     * @return
     */
    Wrapper save(GoodDTD goods);
}
