package com.wdy.content.mapper;

import com.wdy.commons.core.mybatis.MyMapper;
import com.wdy.seckill.model.dto.Content;
import com.wdy.seckill.model.entity.TbContent;
import org.apache.ibatis.annotations.Param;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/20
 */
@Mapper
public interface TbContentMapper extends MyMapper<TbContent> {

  List<Content> findAllContent(@Param("page")Integer page,@Param("rows") Integer rows);

  Long findTotal();
}
