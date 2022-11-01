package com.h2s.ggkt.service_vod.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.h2s.ggkt.model.vod.Course;
import com.h2s.ggkt.vo.vod.CourseFormVo;
import com.h2s.ggkt.vo.vod.CourseQueryVo;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author h2s
 * @since 2022-10-24
 */
public interface CourseService extends IService<Course> {

    Page<Course> pageList(Integer page, Integer limit, CourseQueryVo courseQueryVo);

    Long saveCourseInfo(CourseFormVo courseFormVo);

    CourseFormVo getCourseFormById(Integer id);

    //根据id修改课程信息
    public void updateCourseById(Integer id, CourseFormVo courseFormVo);
}
