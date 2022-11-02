package com.h2s.ggkt.service_vod.controller;
// -*-coding:utf-8 -*-

/*
 * File       : UserController.java
 * Time       ：2022/10/23 10:54
 * Author     ：hhs
 * version    ：java8
 * Description：
 */


import com.h2s.ggkt.Result;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@Api(tags = "用户管理")
@RestController
@RequestMapping("admin/vod/user")
@CrossOrigin
public class UserController {
    /**
     * 登录
     * @return
     */
    @PostMapping("login")
    public Result<Map<String, Object>> login(){
        Map<String, Object> map = new HashMap<>();
        map.put("token","admin");
        return Result.success(map);
    }

    /**
     * 获取用户信息
     * @return
     */
    @GetMapping("info")
    public Result info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        return Result.success(map);
    }
    /**
     * 退出
     * @return
     */
    @PostMapping("logout")
    public Result logout(){
        return Result.success();
    }

}
