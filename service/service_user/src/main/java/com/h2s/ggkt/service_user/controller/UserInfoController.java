package com.h2s.ggkt.service_user.controller;


import com.h2s.ggkt.model.user.UserInfo;
import com.h2s.ggkt.service_user.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author h2s
 * @since 2022-11-03
 */

@Api(tags = "用户管理")
@RestController
@RequestMapping("admin/user/userInfo")
public class UserInfoController {

    @Autowired
    private UserInfoService userService;

    @ApiOperation(value = "获取")
    @GetMapping("/{id}")
    public UserInfo getById(@PathVariable Long id) {
        return userService.getById(id);
    }

}

