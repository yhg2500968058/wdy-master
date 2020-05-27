package com.wdy.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdy.product.mapper.TbGoodsDescMapper;
import com.wdy.product.model.entity.TbGoodsDesc;
import com.wdy.product.service.TbGoodsDescService;

/**
 * User: yanghongguang
 * Date: 2020/3/26
 * Time: 14:47
 * Description:
 */
@Service(version = "1.0.0")
public class TbGoodsDescServiceImpl extends ServiceImpl<TbGoodsDescMapper, TbGoodsDesc> implements TbGoodsDescService {
}
