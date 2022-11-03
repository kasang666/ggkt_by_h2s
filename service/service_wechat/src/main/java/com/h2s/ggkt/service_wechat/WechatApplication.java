package com.h2s.ggkt.service_wechat;
// -*-coding:utf-8 -*-

/*
 * File       : WechatApplication.java
 * Time       ：2022/11/3 15:22
 * Author     ：hhs
 * version    ：java8
 * Description：
 */


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@MapperScan(basePackages = "com.h2s.ggkt.service_wechat.mapper")
@EnableFeignClients("com.h2s.ggkt")
@SpringBootApplication
public class WechatApplication {
    public static void main(String[] args) {
        SpringApplication.run(WechatApplication.class, args);
    }
}
