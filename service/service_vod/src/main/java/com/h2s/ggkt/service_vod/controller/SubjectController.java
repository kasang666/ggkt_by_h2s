package com.h2s.ggkt.service_vod.controller;
// -*-coding:utf-8 -*-

/*
 * File       : SubjectController.java
 * Time       ：2022/10/23 20:52
 * Author     ：hhs
 * version    ：java8
 * Description：
 */


import com.h2s.ggkt.Result;
import com.h2s.ggkt.model.vod.Subject;
import com.h2s.ggkt.service_vod.service.SubjectService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Api(tags = "课程分类管理")
@RestController
@RequestMapping("admin/vod/subject")
//@CrossOrigin
public class SubjectController {

    @Autowired
    private SubjectService subjectService;

    @ApiOperation("获取子课程列表")
    @GetMapping("getChildSubject/{id}")
    public Result<List<Subject>> getChildSubject(@ApiParam(value = "父课程id", name = "id", required = true)
                                                 @PathVariable("id") Long id){
        return Result.success(subjectService.getChildSubject(id));
    }

    @ApiOperation(value="导出")
    @GetMapping(value = "/exportData")
    public void exportData(HttpServletResponse response) {
        subjectService.exportData(response);
    }

    @ApiOperation(value = "导入")
    @PostMapping("importData")
    public Result importData(MultipartFile file) {
        subjectService.importDictData(file);
        return Result.success();
    }
}
