package com.wdy.product.service.impl;

import cn.hutool.core.date.DateTime;
import com.alibaba.dubbo.config.annotation.Service;
import com.wdy.commons.util.upload.FtpUtil;
import com.wdy.commons.util.upload.IDUtils;
import com.wdy.product.config.FtpProperties;
import com.wdy.product.dto.FileDTO;
import com.wdy.product.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.io.InputStream;

//import com.wdy.commons.util.upload.FtpUtil;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/20
 */
@Service(version = "1.0.0")
@Transactional
public class UploadServiceImpl implements UploadService {

    @Autowired
    private FtpProperties ftpProperties;

    @Override
    public String upload(FileDTO fileDTO ) throws IOException {
        //图片名称
        String addName = fileDTO.getName();
        //图片扩展名
        String kzm = addName.substring(addName.lastIndexOf("."));
        //重命名
        String newName = IDUtils.genImageName() + kzm;
        //FtpUtil ftpUtil = new FtpUtil(ftpProperties.getAddress(), ftpProperties.getPort(), ftpProperties.getUsername(), ftpProperties.getPassword(), ftpProperties.getBasePath());
        String filePath = new DateTime().toString("yyyy/MM/dd");
        InputStream in = fileDTO.getInputStream();
        //boolean flag = ftpUtil.uploadFileToFtp(filePath,newName,in);
        //图片上传日期
        boolean flag = FtpUtil.uploadFile(ftpProperties.getAddress(),ftpProperties.getPort(),ftpProperties.getUsername(),ftpProperties.getPassword(),ftpProperties.getBasePath(),filePath,newName,in);

        if (flag){
            String url = ftpProperties.getImageBaeUrl() +"/"+ filePath + "/" + newName;
            return url;
        }
        return null;
    }
}
