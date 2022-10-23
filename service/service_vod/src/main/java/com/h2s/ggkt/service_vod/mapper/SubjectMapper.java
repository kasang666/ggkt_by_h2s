package com.h2s.ggkt.service_vod.mapper;
// -*-coding:utf-8 -*-

/*
 * File       : SubjectMappper.java
 * Time       ：2022/10/23 21:06
 * Author     ：hhs
 * version    ：java8
 * Description：
 */

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.h2s.ggkt.model.vod.Subject;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SubjectMapper extends BaseMapper<Subject> {
}
