package com.h2s.ggkt.service_vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h2s.ggkt.model.vod.Video;
import com.h2s.ggkt.service_vod.mapper.VideoMapper;
import com.h2s.ggkt.service_vod.service.VideoService;
import com.h2s.ggkt.service_vod.service.VodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * <p>
 * 课程视频 服务实现类
 * </p>
 *
 * @author h2s
 * @since 2022-10-24
 */
@Service
public class VideoServiceImpl extends ServiceImpl<VideoMapper, Video> implements VideoService {

    @Autowired
    private VodService vodService;

    @Override
    public void removeByCourseId(Long id) {
        LambdaQueryWrapper<Video> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Video::getCourseId, id);
        List<Video> list = list(lqw);
        list.forEach(video -> {
            if(video.getVideoSourceId() != null){
                vodService.removeVideo(video.getVideoSourceId());
            }
        });
        remove(lqw);
    }

    @Override
    public void removeVideoById(Long id) {
        //1 删除小节中的视频
        //根据课程id获取课程里面所有小节
        QueryWrapper<Video> wrapper = new QueryWrapper<>();
        wrapper.eq("course_id",id);
        List<Video> videoList = baseMapper.selectList(wrapper);
        //遍历获取每个小节中的视频id
        for(Video video:videoList) {
            String videoSourceId = video.getVideoSourceId();
            //如果视频id不为空，调用方法删除
            if(!StringUtils.isEmpty(videoSourceId)) {
                vodService.removeVideo(videoSourceId);
            }
        }
        //2 根据课程id删除小节
        baseMapper.delete(wrapper);
    }
}
