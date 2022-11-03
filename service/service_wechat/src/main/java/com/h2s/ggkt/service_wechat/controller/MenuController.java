package com.h2s.ggkt.service_wechat.controller;


import com.h2s.ggkt.Result;
import com.h2s.ggkt.model.wechat.Menu;
import com.h2s.ggkt.service_wechat.service.MenuService;
import com.h2s.ggkt.vo.wechat.MenuVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 订单明细 订单明细 前端控制器
 * </p>
 *
 * @author h2s
 * @since 2022-11-03
 */
@Api(tags = "公众号菜单管理")
@RestController
@RequestMapping("admin/wechat/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @ApiOperation("获取菜单列表及其子菜单")
    @GetMapping("findMenuInfo")
    public Result findMenuInfo() {
        List<MenuVo> list = menuService.findMenuInfo();
        return Result.success(list);
    }

    @ApiOperation("仅获取一级菜单列表")
    @GetMapping("findOneMenuInfo")
    public Result findOneMenuInfo() {
        List<Menu> list = menuService.findMenuOneInfo();
        return Result.success(list);
    }

    @ApiOperation(value = "获取")
    @GetMapping("/{id}")
    public Result get(@PathVariable Long id) {
        Menu menu = menuService.getById(id);
        return Result.success(menu);
    }

    @ApiOperation(value = "新增")
    @PostMapping
    public Result save(@RequestBody Menu menu) {
        menuService.save(menu);
        return Result.success(null);
    }

    @ApiOperation(value = "修改")
    @PutMapping
    public Result updateById(@RequestBody Menu menu) {
        menuService.updateById(menu);
        return Result.success(null);
    }

    @ApiOperation(value = "删除")
    @DeleteMapping("/{id}")
    public Result remove(@PathVariable Long id) {
        menuService.removeById(id);
        return Result.success(null);
    }

    @ApiOperation(value = "根据id列表删除")
    @DeleteMapping("batchRemove")
    public Result batchRemove(@RequestBody List<Long> idList) {
        menuService.removeByIds(idList);
        return Result.success(null);
    }

    @ApiOperation(value = "同步菜单")
    @GetMapping("syncMenu")
    public Result createMenu() throws WxErrorException {
        menuService.syncMenu();
        return Result.success(null);
    }

    @ApiOperation(value = "删除菜单")
    @DeleteMapping("removeMenu")
    public Result removeMenu() {
        menuService.removeMenu();
        return Result.success(null);
    }
}

