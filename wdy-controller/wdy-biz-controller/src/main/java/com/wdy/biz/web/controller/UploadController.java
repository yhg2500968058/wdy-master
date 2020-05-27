package com.wdy.biz.web.controller;

import cn.hutool.core.date.DateTime;
import com.wdy.biz.web.config.FtpProperties;
import com.wdy.commons.base.enums.ErrorCodeEnum;
import com.wdy.commons.base.exception.BusinessException;
import com.wdy.commons.core.controller.BaseController;
import com.wdy.commons.util.upload.FtpUtil;
import com.wdy.commons.util.upload.IDUtils;
import com.wdy.commons.util.wrapper.WrapMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *  商品图片上传
 * </p>
 *
 * @author yanghongguang
 * @since 2020/3/26
 */
@RestController
@RequestMapping(value = "/upload",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - UploadController",tags = "商品图片上传Api",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UploadController extends BaseController {

    @Autowired
    private FtpProperties ftpProperties;

    @PostMapping(value = "/uploadImage")
    @ApiOperation(value = "商品图片上传",httpMethod = "POST")
    public Wrapper<String> upload(@RequestBody @ApiParam(value = "图片上传") MultipartFile file) throws Exception{
        if (file.isEmpty()){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"文件不存在");
        }
        logger.info("开始图片上传--------------------------------------------");
        String addName = file.getOriginalFilename();
        String kzm = addName.substring(addName.lastIndexOf("."));

        String newName = IDUtils.genImageName() + kzm;

        String filePath = new DateTime().toString("yyyy/MM/dd");

        boolean flag = FtpUtil.uploadFile(ftpProperties.getAddress(),ftpProperties.getPort(),ftpProperties.getUsername(),ftpProperties.getPassword(),ftpProperties.getBasePath(),filePath,newName,file.getInputStream());

        if (flag){
            String path = ftpProperties.getImageBaseUrl() + "/" + filePath + "/" + newName;
            System.out.println(path);
            return WrapMapper.ok(path);
        }else {
            return null;
        }
    }
}
