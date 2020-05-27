package com.wdy.obm.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.core.controller.BaseController;
import com.wdy.commons.util.wrapper.WrapMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.product.model.entity.TbTypeTemplate;
import com.wdy.product.service.TbTypeTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 模板管理控制器
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/8
 */
@RestController
@RequestMapping(value = "/tbTypeTemplate",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - ProductTbTypeTemplateController",tags = "模板管理",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TbTypeTemplateController extends BaseController {

    @Reference(version = "1.0.0")
    private TbTypeTemplateService tbTypeTemplateService;

    @PostMapping("/findByName")
    @ApiOperation(value = "条件查询并分页",httpMethod = "POST")
    public Wrapper<PageVO<TbTypeTemplate>> findByName(@RequestParam("page") Integer page,
                                                      @RequestParam("rows") Integer rows,
                                                      @ApiParam(name ="searchEntity",value = "条件")
                                                             String name){
        logger.info("条件查询 分页");
        PageVO<TbTypeTemplate> pageVO = this.tbTypeTemplateService.findPageByName(page,rows,name);
        return WrapMapper.ok(pageVO);
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加分类模板" ,httpMethod = "POST")
    public Wrapper save(@ApiParam(value = "添加模板") @RequestBody TbTypeTemplate tbTypeTemplate){
        logger.info("添加模板:{}",tbTypeTemplate);
        return this.tbTypeTemplateService.save(tbTypeTemplate);

    }

    @PostMapping("/delete")
    @ApiOperation(value = "删除商品类型模板", httpMethod = "POST")
    public Wrapper delete(@RequestParam Long[] ids){
        logger.info("删除模板");
        return this.tbTypeTemplateService.deleteByIds(ids);
    }

    @GetMapping("/findOne/{id}")
    @ApiOperation(value = "查询实体" ,httpMethod = "GET")
    public Wrapper<TbTypeTemplate> findOne(@PathVariable("id") Integer id){
        return this.tbTypeTemplateService.findOneById(id);
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改模板",httpMethod = "POST")
    public Wrapper update(@RequestBody TbTypeTemplate tbTypeTemplate){
        logger.info("修改模板");
        return this.tbTypeTemplateService.updateTypeTemplate(tbTypeTemplate);
    }
}
