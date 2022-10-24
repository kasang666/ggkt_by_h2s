package com.h2s.ggkt.service_vod.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.h2s.ggkt.Result;
import com.h2s.ggkt.model.vod.Course;
import com.h2s.ggkt.service_vod.service.CourseService;
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
@CrossOrigin
public class CourseController {
    @Autowired
    private CourseService courseService;

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

}

