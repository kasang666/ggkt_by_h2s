package com.h2s.ggkt;
// -*-coding:utf-8 -*-

/*
 * File       : PropertiesTestApp.java
 * Time       ：2022/10/23 19:24
 * Author     ：hhs
 * version    ：java8
 * Description：
 */

import com.h2s.ggkt.service_vod.ServiceVodApplication;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.UUID;

@SpringBootTest(classes = ServiceVodApplication.class)
public class PropertiesTestApp {

    @Test
    public void dem(){
        // 文件根路径  2022/08/29
        String rootPath = LocalDate.now().toString().replace("-", "/");
        // 自定义文件名
        UUID fileName = UUID.randomUUID();
        System.out.println(rootPath + "/" + fileName +".jpg");
    }
}
