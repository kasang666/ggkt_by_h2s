package com.h2s.ggkt.service_vod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.h2s.ggkt.model.vod.Course;
import com.h2s.ggkt.vo.vod.CoursePublishVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 课程 Mapper 接口
 * </p>
 *
 * @author h2s
 * @since 2022-10-24
 */

@Mapper
public interface CourseMapper extends BaseMapper<Course> {

    CoursePublishVo selectCourseVoById(Long id);
}
