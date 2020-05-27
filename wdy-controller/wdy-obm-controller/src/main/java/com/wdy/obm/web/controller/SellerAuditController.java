package com.wdy.obm.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wdy.biz.model.entity.TbSeller;
import com.wdy.biz.service.TbSellerService;
import com.wdy.biz.vo.SearchVo;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.core.controller.BaseController;
import com.wdy.commons.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * 商家审核前端控制器
 */
@RestController
@RequestMapping("/sellerAudit")
@Api(value = "WEB - ProductSellerAuditController",tags = "商家审核", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class SellerAuditController extends BaseController {

    @Reference(version = "1.0.0")
    public TbSellerService tbSellerService;

    @PostMapping("/findPage")
    @ApiOperation(value = "查询所有商家",httpMethod = "POST")
    public Wrapper<PageVO<TbSeller>> findPage(@RequestParam("page") Integer page,
                                              @RequestParam("rows") Integer rows,
                                              @RequestBody(required = false) @ApiParam( name="searchEntity", value="条件") SearchVo searchVo){
        logger.info("分页查询 品牌列表");
        return this.tbSellerService.findPage(page,rows,searchVo);
    }

    @GetMapping("/findOne")
    @ApiOperation(value = "查询实体",httpMethod = "GET")
    public Wrapper<TbSeller> findOnt( String id){
        logger.info("查询实体");
        return this.tbSellerService.findOne(id);
    }

    @PostMapping("/UpdateSeller")
    @ApiOperation(value = "修改商家状态",httpMethod = "POST")
    public Wrapper update(@RequestParam("id") String id,@RequestParam("status") String status){
        logger.info("修改商家状态");
        return this.tbSellerService.updateBySellerId(id,status);
    }


}
