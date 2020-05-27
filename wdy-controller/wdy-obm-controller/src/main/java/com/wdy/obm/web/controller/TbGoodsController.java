package com.wdy.obm.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.core.controller.BaseController;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.product.model.dto.Goods;
import com.wdy.product.service.TbGoodsService;
import com.wdy.product.vo.SearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商品审核前端控制器
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/12
 */
@RestController
@RequestMapping("/tbGoods")
@Api(value = "WEB - ProductTbGoodsController",tags = "商品审核API",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TbGoodsController extends BaseController {

    @Reference(version = "1.0.0")
    private TbGoodsService tbGoodsService;

    @PostMapping("/findPageAndName")
    @ApiOperation(value = "分页；条件查询",httpMethod = "POST")
    public Wrapper<PageVO<Goods>> findPage(@ApiParam(name = "searchEntity",value = "条件")
                                             @RequestBody(required = false) SearchVo searchVo,
                                           @RequestParam("page") Integer page,
                                           @RequestParam("rows") Integer rows
                                             ){
        logger.info("分页；条件查询");
        return this.tbGoodsService.findPageOrByNameSearch(page,rows,searchVo);
    }

    @GetMapping(value = "/updateStatus")
    @ApiOperation(value = "修改商品状态",httpMethod = "GET")
    public Wrapper updateStatus( Long[] ids,  String status){
        logger.info("修改商品状态 、审核通过、驳回");
        return this.tbGoodsService.updateStatus(ids,status);
    }




}
