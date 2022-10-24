package com.h2s.ggkt.service_vod.service;
// -*-coding:utf-8 -*-

/*
 * File       : SubjectService.java
 * Time       ：2022/10/23 20:57
 * Author     ：hhs
 * version    ：java8
 * Description：
 */


import com.baomidou.mybatisplus.extension.service.IService;
import com.h2s.ggkt.model.vod.Subject;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface SubjectService extends IService<Subject> {
    List<Subject> getChildSubject(Long id);

    void exportData(HttpServletResponse response);

    void importDictData(MultipartFile file);
}
