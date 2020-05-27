package com.wdy.product.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wdy.product.mapper.TbItemServiceMapper;
import com.wdy.product.model.entity.TbItem;
import com.wdy.product.service.TbItemService;

/**
 * User: yanghongguang
 * Date: 2020/3/26
 * Time: 15:37
 * Description:
 */
@Service(version = "1.0.0")
public class TbItemServiceImpl extends ServiceImpl<TbItemServiceMapper, TbItem> implements TbItemService {
}
