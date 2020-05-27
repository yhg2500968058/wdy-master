package com.wdy.biz.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wdy.commons.core.controller.BaseController;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.product.model.entity.TbItemCat;
import com.wdy.product.service.TbItemCatService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/3/13
 */
@RestController
@RequestMapping(value = "/tbItemCats",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - TbItemCatController",tags = "商品录入API",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TbItemCatController extends BaseController {

    @Reference(version = "1.0.0")
    private TbItemCatService tbItemCatService;

    @GetMapping("/findByParentId")
    @ApiOperation(value = "获取商品一级分类",httpMethod = "GET")
    @ApiImplicitParams({@ApiImplicitParam(value = "商品品牌ID",paramType = "query")})
    public Wrapper<List<TbItemCat>> findByParentId( Long parentId){
        logger.info("查询所有一级分类");
        return this.tbItemCatService.findByParentId(parentId);
    }

    @GetMapping("/findOne")
    @ApiOperation(value = "",httpMethod = "GET")
    public Wrapper<TbItemCat> findOne( Long id){
        logger.info("---------------------");
        return this.tbItemCatService.findById(id);
    }
}
