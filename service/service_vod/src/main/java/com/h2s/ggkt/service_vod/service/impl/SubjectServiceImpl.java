package com.h2s.ggkt.service_vod.service.impl;
// -*-coding:utf-8 -*-

/*
 * File       : SubjectServiceImpl.java
 * Time       ：2022/10/23 20:57
 * Author     ：hhs
 * version    ：java8
 * Description：
 */

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h2s.ggkt.model.vod.Subject;
import com.h2s.ggkt.service_vod.listener.SubjectListener;
import com.h2s.ggkt.service_vod.mapper.SubjectMapper;
import com.h2s.ggkt.service_vod.service.SubjectService;
import com.h2s.ggkt.vo.vod.SubjectEeVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {
    @Autowired
    private SubjectListener subjectListener;

    @Override
    public List<Subject> getChildSubject(Long id) {
        LambdaQueryWrapper<Subject> lqw = new LambdaQueryWrapper<>();
        lqw.eq(id != null, Subject::getParentId, id);
        List<Subject> list = list(lqw);
        list.stream().forEach(item -> item.setHasChildren(hasChildren(item.getId())));
        return list;
    }

    @Override
    public void exportData(HttpServletResponse response) {
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            // 这里URLEncoder.encode可以防止中文乱码 当然和easyexcel没有关系
            String fileName = URLEncoder.encode("课程分类", "UTF-8");
            response.setHeader("Content-disposition", "attachment;filename="+ fileName + ".xlsx");
            List<Subject> dictList = baseMapper.selectList(null);
            List<SubjectEeVo> dictVoList = new ArrayList<>(dictList.size());
            for(Subject dict : dictList) {
                SubjectEeVo dictVo = new SubjectEeVo();
                BeanUtils.copyProperties(dict,dictVo);
                dictVoList.add(dictVo);
            }
            EasyExcel.write(response.getOutputStream(), SubjectEeVo.class).sheet("课程分类").doWrite(dictVoList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void importDictData(MultipartFile file) {
        try {
            EasyExcel.read(file.getInputStream(),
                    SubjectEeVo.class,subjectListener).sheet().doRead();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private boolean hasChildren(Long id) {
        LambdaQueryWrapper<Subject> lqw = new LambdaQueryWrapper<>();
        lqw.eq(id != null, Subject::getParentId, id);
        return count(lqw)>0;
    }
}
