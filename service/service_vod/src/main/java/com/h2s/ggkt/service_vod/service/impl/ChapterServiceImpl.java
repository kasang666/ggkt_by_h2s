package com.h2s.ggkt.service_vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h2s.ggkt.model.vod.Chapter;
import com.h2s.ggkt.model.vod.Video;
import com.h2s.ggkt.service_vod.mapper.ChapterMapper;
import com.h2s.ggkt.service_vod.service.ChapterService;
import com.h2s.ggkt.service_vod.service.VideoService;
import com.h2s.ggkt.vo.vod.ChapterVo;
import com.h2s.ggkt.vo.vod.VideoVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author h2s
 * @since 2022-10-24
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

    @Autowired
    private VideoService videoService;

    /**
     * 根据courseId获取章节信息，video里面设置了一个courseId, 可以一次性将所有的视频数据全部查询出来，减少数据库压力
     * @param courseId
     * @return
     */
    @Override
    public List<ChapterVo> getNestedTreeList(Long courseId) {
        // 查询出当前课程的所有的章节信息
        LambdaQueryWrapper<Chapter> chapterLambdaQueryWrapper = new LambdaQueryWrapper<>();
        chapterLambdaQueryWrapper.eq(Chapter::getCourseId, courseId).orderByAsc(Chapter::getSort);
        List<Chapter> chapters = list(chapterLambdaQueryWrapper);

        // 查询出当前课程的所有课时， 这样可以减少数据库的查询次数
        LambdaQueryWrapper<Video> videoLambdaQueryWrapper = new LambdaQueryWrapper<>();
        videoLambdaQueryWrapper.eq(Video::getCourseId, courseId).orderByAsc(Video::getSort);
        List<Video> videos = videoService.list(videoLambdaQueryWrapper);

        List<ChapterVo> chapterVos = new ArrayList<>(chapters.size());
        chapters.forEach(chapter -> {
            ChapterVo chapterVo = new ChapterVo();
            BeanUtils.copyProperties(chapter, chapterVo);
            setChildren(chapterVo, videos);
            chapterVos.add(chapterVo);
        });
        return chapterVos;
    }

    @Override
    public void removeByCourseId(Long id) {
        LambdaQueryWrapper<Chapter> lqw = new LambdaQueryWrapper<>();
        lqw.eq(Chapter::getCourseId, id);
        remove(lqw);
    }

    /**
     * 给chapter设置children(也就是videoVos)
     * @param chapterVo
     * @param videos
     */
    private void setChildren(ChapterVo chapterVo, List<Video> videos){
        List<VideoVo> videoVos = new ArrayList<>();
        videos.forEach(video -> {
            if (chapterVo.getId().longValue() == video.getChapterId().longValue()){
                VideoVo videoVo = new VideoVo();
                BeanUtils.copyProperties(video, videoVo);
                videoVos.add(videoVo);
            }
        });
        chapterVo.setChildren(videoVos);
    }
}
