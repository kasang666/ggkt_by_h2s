package com.h2s.ggkt.service_order;
// -*-coding:utf-8 -*-

/*
 * File       : OrderApplication.java
 * Time       ：2022/11/3 9:18
 * Author     ：hhs
 * version    ：java8
 * Description：
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient  // 可省略
public class OrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderApplication.class, args);
    }
}
