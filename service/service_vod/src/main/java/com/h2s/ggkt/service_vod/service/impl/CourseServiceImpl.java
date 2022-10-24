package com.h2s.ggkt.service_vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h2s.ggkt.model.vod.Course;
import com.h2s.ggkt.model.vod.Subject;
import com.h2s.ggkt.model.vod.Teacher;
import com.h2s.ggkt.service_vod.mapper.CourseMapper;
import com.h2s.ggkt.service_vod.service.CourseService;
import com.h2s.ggkt.service_vod.service.SubjectService;
import com.h2s.ggkt.service_vod.service.TeacherService;
import com.h2s.ggkt.vo.vod.CourseQueryVo;
import com.h2s.ggkt.vo.vod.CourseVo;
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
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements CourseService {
    @Autowired
    private TeacherService teacherService;

    @Autowired
    private SubjectService subjectService;

    @Override
    public Page<Course> pageList(Integer page, Integer limit, CourseQueryVo courseQueryVo) {
        Page<Course> coursePage = new Page<>(page, limit);
        Long subjectId = courseQueryVo.getSubjectId();
        Long teacherId = courseQueryVo.getTeacherId();
        Long subjectParentId = courseQueryVo.getSubjectParentId();
        String title = courseQueryVo.getTitle();
        LambdaQueryWrapper<Course> lqw = new LambdaQueryWrapper<>();
        lqw
                .eq(subjectId!=null, Course::getSubjectId, subjectId)
                .eq(teacherId!=null, Course::getTeacherId, teacherId)
                .eq(subjectParentId!=null, Course::getSubjectParentId, subjectParentId)
                .like(title!=null, Course::getTitle ,title);
        page(coursePage, lqw);
        List<Course> records = coursePage.getRecords();
        records.forEach(item->{
            getTeacherOrSubjectName(item);
        });
        return coursePage;
    }

    /**
     * 根据teacherId贺SubjectId获取相应的对象然后给vo对象封装属性
     * @param course
     * @param courseVo
     */
    private void setTeacherNameAndSubjectName(Course course, CourseVo courseVo){
        Teacher teacher = teacherService.getById(course.getTeacherId());
        Subject subject = subjectService.getById(course.getSubjectId());
        Subject subject1 = subjectService.getById(course.getSubjectParentId());
        courseVo.setTeacherName(teacher.getName());
        courseVo.setSubjectTitle(subject.getTitle());
        courseVo.setSubjectParentTitle(subject1.getTitle());
    }
    /**
     * 将course的属性复制到courseVo上面, 这个方法是转换成vo对象返回给前端
     * @param course
     * @param courseVo
     */
    private void copyProperties(Course course, CourseVo courseVo){
        BeanUtils.copyProperties(course, courseVo);
        courseVo.setId(course.getId().toString());
        courseVo.setPrice(course.getPrice().toString());
        courseVo.setStatus(course.getStatus().toString());
        if(course.getPublishTime()!=null){
            courseVo.setPublishTime(course.getPublishTime().toString());
        }
        setTeacherNameAndSubjectName(course, courseVo);
    }

    /**
     * 这个方法给course设置到param里面，和上面的方法二选一即可
     * @param course
     * @return
     */
    private void getTeacherOrSubjectName(Course course) {
        //查询讲师名称
        Teacher teacher = teacherService.getById(course.getTeacherId());
        if(teacher != null) {
            course.getParam().put("teacherName",teacher.getName());
        }
        //查询分类名称
        Subject subjectOne = subjectService.getById(course.getSubjectParentId());
        if(subjectOne != null) {
            course.getParam().put("subjectParentTitle",subjectOne.getTitle());
        }
        Subject subjectTwo = subjectService.getById(course.getSubjectId());
        if(subjectTwo != null) {
            course.getParam().put("subjectTitle",subjectTwo.getTitle());
        }
    }
}
