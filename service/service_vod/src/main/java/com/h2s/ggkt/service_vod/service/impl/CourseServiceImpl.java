package com.h2s.ggkt.service_vod.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h2s.ggkt.model.vod.Course;
import com.h2s.ggkt.model.vod.CourseDescription;
import com.h2s.ggkt.model.vod.Subject;
import com.h2s.ggkt.model.vod.Teacher;
import com.h2s.ggkt.service_vod.mapper.CourseMapper;
import com.h2s.ggkt.service_vod.service.*;
import com.h2s.ggkt.vo.vod.CourseFormVo;
import com.h2s.ggkt.vo.vod.CoursePublishVo;
import com.h2s.ggkt.vo.vod.CourseQueryVo;
import com.h2s.ggkt.vo.vod.CourseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
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

    @Autowired
    private CourseDescriptionService courseDescriptionService;

    @Autowired
    private ChapterService chapterService;

    @Autowired
    private VideoService videoService;

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
        records.forEach(this::getTeacherOrSubjectName);
        return coursePage;
    }

    @Override
    public Long saveCourseInfo(CourseFormVo courseFormVo) {
        // 保持课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo, course);
        save(course);
        // 保存详情信息
        CourseDescription courseDescription = new CourseDescription();
        courseDescription.setCourseId(course.getId());
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescriptionService.save(courseDescription);
        return course.getId();
    }

    @Override
    public CourseFormVo getCourseFormById(Integer id) {
        Course course = getById(id);
        if (course == null){
            return null;
        }
        CourseFormVo courseFormVo = new CourseFormVo();
        BeanUtils.copyProperties(course, courseFormVo);
        LambdaQueryWrapper<CourseDescription> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CourseDescription::getCourseId, id);
        CourseDescription courseDescription = courseDescriptionService.getOne(lqw);
        if (courseDescription != null){
            courseFormVo.setDescription(courseDescription.getDescription());
        }
        return courseFormVo;
    }

    //根据id修改课程信息
    @Override
    public void updateCourseById(Integer id, CourseFormVo courseFormVo) {
        //修改课程基本信息
        Course course = new Course();
        BeanUtils.copyProperties(courseFormVo, course);
        baseMapper.updateById(course);
        //修改课程详情信息
        LambdaQueryWrapper<CourseDescription> lqw = new LambdaQueryWrapper<>();
        lqw.eq(CourseDescription::getCourseId, id);
        CourseDescription courseDescription = courseDescriptionService.getOne(lqw);
        courseDescription.setDescription(courseFormVo.getDescription());
        courseDescriptionService.updateById(courseDescription);
    }

    @Override
    public CoursePublishVo getCoursePublishVo(Long id) {
        return baseMapper.selectCourseVoById(id);
    }

    @Override
    public boolean publishCourseById(Long id) {
        Course course = new Course();
        course.setId(id);
        course.setPublishTime(new Date());
        course.setStatus(1);
        return this.updateById(course);
    }

    @Override
    public boolean removeCourseById(Long id) {
        // 删除对应的章节信息
        chapterService.removeByCourseId(id);
        // 删除对应的课时信息
        videoService.removeByCourseId(id);
        // 删除对应的课程描述信息
        courseDescriptionService.removeByCourseId(id);
        // 删除课程
        return removeById(id);
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
