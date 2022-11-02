package com.h2s.ggkt.service_vod.controller;


import com.h2s.ggkt.Result;
import com.h2s.ggkt.model.vod.Chapter;
import com.h2s.ggkt.service_vod.service.ChapterService;
import com.h2s.ggkt.vo.vod.ChapterVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author h2s
 * @since 2022-10-24
 */

@Api(tags = "课程章节管理")
@RestController
@RequestMapping("admin/vod/chapter")
//@CrossOrigin
public class ChapterController {
    @Autowired
    private ChapterService chapterService;

    @ApiOperation("新增章节信息")
    @PostMapping
    public Result save(
            @Validated
            @ApiParam(value = "章节对象", name = "Chapter", required = true)
            @RequestBody Chapter chapter){
        chapterService.save(chapter);
        return Result.success();
    }

    @ApiOperation("修改章节信息")
    @PutMapping("/{id}")
    public Result updateChapterById(@ApiParam(value = "章节id", name = "ID", required = true) @PathVariable Long id,
                                    @Validated @ApiParam(value = "章节对象", name = "chapter", required = true) @RequestBody Chapter chapter){
        chapterService.updateById(chapter);
        return Result.success();
    }

    @ApiOperation("删除章节信息")
    @DeleteMapping("/{id}")
    public Result deleteById(@ApiParam(value = "", name = "", required = true) @PathVariable Long id){
        chapterService.removeById(id);
        return Result.success();
    }

    /**
     * 这个接口只用于修改章节时调用，所以直接返回Chapter就行了，不需要封装Video
     * @param id
     * @return
     */
    @ApiOperation("获取章节信息")
    @GetMapping("/{id}")
    public Result<Chapter> getById(@ApiParam(value = "章节id", name = "Id", required = true) @PathVariable Long id){
        return Result.success(chapterService.getById(id));
    }

    @ApiOperation("根据课程id获取章节列表")
    @GetMapping("/getListByCourseId/{courseId}")
    public Result<List<ChapterVo>> getNestedTreeList(
            @ApiParam(value = "课程id", name = "courseId", required = true)
            @PathVariable Long courseId){
        List<ChapterVo> list = this.chapterService.getNestedTreeList(courseId);
        return Result.success(list);
    }


}

