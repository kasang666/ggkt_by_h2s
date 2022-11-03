package com.h2s.ggkt.service_activity.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.h2s.ggkt.model.activity.CouponUse;
import com.h2s.ggkt.vo.activity.CouponUseQueryVo;

/**
 * <p>
 * 优惠券领用表 服务类
 * </p>
 *
 * @author h2s
 * @since 2022-11-03
 */
public interface CouponUseService extends IService<CouponUse> {

    IPage<CouponUse> selectCouponUsePage(Page<CouponUse> pageParam, CouponUseQueryVo couponUseQueryVo);

}
