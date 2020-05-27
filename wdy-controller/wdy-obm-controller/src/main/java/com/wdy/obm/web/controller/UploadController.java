package com.wdy.obm.web.controller;

import cn.hutool.core.date.DateTime;
import com.alibaba.dubbo.config.annotation.Reference;
import com.wdy.commons.base.enums.ErrorCodeEnum;
import com.wdy.commons.base.exception.BusinessException;
import com.wdy.commons.core.controller.BaseController;
import com.wdy.commons.util.upload.FtpUtil;
import com.wdy.commons.util.upload.IDUtils;
import com.wdy.commons.util.wrapper.WrapMapper;
import com.wdy.commons.util.wrapper.Wrapper;
import com.wdy.obm.web.config.FtpProperties;
import com.wdy.product.service.UploadService;
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

import java.io.IOException;
import java.io.InputStream;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/20
 */
@RestController
@RequestMapping(value = "/upload",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
@Api(value = "WEB - UploadController",tags = "图片上传Api",produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class UploadController extends BaseController {

    @Reference(version = "1.0.0",timeout = 6000)
    private UploadService uploadService;
    @Autowired
    private FtpProperties ftpProperties;

    @PostMapping("/uploadImage")
    @ApiOperation(value = "图片上传",httpMethod = "POST")
    public Wrapper upload(@RequestBody @ApiParam(value = "图片上传") MultipartFile file) throws IOException {
        if (file.isEmpty()){
            throw new BusinessException(ErrorCodeEnum.GL99990500,"文件不存在");
        }
        logger.info("图片上传：{}", file);

        //图片名称
        String addName = file.getOriginalFilename();
        //图片扩展名
        String kzm = addName.substring(addName.lastIndexOf("."));
        //重命名
        String newName = IDUtils.genImageName() + kzm;
        String filePath = new DateTime().toString("yyyy/MM/dd");
        InputStream in = file.getInputStream();
        //图片上传日期
        boolean flag = FtpUtil.uploadFile(ftpProperties.getAddress(),ftpProperties.getPort(),ftpProperties.getUsername(),ftpProperties.getPassword(),ftpProperties.getBasePath(),filePath,newName,in);
        if (flag){
            String stringWrapper = ftpProperties.getImageBaseUrl() +"/"+ filePath + "/" + newName;
            System.out.println(stringWrapper);
            return  WrapMapper.ok(stringWrapper);
        }
        return null;
    }
}
