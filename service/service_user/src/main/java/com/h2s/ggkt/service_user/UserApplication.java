package com.h2s.ggkt.service_user;
// -*-coding:utf-8 -*-

/*
 * File       : UserApplication.java
 * Time       ：2022/11/3 11:09
 * Author     ：hhs
 * version    ：java8
 * Description：
 */


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.h2s.ggkt.service_user.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
