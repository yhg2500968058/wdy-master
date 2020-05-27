package com.wdy.biz.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.core.controller.BaseController;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.product.model.dto.GoodDTD;
import com.wdy.product.model.dto.Goods;
import com.wdy.product.service.TbGoodsService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * <p>
 * 商品管理控制器
 * </p>
 *
 * @author yanghongguang
 * @since 2020/3/7
 */
@RestController
@RequestMapping(value = "/tbGoods",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - TbGoodsController",tags = "商品录入API",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TbGoodsController extends BaseController {

    @Reference(version = "1.0.0")
    private TbGoodsService tbGoodsService;

    @PostMapping(value = "/search")
    @ApiOperation(value = "搜索",httpMethod = "POST")
    public Wrapper<PageVO<Goods>> search(Integer page, Integer rows,
                                         @RequestBody(required = false)
                                         @ApiParam(name = "searchEntity",value = "条件") Goods goods , Principal principal){
        logger.info("商品搜索");
        return tbGoodsService.search(page,rows,goods,principal.getName());
    }

    @PostMapping(value = "add")
    @ApiOperation(value = "添加商品",httpMethod = "POST")
    public Wrapper add(@RequestBody @ApiParam(value = "商家添加商品") GoodDTD goods,Principal principal){
        //goods.getGoods().setSellerId(principal.getName());
        goods.getGoods().setSellerId("猛字楼");
        logger.info("添加商品基本信息：{}",goods);
        return this.tbGoodsService.save(goods);
    }
}
