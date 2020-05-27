package com.wdy.product.service;

import com.wdy.product.dto.FileDTO;

import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author yanghongguang
 * @since 2020/1/20
 */
public interface UploadService {
    /**
     * 图片上传
     * @param fileDTO
     * @return
     */
    public String upload(FileDTO fileDTO) throws IOException;
}
