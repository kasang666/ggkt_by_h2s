package com.h2s.ggkt.service_vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h2s.ggkt.model.vod.CourseDescription;
import com.h2s.ggkt.service_vod.mapper.CourseDescriptionMapper;
import com.h2s.ggkt.service_vod.service.CourseDescriptionService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程简介 服务实现类
 * </p>
 *
 * @author h2s
 * @since 2022-10-24
 */
@Service
public class CourseDescriptionServiceImpl extends ServiceImpl<CourseDescriptionMapper, CourseDescription> implements CourseDescriptionService {

    @Override
    public void removeByCourseId(Long id) {
        LambdaQueryWrapper<CourseDescription> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CourseDescription::getCourseId, id);
        removeById(id);
    }
}
