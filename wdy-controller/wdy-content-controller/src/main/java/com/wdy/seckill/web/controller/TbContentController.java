package com.wdy.seckill.web.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wdy.commons.base.vo.PageVO;
import com.wdy.commons.core.controller.BaseController;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.seckill.model.dto.Content;
import com.wdy.seckill.model.entity.TbContent;
import com.wdy.seckill.service.TbContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 广告前端控制器
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/19
 */
@RestController
@RequestMapping("/tbContent")
@Api(value = "WEB - ProductTbContentController",tags = "广告管理",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class TbContentController extends BaseController {

    @Reference(version = "1.0.0")
    private TbContentService tbContentService;

    @PostMapping("/findAllContentPage")
    @ApiOperation(value = "查询所有广告",httpMethod = "POST")
    public Wrapper<PageVO<Content>> findAllPage(@RequestParam("page")Integer page,
                                                @RequestParam("rows") Integer rows){
        logger.info("查询出所有的广告");
        return this.tbContentService.findPage(page,rows);
    }
    @GetMapping("/findById/{id}")
    @ApiOperation(value = "查询实体",httpMethod = "GET")
    public Wrapper<TbContent> findById(@PathVariable("id") Integer id){
        logger.info("更具id查询实体：{}",id);
        return this.tbContentService.findById(id);
    }
    @PutMapping("/updateById")
    @ApiOperation(value = "修改广告信息",httpMethod = "PUT")
    public Wrapper findById(@RequestBody TbContent tbContent){
        logger.info("修改广告信息标题：{}",tbContent.getTitle());
        return this.tbContentService.updateByContent(tbContent);
    }

    @PostMapping("/addContent")
    @ApiOperation(value = "添加广告",httpMethod = "POST")
    public Wrapper save(@ApiParam(value = "添加") @RequestBody TbContent tbContent){
        logger.info("添加广告：{}",tbContent.getTitle());
        return this.tbContentService.save(tbContent);
    }
    @DeleteMapping("/delete/{ids}")
    @ApiOperation(value = "删除广告",httpMethod = "DELETE")
    public Wrapper delete(@ApiParam("删除广告") @PathVariable("ids") Long[] longs){
        logger.info("删除广告id：");
        return this.tbContentService.deleteByContentId(longs);
    }

    @GetMapping("/findByCategoryId")
    @ApiOperation(value = "首页广告轮播图",httpMethod = "GET")
    public Wrapper<List<TbContent>> findByCategoryId(Long id){
        logger.info("查询首页广告图");
        return this.tbContentService.findByCategoryId(id);
    }


}
