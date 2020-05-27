package com.wdy.biz.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wdy.commons.core.controller.BaseController;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.product.service.TbTypeTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * User: yanghongguang
 * Date: 2020/3/13
 * Time: 16:21
 * Description:
 */
@RestController
@RequestMapping(value = "/tbTypeTemplate",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - TbTypeTemplateController",tags = "模板API",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TbTypeTemplateController extends BaseController {
    @Reference(version = "1.0.0")
    private TbTypeTemplateService tbTypeTemplateService;

    @GetMapping("/findOne")
    @ApiOperation(value = "查询",httpMethod = "GET")
    public Wrapper findOne( Integer id){
        logger.info("查询");
        return this.tbTypeTemplateService.findOneById(id);
    }

    @GetMapping("/findSpecList")
    @ApiOperation(value = "",httpMethod = "GET")
    public Wrapper<List<Map>> findSpecList(Long id){
        logger.info("---------------");
        return this.tbTypeTemplateService.findSpecList(id);
    }
}
