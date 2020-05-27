package com.wdy.seckill.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.core.controller.BaseController;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.seckill.model.dto.SearchVo;
import com.wdy.seckill.model.entity.TbContentCategory;
import com.wdy.seckill.service.TbContentCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/20
 */
@RestController
@RequestMapping("/tbContentCategory")
@Api(value = "WEB - TbContentCategoryController",tags = "广告类型管理",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TbContentCategoryController extends BaseController {

    @Reference(version = "1.0.0")
    private TbContentCategoryService tbContentCategoryService;

    @GetMapping("/findAllCategory")
    @ApiOperation(value = "查询所有广告类型",httpMethod = "GET")
    public Wrapper<List<TbContentCategory>> findAllCategory(){
        logger.info("查询所有广告类型");
        return this.tbContentCategoryService.findAllCategory();
    }

    @PostMapping("/search")
    @ApiOperation(value = "分页",httpMethod = "POST")
    public Wrapper<PageVO<TbContentCategory>> search(@ApiParam(name = "searchEntity",value = "条件")
                                                         @RequestBody(required = false) SearchVo searchVo,
                                                     @RequestParam("page") Integer page,
                                                     @RequestParam("rows") Integer rows){
        logger.info("广告分类信息查询");
        return this.tbContentCategoryService.findContentCategoryPage(page,rows,searchVo);
    }
    @PostMapping("/save")
    @ApiOperation(value = "添加广告类型",httpMethod = "POST")
    public Wrapper save(@RequestBody TbContentCategory tbContentCategory){
        logger.info("添加广告");
        return this.tbContentCategoryService.save(tbContentCategory);
    }
    @DeleteMapping("/delete/{ids}")
    public Wrapper delete(@PathVariable("ids") Long[] ids){
        logger.info("删除广告");
        return this.tbContentCategoryService.deleteByIds(ids);
    }
    @GetMapping("/findById/{id}")
    @ApiOperation(value = "通过id查询实体",httpMethod = "GET")
    public Wrapper<TbContentCategory> findById(@PathVariable("id") Integer id){
        logger.info("通过id查询实体");
        return this.tbContentCategoryService.findById(id);
    }

    @PutMapping("/update")
    @ApiOperation(value = "修改广告类型",httpMethod = "PUT")
    public Wrapper update(@RequestBody TbContentCategory tbContentCategory){
        logger.info("修改广告类型");
        return this.tbContentCategoryService.updateByContentCategory(tbContentCategory);
    }
}
