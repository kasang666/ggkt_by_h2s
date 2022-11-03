package com.h2s.ggkt.service_activity.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.h2s.ggkt.Result;
import com.h2s.ggkt.model.activity.CouponUse;
import com.h2s.ggkt.service_activity.service.CouponUseService;
import com.h2s.ggkt.vo.activity.CouponUseQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 优惠券领用表 前端控制器
 * </p>
 *
 * @author h2s
 * @since 2022-11-03
 */

@Api(tags = "已经使用的优惠卷管理")
@RestController
@RequestMapping("admin/activity/couponUse")
public class CouponUseController {

    @Autowired
    private CouponUseService couponUseService;

    @ApiOperation(value = "获取已经使用的优惠卷分页列表")
    @GetMapping("/{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit,
            @ApiParam(name = "couponUseVo", value = "查询对象", required = false)
                    CouponUseQueryVo couponUseQueryVo) {
        Page<CouponUse> pageParam = new Page<>(page, limit);
        IPage<CouponUse> pageModel = couponUseService.selectCouponUsePage(pageParam, couponUseQueryVo);
        return Result.success(pageModel);
    }

}

