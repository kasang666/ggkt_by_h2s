package com.h2s.ggkt.service_vod.utils;
// -*-coding:utf-8 -*-

/*
 * File       : CosProperties.java
 * Time       ：2022/10/23 19:07
 * Author     ：hhs
 * version    ：java8
 * Description：
 */


import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "tencent.cos.file")
public class CosPropertiesUtil implements InitializingBean {
    private String secretId;
    private String secretKey;
    private String region;
    private String bucketName;

    public static String SECRET_ID;
    public static String SECRET_KEY;
    public static String REGION;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {
        SECRET_ID = this.secretId;
        SECRET_KEY = this.secretKey;
        REGION = this.region;
        BUCKET_NAME = this.bucketName;
    }
}
