package com.wdy.obm.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.core.controller.BaseController;
import com.wdy.commons.util.wrapper.WrapMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.product.model.entity.TbBrand;
import com.wdy.product.service.TbBrandService;
import com.wdy.product.vo.BrandVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * User: yanghongguang
 * Date: 2020/1/2
 * Time: 16:35
 * Description:商品品牌前端控制器
 */
@RestController
@RequestMapping("/tbBrand")
@Api(value = "WEB - ProductBrandController", tags = "商品品牌API", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TbBrandController extends BaseController {

    @Reference(version = "1.0.0")
    private TbBrandService tbBrandService;

    @GetMapping("/selectOptionList")
    @ApiOperation(value = "查询所有品牌")
    public List<Map> selectOptionList(){
       return this.tbBrandService.selectOptionList();
    }

    @GetMapping("list")
    @ApiOperation(value = "获取所有商品品牌列表",httpMethod = "GET")
    public Wrapper<List<TbBrand>> list(){
        List<TbBrand> list = tbBrandService.selectList(new QueryWrapper<>());
        return WrapMapper.ok(list);
    }

    @CrossOrigin("*")
    @PostMapping("/search")
    @ApiOperation(value = "分页； 按条件查询",httpMethod = "POST")
    public Wrapper<PageVO<TbBrand>> search(@RequestBody(required = false)
                                               @ApiParam(name = "searchEntity" ,value = "条件")
                                                 BrandVo brand , Integer page, Integer rows){
        logger.info("分页； 按条件查询");
        return this.tbBrandService.findPage(brand,page,rows);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加新的品牌",httpMethod = "POST")
    public Wrapper add(@RequestBody TbBrand tbBrand){
        logger.info("添加新品牌");
        return this.tbBrandService.save(tbBrand);
    }

    @GetMapping("/findOne/{id}")
    @ApiOperation(value = "查询实体",httpMethod = "GET")
    public Wrapper<TbBrand> findOne(@ApiParam @PathVariable("id") Integer id){
        logger.info("查询 实体");
        return this.tbBrandService.findById(id);
    }

    @PostMapping("/update")
    @ApiOperation(value = "品牌修改",httpMethod = "POST")
    public Wrapper update(@RequestBody TbBrand brand){
        logger.info("修改品牌信息");
        return this.tbBrandService.updateByTbBrand(brand);
    }

    @GetMapping("/delete/{ids}")
    @ApiOperation(value = "删除商品品牌信息",httpMethod = "GET")
    public Wrapper delete(@ApiParam @PathVariable("ids") Long[] ids){
        logger.info("删除商品品牌");
        return this.tbBrandService.deleteTbBrandInfoById(ids);
    }
}

