package com.h2s.ggkt.service_vod.service;
// -*-coding:utf-8 -*-

/*
 * File       : FileService.java
 * Time       ：2022/10/23 19:37
 * Author     ：hhs
 * version    ：java8
 * Description：
 */

import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    String upload(MultipartFile file, String module);
}
