package com.wdy.product.mapper;

import com.wdy.commons.core.mybatis.MyMapper;
import com.wdy.product.model.entity.TbSpecification;
import org.mapstruct.Mapper;

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
@Mapper
public interface TbSpecificationMapper extends MyMapper<TbSpecification> {
    //   int insert(UserInfo record);

    List<Map> selectOptionList();

    Long addOptionReturnKey(TbSpecification tbSpecification);

}
