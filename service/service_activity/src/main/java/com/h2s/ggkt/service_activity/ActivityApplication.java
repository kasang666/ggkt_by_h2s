package com.h2s.ggkt.service_activity;
// -*-coding:utf-8 -*-

/*
 * File       : ActivityApplication.java
 * Time       ：2022/11/3 10:47
 * Author     ：hhs
 * version    ：java8
 * Description：
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.h2s.ggkt.userClient")
// 开启远程调用,如果不加com.h2s.ggkt，那么扫描的就是当前类所在包及其子包，也就是com.h2s.ggkt。service_activity，无法扫描到userInfoClientService
@SpringBootApplication
public class ActivityApplication {
    public static void main(String[] args) {
        SpringApplication.run(ActivityApplication.class, args);
    }
}
