package com.h2s.ggkt.service_vod.service.impl;
// -*-coding:utf-8 -*-

/*
 * File       : VodServiceImpl.java
 * Time       ：2022/11/2 18:26
 * Author     ：hhs
 * version    ：java8
 * Description：
 */

import com.h2s.ggkt.service_vod.service.VodService;
import com.h2s.ggkt.service_vod.utils.CosPropertiesUtil;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaRequest;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaResponse;
import org.springframework.stereotype.Service;


@Service
public class VodServiceImpl implements VodService {

    @Override
    public void removeVideo(String videoSourceId) {
        try{
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            Credential cred =
                    new Credential(CosPropertiesUtil.SECRET_ID,
                            CosPropertiesUtil.SECRET_KEY);
            // 实例化要请求产品的client对象,clientProfile是可选的
            VodClient client = new VodClient(cred, "");
            // 实例化一个请求对象,每个接口都会对应一个request对象
            DeleteMediaRequest req = new DeleteMediaRequest();
            req.setFileId(videoSourceId);
            // 返回的resp是一个DeleteMediaResponse的实例，与请求对象对应
            DeleteMediaResponse resp = client.DeleteMedia(req);
            // 输出json格式的字符串回包
            System.out.println(DeleteMediaResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            System.out.println(e.toString());
        }
    }
}
