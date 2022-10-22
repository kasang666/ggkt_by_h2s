package com.h2s.ggkt.service_vod.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.h2s.ggkt.model.vod.Teacher;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 讲师 Mapper 接口
 * </p>
 *
 * @author h2s
 * @since 2022-10-22
 */

@Mapper  // @Repository需要在Spring中配置扫描地址，然后生成Dao层的Bean才能被注入到Service层中， 而mapper注解因为有xml文件，所以不用scan
public interface TeacherMapper extends BaseMapper<Teacher> {

}
