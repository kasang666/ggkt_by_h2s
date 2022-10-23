package com.h2s.ggkt.service_vod.utils;
// -*-coding:utf-8 -*-

/*
 * File       : CosUtil.java
 * Time       ：2022/10/23 19:38
 * Author     ：hhs
 * version    ：java8
 * Description：
 */

import com.alibaba.fastjson.JSON;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.ObjectMetadata;
import com.qcloud.cos.model.PutObjectRequest;
import com.qcloud.cos.model.PutObjectResult;
import com.qcloud.cos.region.Region;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.UUID;

@Slf4j
@Component
public class CosUtil {
    public String upload(MultipartFile file, String  module) throws IOException {
        // 1 初始化用户身份信息（secretId, secretKey）。
        // SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        String secretId = CosPropertiesUtil.SECRET_ID;
        String secretKey = CosPropertiesUtil.SECRET_KEY;
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(CosPropertiesUtil.REGION);
        //ap-chongqing
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        // 获取文件流
        InputStream inputStream = file.getInputStream();

        // https://ggkt-file-by-h2s-1310090924.cos.ap-chongqing.myqcloud.com/
        // 指定文件将要存放的存储桶
        String bucketName = CosPropertiesUtil.BUCKET_NAME;
        // 指定文件上传到 COS 上的路径，即对象键。例如对象键为folder/picture.jpg，则表示将文件 picture.jpg 上传到 folder 路径下

        String name = file.getOriginalFilename();
        // 获取文件后缀
        String fileSuffix = name.substring(name.lastIndexOf("."));
        // 文件根路径  2022/08/29
        String rootPath = LocalDate.now().toString().replace("-", "/");
        // 自定义文件名
        UUID fileName = UUID.randomUUID();
        // 文件路径
        String key = module + "/" +rootPath + "/" + fileName + fileSuffix;

        ObjectMetadata metadata = new ObjectMetadata();
        // 上传文件
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, key, inputStream, metadata);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        log.info(JSON.toJSONString(putObjectResult));
        ///	https://ggkt-file-by-h2s-1310090924.cos.ap-chongqing.myqcloud.com/vod/2022/10/23/6f2817d1-2bb1-4136-902e-cb2496910511.jpg
        return "https://"+bucketName+"."+"cos"+"."+CosPropertiesUtil.REGION+".myqcloud.com"+"/"+key;
    }
}
