package com.h2s.ggkt.service_vod;
// -*-coding:utf-8 -*-

/*
 * File       : ServiceVodApplication.java
 * Time       ：2022/10/22 20:03
 * Author     ：hhs
 * version    ：java8
 * Description：
 */


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.h2s.ggkt"})
public class ServiceVodApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ServiceVodApplication.class, args);
    }
}
