package com.h2s.ggkt.service_activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h2s.ggkt.model.activity.CouponUse;
import com.h2s.ggkt.service_activity.mapper.CouponUseMapper;
import com.h2s.ggkt.service_activity.service.CouponUseService;
import com.h2s.ggkt.vo.activity.CouponUseQueryVo;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 优惠券领用表 服务实现类
 * </p>
 *
 * @author h2s
 * @since 2022-11-03
 */
@Service
public class CouponUseServiceImpl extends ServiceImpl<CouponUseMapper, CouponUse> implements CouponUseService {

    @Override
    public IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParam, CouponUseQueryVo couponUseQueryVo) {
        Long couponId = couponUseQueryVo.getCouponId();
        String couponStatus = couponUseQueryVo.getCouponStatus();
        String getTimeBegin = couponUseQueryVo.getGetTimeBegin();
        String getTimeEnd = couponUseQueryVo.getGetTimeEnd();
        LambdaQueryWrapper<CouponUse> lqw = new LambdaQueryWrapper<>();
        lqw
                .eq(couponId!=null, CouponUse::getCouponId, couponId)
                .eq(couponStatus!=null, CouponUse::getCouponStatus, couponStatus)
                .ge(getTimeBegin!=null, CouponUse::getGetTime, getTimeBegin)
                .le(getTimeEnd!=null, CouponUse::getUsedTime, getTimeEnd);
        return page(pageParam, lqw);
    }
}
