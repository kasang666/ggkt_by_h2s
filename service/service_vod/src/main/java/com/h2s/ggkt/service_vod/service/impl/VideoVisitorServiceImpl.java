package com.h2s.ggkt.service_vod.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h2s.ggkt.model.vod.VideoVisitor;
import com.h2s.ggkt.service_vod.mapper.VideoVisitorMapper;
import com.h2s.ggkt.service_vod.service.VideoVisitorService;
import com.h2s.ggkt.vo.vod.VideoVisitorCountVo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 视频来访者记录表 服务实现类
 * </p>
 *
 * @author h2s
 * @since 2022-11-02
 */
@Service
public class VideoVisitorServiceImpl extends ServiceImpl<VideoVisitorMapper, VideoVisitor> implements VideoVisitorService {

    @Override
    public Map<String, Object> findCount(Long courseId, String startTime, String endTime) {
        List<VideoVisitorCountVo> videoVisitorVos = baseMapper.findCount(courseId, startTime, endTime);
        Map<String, Object> map = new HashMap<>();
        // 获取观看日期列表
        List<String> joinDateList = videoVisitorVos.stream().map(videoVisitorCountVo -> videoVisitorCountVo.getJoinTime().toString()).collect(Collectors.toList());
        // 获取观看用户数量列表
        List<Integer> userCountList = videoVisitorVos.stream().map(VideoVisitorCountVo::getUserCount).collect(Collectors.toList());
        map.put("xData", joinDateList);
        map.put("yData", userCountList);
        return map;
    }
}
