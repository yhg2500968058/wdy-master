package com.wdy.biz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wdy.biz.model.entity.TbSeller;
import com.wdy.biz.vo.SearchVo;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.util.wrapper.Wrapper;

/**
 * User: yanghongguang
 * Date: 2020/1/2
 * Time: 13:30
 * Description:
 */
public interface TbSellerService extends IService<TbSeller> {
    /**
     * 分页查询 ；条件查询
     * @param pageNum
     * @param pageSize
     * @return
     */
    public Wrapper<PageVO<TbSeller>> findPage(Integer pageNum, Integer pageSize, SearchVo searchVo);

    /**
     * 查询实体
     * @param id
     * @return
     */
    Wrapper<TbSeller> findOne(String id);

    /**
     * 修改商家状态通过id
     * @param id
     * @param status
     * @return
     */
    Wrapper updateBySellerId(String id, String status);

    Wrapper<PageVO<TbSeller>> listPage(Integer page,Integer rows);

    /**
     * 更具用户名查询用户信息
     * @param username
     * @return
     */
    TbSeller findByLoginName(String username);
}
