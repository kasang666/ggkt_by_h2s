package com.h2s.ggkt.service_activity.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.h2s.ggkt.model.activity.CouponUse;
import com.h2s.ggkt.model.user.UserInfo;
import com.h2s.ggkt.service_activity.mapper.CouponUseMapper;
import com.h2s.ggkt.service_activity.service.CouponUseService;
import com.h2s.ggkt.userClient.UserInfoClientService;
import com.h2s.ggkt.vo.activity.CouponUseQueryVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Autowired
    private UserInfoClientService userInfoClientService;

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
        page(pageParam, lqw);
        List<CouponUse> records = pageParam.getRecords();
        // 给每一张使用过的优惠卷设置用户信息
        records.forEach(this::setUserInfoByCouponUse);
        return pageParam;
    }

    private void setUserInfoByCouponUse(CouponUse couponUse){
        Long userId = couponUse.getUserId();
        UserInfo userInfo = userInfoClientService.getUserInfoById(userId);
        couponUse.getParam().put("nickname", userInfo.getNickName());
        couponUse.getParam().put("phone", userInfo.getPhone());
    }
}
