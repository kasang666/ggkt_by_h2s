package com.h2s.ggkt.service_vod.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h2s.ggkt.model.vod.Chapter;
import com.h2s.ggkt.service_vod.mapper.ChapterMapper;
import com.h2s.ggkt.service_vod.service.ChapterService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 课程 服务实现类
 * </p>
 *
 * @author h2s
 * @since 2022-10-24
 */
@Service
public class ChapterServiceImpl extends ServiceImpl<ChapterMapper, Chapter> implements ChapterService {

}
