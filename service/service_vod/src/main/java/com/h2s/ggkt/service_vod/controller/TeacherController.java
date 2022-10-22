package com.h2s.ggkt.service_vod.controller;


import com.h2s.ggkt.model.vod.Teacher;
import com.h2s.ggkt.service_vod.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 讲师 前端控制器
 * </p>
 *
 * @author h2s
 * @since 2022-10-22
 */
@RestController
@RequestMapping("/service_vod/teachers")
public class TeacherController {

    @Autowired
    private TeacherService teacherService;

    @GetMapping
    public List<Teacher> findAll(){
        return teacherService.list();
    }

    @DeleteMapping("/{id}")
    public boolean removeById(@PathVariable Integer id){
        boolean b = teacherService.removeById(id);
        return b;
    }

}

