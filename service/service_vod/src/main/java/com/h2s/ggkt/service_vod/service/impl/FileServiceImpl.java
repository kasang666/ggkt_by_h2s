package com.h2s.ggkt.service_vod.service.impl;
// -*-coding:utf-8 -*-

/*
 * File       : FileServiceImpl.java
 * Time       ：2022/10/23 19:37
 * Author     ：hhs
 * version    ：java8
 * Description：
 */

import com.h2s.ggkt.BusinessException;
import com.h2s.ggkt.service_vod.service.FileService;
import com.h2s.ggkt.service_vod.utils.CosUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class FileServiceImpl implements FileService {
    @Autowired
    private CosUtil cosUtil;

    @Override
    public String upload(MultipartFile file, String module) {
        try {
            String url = cosUtil.upload(file, module);
            return url;
        } catch (IOException e) {
            throw new BusinessException("文件上传失败！");
        }
    }
}
