package com.h2s.ggkt.service_vod.controller;


import com.h2s.ggkt.Result;
import com.h2s.ggkt.model.vod.Teacher;
import com.h2s.ggkt.service_vod.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author h2s
 * @since 2022-10-22
 */

@Api(tags = "讲师管理接口")
@RestController
@RequestMapping(value="/admin/vod/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("查询讲师列表")
    @GetMapping
    public Result<List<Teacher>> findAll(){
        return Result.success(teacherService.list());
    }

    @ApiOperation("根据id删除讲师")
    @DeleteMapping("/{id}")
    public Result removeById(@ApiParam(value = "ID", name = "id", required = true, example = "1") @PathVariable Integer id){
        return Result.success(teacherService.removeById(id));
    }
}

