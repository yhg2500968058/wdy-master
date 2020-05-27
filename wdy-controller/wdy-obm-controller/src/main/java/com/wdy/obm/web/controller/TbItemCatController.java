package com.wdy.obm.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.core.controller.BaseController;
import com.wdy.commons.util.wrapper.WrapMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.product.model.entity.TbItemCat;
import com.wdy.product.service.TbItemCatService;
import com.wdy.product.vo.SearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * User: yanghongguang
 * Date: 2020/1/10
 * Time: 16:52
 * Description:分类管理控制器
 */
@RestController
@RequestMapping("/tbItemCat")
@Api(value = "WEB - ProductTbItemCatController",tags = "分类管理",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TbItemCatController extends BaseController {

    @Reference(version = "1.0.0")
    private TbItemCatService tbItemCatService;

    @PostMapping("/findPage")
    @ApiOperation(value = "分类管理 分页",httpMethod = "POST")
    public Wrapper<PageVO<TbItemCat>> findPage(@RequestParam("page") Integer page,
                                               @RequestParam("rows") Integer rows,
                                               @RequestBody SearchVo searchVo){
        logger.info("分页查询");
        return this.tbItemCatService.findPage(page,rows,searchVo);
    }

    @GetMapping("/findById/{id}")
    @ApiOperation(value ="修改查找实列",httpMethod = "GET")
    public Wrapper<TbItemCat> findByParenId(@ApiParam @PathVariable("id") Long id){
        logger.info("查找父类目");
        return this.tbItemCatService.findById(id);
    }

    @PostMapping("/save")
    @ApiOperation(value = "添加商品类型",httpMethod = "POST")
    public Wrapper save(@RequestBody TbItemCat tbItemCat,@RequestParam("parentId") Long parentId){
        logger.info("添加商品类型");
        if (parentId != null){
            tbItemCat.setParentId(parentId);
            return this.tbItemCatService.save(tbItemCat);
        }
        return WrapMapper.ok();
    }

    @PostMapping("/update")
    @ApiOperation(value = "修改商品类型",httpMethod = "POST")
    public Wrapper update(@RequestBody TbItemCat tbItemCat){
        logger.info("修改商品类型");
        return this.tbItemCatService.updateByTbItemCat(tbItemCat);
    }

    @GetMapping("/delete/{ids}")
    @ApiOperation(value = "删除商品类型",httpMethod = "DELETE")
    public Wrapper delete(@PathVariable("ids") Long[] ids){
        logger.info("删除商品类型；ID：{}",ids);
        return this.tbItemCatService.deleteByIds(ids);
    }

    @GetMapping("/findBySonId/{id}")
    @ApiOperation(value = "删除查找该类型有没有子类",httpMethod = "GET")
    public Wrapper<List<TbItemCat>> findBySonId(@PathVariable("id") Long[] id){
        logger.info("查找子类");
        return this.tbItemCatService.findBySonId(id);
    }





}
