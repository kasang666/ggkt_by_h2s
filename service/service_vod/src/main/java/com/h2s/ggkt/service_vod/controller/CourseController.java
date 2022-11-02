package com.h2s.ggkt.service_vod.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.h2s.ggkt.Result;
import com.h2s.ggkt.model.vod.Course;
import com.h2s.ggkt.service_vod.service.CourseService;
import com.h2s.ggkt.vo.vod.CourseFormVo;
import com.h2s.ggkt.vo.vod.CoursePublishVo;
import com.h2s.ggkt.vo.vod.CourseQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author h2s
 * @since 2022-10-24
 */
@Api(tags = "课程管理")
@RestController
@RequestMapping("admin/vod/course")
//@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "新增")
    @PostMapping
    public Result save(@RequestBody CourseFormVo courseFormVo) {
        Long courseId = courseService.saveCourseInfo(courseFormVo);
        return Result.success(courseId);
    }

    @ApiOperation("删除课程")
    @DeleteMapping("/{id}")
    public Result removeById(@ApiParam(value = "课程id", name = "Id", required = true) @PathVariable Long id){
        boolean result = courseService.removeCourseById(id);
        return Result.success(result);
    }

    @ApiOperation("获取课程列表")
    @GetMapping("/{page}/{limit}")
    public Result<Page<Course>> pageList(
            @ApiParam(value = "当前页码", name = "page", required = true)
            @PathVariable("page")  Integer page,
            @ApiParam(value = "每页数量", name = "limit", required = true)
            @PathVariable("limit") Integer limit,
            @ApiParam(value = "查询条件对象", name = "courseQueryVo", required = true)
            CourseQueryVo courseQueryVo){
        return Result.success(courseService.pageList(page, limit, courseQueryVo));
    }

    @ApiOperation("根据id获取课程")
    @GetMapping("/{id}")
    public Result<CourseFormVo> getCourseFormById(@ApiParam(value = "课程id", name = "id", required = true) @PathVariable Integer id){
        return Result.success(courseService.getCourseFormById(id));
    }

    @ApiOperation(value = "修改")
    @PutMapping("/{id}")
    public Result updateById(@ApiParam(value = "课程id", name = "id", required = true) @PathVariable("id") Integer id,
                             @ApiParam(value = "课程对象", name = "courseFormVo", required = true) @RequestBody CourseFormVo courseFormVo) {
        courseService.updateCourseById(id, courseFormVo);
        return Result.success();
    }

    @ApiOperation("根据id获取课程发布信息")
    @GetMapping("getCoursePublishVo/{id}")
    public Result getCoursePublishVoById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long id){
        CoursePublishVo coursePublishVo = courseService.getCoursePublishVo(id);
        return Result.success(coursePublishVo);
    }

    @ApiOperation("根据id发布课程")
    @PutMapping("publishCourseById/{id}")
    public Result publishCourseById(
            @ApiParam(value = "课程ID", required = true)
            @PathVariable Long id){

        boolean result = courseService.publishCourseById(id);
        return Result.success();
    }


}

