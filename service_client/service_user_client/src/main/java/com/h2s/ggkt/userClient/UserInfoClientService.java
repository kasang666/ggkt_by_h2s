package com.h2s.ggkt.userClient;
// -*-coding:utf-8 -*-

/*
 * File       : UserClientService.java
 * Time       ：2022/11/3 11:21
 * Author     ：hhs
 * version    ：java8
 * Description：
 */


import com.h2s.ggkt.model.user.UserInfo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("service-user")
public interface UserInfoClientService {
    @GetMapping("admin/user/userInfo/{id}")
    public UserInfo getUserInfoById(@PathVariable Long id);

}
