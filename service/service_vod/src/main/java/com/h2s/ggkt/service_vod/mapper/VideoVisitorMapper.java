package com.h2s.ggkt.service_vod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.h2s.ggkt.model.vod.VideoVisitor;
import com.h2s.ggkt.vo.vod.VideoVisitorCountVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 视频来访者记录表 Mapper 接口
 * </p>
 *
 * @author h2s
 * @since 2022-11-02
 */
public interface VideoVisitorMapper extends BaseMapper<VideoVisitor> {

    List<VideoVisitorCountVo> findCount(@Param("courseId") Long courseId, String startTime, String endTime);
}
