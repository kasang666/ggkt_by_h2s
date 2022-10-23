package com.h2s.ggkt.service_vod.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.h2s.ggkt.Result;
import com.h2s.ggkt.model.vod.Teacher;
import com.h2s.ggkt.service_vod.service.TeacherService;
import com.h2s.ggkt.vo.vod.TeacherQueryVo;
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

@CrossOrigin
@Api(tags = "讲师管理接口")
@RestController
@RequestMapping(value = "/admin/vod/teacher")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @ApiOperation("查询讲师列表")
    @GetMapping
    public Result<List<Teacher>> findAll() {
        return Result.success(teacherService.list());
    }

    @ApiOperation("根据id删除讲师")
    @DeleteMapping("/{id}")
    public Result removeById(@ApiParam(value = "ID", name = "id", required = true, example = "1") @PathVariable Integer id) {
        return Result.success(teacherService.removeById(id));
    }

    @ApiOperation("批量删除讲师")
    @DeleteMapping
    public Result removeBatch(@ApiParam(value = "ids", required = true) @RequestBody List<Integer> ids){
        return Result.success(teacherService.removeByIds(ids));
    }

    @ApiOperation("修改讲师信息")
    @PutMapping("/{id}")
    public Result updateById(@ApiParam(value = "讲师id", required = true, example = "1") @PathVariable Integer id,
                             @ApiParam(value = "讲师对象", required = true) @RequestBody Teacher teacher) {
        return Result.success(teacherService.updateById(teacher));
    }

    @ApiOperation("根据id查询")
    @GetMapping("/{id}")
    public Result<Teacher> getById(@ApiParam(value = "讲师id", required = true, example = "1") @PathVariable Integer id) {
        return Result.success(teacherService.getById(id));
    }

    @ApiOperation("获取分页列表")
    @GetMapping("/{page}/{limit}")
    public Result pageList(@ApiParam(value = "page", name = "page", required = true, example = "1") @PathVariable Integer page,
                           @ApiParam(value = "limit", name = "limit", required = true, example = "10") @PathVariable Integer limit,
                           @ApiParam(value = "分页条件对象", name = "teacherQueryVO") TeacherQueryVo teacherQueryVo) {
        Page<Teacher> teacherPage = new Page<>(page, limit);
        String name = teacherQueryVo.getName();
        Integer level = teacherQueryVo.getLevel();
        String joinDateBegin = teacherQueryVo.getJoinDateBegin();
        String joinDateEnd = teacherQueryVo.getJoinDateEnd();

        LambdaQueryWrapper<Teacher> lqw = new LambdaQueryWrapper<>();
        lqw
                .like(name != null, Teacher::getName, name)
                .eq(level != null, Teacher::getLevel, level)
                .ge(joinDateBegin != null, Teacher::getJoinDate, joinDateBegin)
                .le(joinDateEnd != null, Teacher::getJoinDate, joinDateEnd);
        return Result.success(teacherService.page(teacherPage, lqw));
    }

    @ApiOperation("添加讲师")
    @PostMapping
    public Result addTeacher(@ApiParam(value = "讲师对象", name = "teacher", required = true) @RequestBody Teacher teacher) {
        teacherService.save(teacher);
        return Result.success(teacher);
    }
}

