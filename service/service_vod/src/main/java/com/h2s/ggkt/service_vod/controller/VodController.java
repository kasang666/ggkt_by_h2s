package com.h2s.ggkt.service_vod.controller;
// -*-coding:utf-8 -*-

/*
 * File       : VodController.java
 * Time       ：2022/11/2 18:24
 * Author     ：hhs
 * version    ：java8
 * Description：
 */

import com.h2s.ggkt.Result;
import com.h2s.ggkt.service_vod.service.VodService;
import com.h2s.ggkt.service_vod.utils.CosPropertiesUtil;
import com.h2s.ggkt.service_vod.utils.Signature;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Api(tags = "云点播管理")
@RestController
@RequestMapping("admin/vod")
@CrossOrigin
public class VodController {

    @Autowired
    private VodService vodService;

    /**
     * 上传视频，这里的上传视频不需要将视频对象上传到服务器，只需要服务器将对应的secret_key, secret_id通过加密之后返回给前端页面，然后前端页面直接调用接口将文件上传到腾讯云
     * @return
     */
    @PostMapping("upload")
    public Result uploadVideo(){
        Signature sign = new Signature();
        // 设置 App 的云 API 密钥
        sign.setSecretId(CosPropertiesUtil.SECRET_ID);
        sign.setSecretKey(CosPropertiesUtil.SECRET_KEY);
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24 * 2); // 签名有效期：2天
        try {
            String signature = sign.getUploadSignature();
            System.out.println("signature : " + signature);
            return Result.success(signature);
        } catch (Exception e) {
            System.out.print("获取签名失败");
            e.printStackTrace();
            return Result.fail(null);
        }
    }

    //删除视频
    @DeleteMapping("remove")
    public Result removeVideo( @PathVariable String videoSourceId) {
        vodService.removeVideo(videoSourceId);
        return Result.success();
    }
}
