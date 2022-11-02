package com.h2s.ggkt.service_vod.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.h2s.ggkt.model.vod.Chapter;
import com.h2s.ggkt.vo.vod.ChapterVo;

import java.util.List;

/**
 * <p>
 * 课程 服务类
 * </p>
 *
 * @author h2s
 * @since 2022-10-24
 */
public interface ChapterService extends IService<Chapter> {
    List<ChapterVo> getNestedTreeList(Long courseId);

    void removeByCourseId(Long id);
}
