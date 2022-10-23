package com.h2s.ggkt.service_vod.service.impl;
// -*-coding:utf-8 -*-

/*
 * File       : SubjectServiceImpl.java
 * Time       ：2022/10/23 20:57
 * Author     ：hhs
 * version    ：java8
 * Description：
 */

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h2s.ggkt.model.vod.Subject;
import com.h2s.ggkt.service_vod.mapper.SubjectMapper;
import com.h2s.ggkt.service_vod.service.SubjectService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    @Override
    public List<Subject> getChildSubject(Long id) {
        LambdaQueryWrapper<Subject> lqw = new LambdaQueryWrapper<>();
        lqw.eq(id != null, Subject::getParentId, id);
        List<Subject> list = list(lqw);
        list.stream().forEach(item -> item.setHasChildren(hasChildren(item.getId())));
        return list;
    }

    private boolean hasChildren(Long id) {
        LambdaQueryWrapper<Subject> lqw = new LambdaQueryWrapper<>();
        lqw.eq(id != null, Subject::getParentId, id);
        return count(lqw)>0;
    }
}
