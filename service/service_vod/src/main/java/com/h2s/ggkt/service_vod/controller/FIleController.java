package com.h2s.ggkt.service_vod.controller;
// -*-coding:utf-8 -*-

/*
 * File       : FIleController.java
 * Time       ：2022/10/23 20:07
 * Author     ：hhs
 * version    ：java8
 * Description：
 */


import com.h2s.ggkt.Result;
import com.h2s.ggkt.service_vod.service.FileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

//@CrossOrigin
@Api(tags = "文件管理接口")
@RestController
@RequestMapping("admin/vod/file")
public class FIleController {

    @Autowired
    private FileService fileService;

    @ApiOperation("文件上传")
    @PostMapping("/upload")
    public Result<String> upload(@ApiParam(value = "文件对象", name = "file", required = true)
                                 @RequestParam("file")MultipartFile file,
                                 @ApiParam(value = "文件所属模块", name = "module", required = true)
                                 @RequestParam("module") String module){
        String url = fileService.upload(file, module);
        return Result.success(url).message("文件上传成功！");
    }

}
