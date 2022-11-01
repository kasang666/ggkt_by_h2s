package com.h2s.ggkt.service_vod.controller;


import com.h2s.ggkt.Result;
import com.h2s.ggkt.model.vod.Video;
import com.h2s.ggkt.service_vod.service.VideoService;
import com.h2s.ggkt.vo.vod.VideoVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程视频 前端控制器
 * </p>
 *
 * @author h2s
 * @since 2022-10-24
 */

@Api(tags = "课时（视频）管理")
@RestController
@RequestMapping("admin/vod/video")
@CrossOrigin
public class VideoController {

    @Autowired
    private VideoService videoService;

    @ApiOperation("新增课时")
    @PostMapping
    public Result<VideoVo> save(@Validated @ApiParam(value = "", name = "", required = true) @RequestBody Video video){
        videoService.save(video);
        VideoVo videoVo = new VideoVo();
        BeanUtils.copyProperties(video, videoVo);
        return Result.success(videoVo);
    }

    @ApiOperation("删除课时")
    @DeleteMapping("/{id}")
    public Result deleteById(@ApiParam(value = "课时id", name = "Id", required = true) @PathVariable Long id){
        videoService.removeById(id);
        return Result.success();
    }

    @ApiOperation("修改课时")
    @PutMapping("/{id}")
    public Result<VideoVo> update(
            @ApiParam(value = "课时id", name = "ID", required = true) @PathVariable Long id,
            @Validated @ApiParam(value = "", name = "", required = true) @RequestBody Video video){
        videoService.updateById(video);
        VideoVo videoVo = new VideoVo();
        BeanUtils.copyProperties(video, videoVo);
        return Result.success(videoVo);
    }

    @ApiOperation("查询课时")
    @GetMapping("/{id}")
    public Result<VideoVo> getById(@ApiParam(value = "课时id", name = "Id", required = true) @PathVariable Long id){
        Video video = videoService.getById(id);
        VideoVo videoVo = new VideoVo();
        BeanUtils.copyProperties(video, videoVo);
        return Result.success(videoVo);
    }

}

