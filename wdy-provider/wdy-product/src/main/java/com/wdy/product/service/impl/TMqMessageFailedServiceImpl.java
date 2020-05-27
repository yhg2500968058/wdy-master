package com.wdy.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdy.product.mapper.TMqMessageFailedMapper;
import com.wdy.product.model.entity.TMqMessageFailed;
import com.wdy.product.service.TMqMessageFailedService;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/3/9
 */
@Service
public class TMqMessageFailedServiceImpl extends ServiceImpl<TMqMessageFailedMapper,TMqMessageFailed> implements TMqMessageFailedService {
}
