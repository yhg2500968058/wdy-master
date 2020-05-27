package com.wdy.obm.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wdy.commons.base.enums.ErrorCodeEnum;
import com.wdy.commons.base.exception.BusinessException;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.core.controller.BaseController;
import com.wdy.commons.util.wrapper.WrapMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.product.model.entity.TbSpecification;
import com.wdy.product.model.entity.TbSpecificationOption;
import com.wdy.product.service.TbSpecificationOptionService;
import com.wdy.product.service.TbSpecificationService;
import com.wdy.product.vo.SearchVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * User: yanghongguang
 * Date: 2020/1/6
 * Time: 14:16
 * Description:规格管理控制器
 */
@RestController
@RequestMapping(value = "/tbSpecification",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - ProductTbSpecificationController",tags = "规格管理",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TbSpecificationController extends BaseController {

    @Reference(version = "1.0.0")
    private TbSpecificationService tbSpecificationService;

    @Reference(version = "1.0.0")
    private TbSpecificationOptionService tbSpecificationOptionService;

    @GetMapping("/findAllSpecification")
    @ApiOperation(value = "查询所有规格",httpMethod = "GET")
    public List<TbSpecification> findAll(){
        return this.tbSpecificationService.findAllTbSpecification();
    }

    @PostMapping("/search")
    @ApiOperation(value = "搜索；分页查询" ,httpMethod = "POST")
    public Wrapper<PageVO<TbSpecification>> search(@ApiParam(value = "分页查询")
                                                     @RequestBody(required = false) SearchVo searchVo,
                                                   Integer page, Integer rows){
        logger.info("搜索；分页查询");
        return this.tbSpecificationService.findPage(page,rows,searchVo);
    }

    @PostMapping("/add")
    @ApiOperation(value = "添加商品规格",httpMethod = "POST")
    public Wrapper add(@RequestParam String name, @RequestParam String[] optionName){
        logger.info("添加商品规格");
        TbSpecification tbSpecification = this.tbSpecificationService.findByName(name);
        if (tbSpecification != null){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"数据添加失败，已有该商品规格");
        }
        TbSpecification tbSpecification1 = new TbSpecification();
        Long id = this.tbSpecificationService.save(tbSpecification1.setSpecName(name));

        return saveOption(id,optionName);
    }

    @PostMapping("/findOptionById")
    @ApiOperation(value = "更具商品id 查询商品类型选项",httpMethod = "POSt")
    public Wrapper<List<TbSpecificationOption>> findById(@ApiParam @RequestParam("id") Long id){
        logger.info("更具商品id 查询商品类型选项");
        List<TbSpecificationOption> specificationOptions= this.tbSpecificationOptionService.findOptionById(id);
        return WrapMapper.ok(specificationOptions);
    }

    @PostMapping("/updateSpecOption")
    @ApiOperation(value = "修改商品规格",httpMethod = "POST")
    public Wrapper updateSpecOption(@RequestParam Long id,@RequestParam String name,@RequestParam String[] optionArray){
        logger.info("修改商品规格");
        TbSpecification tbSpecification = new TbSpecification();
        tbSpecification.setSpecName(name);
        tbSpecification.setId(id);
        this.tbSpecificationService.updateByTbSpecification(tbSpecification);

        this.tbSpecificationOptionService.deleteOptionById(id);

        return saveOption(id,optionArray);
    }

    @GetMapping("/delete/{ids}")
    @ApiOperation(value = "删除商品规格和商品选项",httpMethod = "GEt")
    public Wrapper delete(@PathVariable("ids") Long[] ids){
        logger.info("删除商品规格");
        this.tbSpecificationService.deleteSpecification(ids);
        logger.info("删除商品规格选项");
        this.tbSpecificationOptionService.deleteSpecificationOption(ids);
        return WrapMapper.ok();
    }

    @GetMapping("/selectOptionList")
    @ApiOperation(value = "查询所有商品规格",httpMethod = "GET")
    public List<Map> selectOptionList(){
        return this.tbSpecificationService.selectOptionList();
    }


    /**
     * 添加商品规格选项
     * @param id
     * @param options
     * @return
     */
    public Wrapper saveOption(Long id,String[] options){

        TbSpecificationOption tbSpecificationOption = new TbSpecificationOption();
        String[] strings = null;
        for (String s : options) {
            strings = s.split("-");
            tbSpecificationOption.setOptionName(strings[0]);
            tbSpecificationOption.setOrders(Integer.parseInt(strings[1]));
            tbSpecificationOption.setSpecId(id);
            this.tbSpecificationOptionService.saveOption(tbSpecificationOption);
        }
        return WrapMapper.ok();
    }

}
