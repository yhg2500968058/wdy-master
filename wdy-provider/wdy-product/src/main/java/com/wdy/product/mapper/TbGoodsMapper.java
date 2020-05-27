package com.wdy.product.mapper;

import com.wdy.commons.core.mybatis.MyMapper;
import com.wdy.product.model.dto.Goods;
import com.wdy.product.model.entity.TbGoods;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/12
 */
@Mapper
public interface TbGoodsMapper extends MyMapper<TbGoods> {
    /**
     * 运营商后台 查询所有未审核商品
     * @param page
     * @param rows
     * @param search
     * @return
     */
    List<Goods> findPage(@Param("page") Integer page, @Param("rows") Integer rows , @Param("search") String search);

    /**
     * 查询未审核商品多少数据
     * @param longs
     * @param name
     * @return
     */
    Long findTotal(@Param("longs") Long longs,@Param("name") String name);

    /**
     * 商家后台 查询商家所有商品
     * @param page1
     * @param rows
     * @param goods
     * @param username
     * @return
     */
    List<Goods> search(@Param("longs") Integer page1,@Param("rows") Integer rows,@Param("goods") Goods goods,@Param("username") String username);

    /**
     * 商家后台 查询商家所有商品数
     * @param username
     * @param goods
     * @return
     */
    Long searchTotal(@Param("username") String username,@Param("goods") Goods goods);
}
