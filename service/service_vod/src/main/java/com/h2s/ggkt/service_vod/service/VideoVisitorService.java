package com.h2s.ggkt.service_vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.h2s.ggkt.model.vod.VideoVisitor;

import java.util.Map;

/**
 * <p>
 * 视频来访者记录表 服务类
 * </p>
 *
 * @author h2s
 * @since 2022-11-02
 */
public interface VideoVisitorService extends IService<VideoVisitor> {

    Map<String, Object> findCount(Long courseId, String startTime, String endTime);
}
