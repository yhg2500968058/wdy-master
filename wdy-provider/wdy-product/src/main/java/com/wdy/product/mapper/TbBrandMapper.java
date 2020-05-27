package com.wdy.product.mapper;

import com.wdy.commons.core.mybatis.MyMapper;
import com.wdy.product.model.entity.TbBrand;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/2
 */
@Mapper
public interface TbBrandMapper extends MyMapper<TbBrand> {
    List<Map> selectBrandAll();
}
