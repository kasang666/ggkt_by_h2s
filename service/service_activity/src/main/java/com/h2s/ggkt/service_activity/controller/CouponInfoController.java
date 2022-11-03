package com.h2s.ggkt.service_activity.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.h2s.ggkt.Result;
import com.h2s.ggkt.model.activity.CouponInfo;
import com.h2s.ggkt.model.activity.CouponUse;
import com.h2s.ggkt.service_activity.service.CouponInfoService;
import com.h2s.ggkt.service_activity.service.CouponUseService;
import com.h2s.ggkt.vo.activity.CouponUseQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 优惠券信息 前端控制器
 * </p>
 *
 * @author h2s
 * @since 2022-11-03
 */

@Api(tags = "优惠卷管理")
@RestController
@RequestMapping("admin/activity/couponInfo")
public class CouponInfoController {

    @Autowired
    private CouponInfoService couponInfoService;

    @Autowired
    private CouponUseService couponUseService;

    @ApiOperation(value = "获取优惠卷分页列表")
    @GetMapping("{page}/{limit}")
    public Result index(
            @ApiParam(name = "page", value = "当前页码", required = true)
            @PathVariable Long page,
            @ApiParam(name = "limit", value = "每页记录数", required = true)
            @PathVariable Long limit) {
        Page<CouponInfo> pageParam = new Page<>(page, limit);
        IPage<CouponInfo> pageModel = couponInfoService.page(pageParam);
        return Result.success(pageModel);
    }

    @ApiOperation(value = "获取优惠券")
    @GetMapping("/{id}")
    public Result get(@PathVariable String id) {
        CouponInfo couponInfo = couponInfoService.getById(id);
        return Result.success(couponInfo);
    }

    @ApiOperation(value = "新增优惠券")
    @PostMapping
    public Result save(@RequestBody CouponInfo couponInfo) {
        couponInfoService.save(couponInfo);
        return Result.success();
    }

    @ApiOperation(value = "修改优惠券")
    @PutMapping("/{id}")
    public Result updateById(@PathVariable Long id,
                             @RequestBody CouponInfo couponInfo) {
        couponInfoService.updateById(couponInfo);
        return Result.success();
    }

    @ApiOperation(value = "删除优惠券")
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable String id) {
        couponInfoService.removeById(id);
        return Result.success();
    }

    @ApiOperation(value="根据id列表删除优惠券")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<String> idList){
        couponInfoService.removeByIds(idList);
        return Result.success();
    }

    @ApiOperation("获取已使用的优惠卷列表")
    @GetMapping("/couponUse/{page}/{limit}")
    public Result getCouponUseList(@PathVariable Integer page,
                                   @PathVariable Integer limit,
                                   @ApiParam(value = "已使用的优惠卷查询对象", required = false) CouponUseQueryVo couponUseQueryVo){
        Page<CouponUse> usePage = new Page<>(page, limit);
        usePage = (Page<CouponUse>) couponUseService.selectCouponUsePage(usePage, couponUseQueryVo);
        return Result.success(usePage);
    }

}

