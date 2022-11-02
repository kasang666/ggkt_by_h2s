package com.h2s.ggkt.service_vod.controller;


import com.h2s.ggkt.Result;
import com.h2s.ggkt.service_vod.service.VideoVisitorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 前端控制器
 * </p>
 *
 * @author h2s
 * @since 2022-11-02
 */

@Api(tags = "课程观看用户管理")
@RestController
@RequestMapping("admin/vod/videoVisitor")
@CrossOrigin
public class VideoVisitorController {

    @Autowired
    private VideoVisitorService videoVisitorService;

    @ApiOperation("显示统计数据")
    @GetMapping("/{courseId}/{startTime}/{endTime}")
    public Result showChart(@ApiParam(value = "课程id", name = "courseId", required = true) @PathVariable Long courseId,
                            @ApiParam(value = "开始时间", name = "startTime") @PathVariable String startTime,
                            @ApiParam(value = "结束时间", name = "endTime") @PathVariable String endTime){
        Map<String, Object> map =  videoVisitorService.findCount(courseId, startTime, endTime);
        return Result.success(map);
    }

}

