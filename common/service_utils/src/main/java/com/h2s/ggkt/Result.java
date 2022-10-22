package com.h2s.ggkt;
// -*-coding:utf-8 -*-

/*
 * File       : Result.java
 * Time       ：2022/10/22 20:38
 * Author     ：hhs
 * version    ：java8
 * Description：
 */


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/*
统一返回结果类
 */
@Data
@ApiModel(value = "全局统一返回结果")
public class Result<T> {

    @ApiModelProperty(value = "响应状态码")
    private Integer code;
    @ApiModelProperty(value = "响应信息")
    private String message;
    @ApiModelProperty(value = "响应数据")
    private T data;

    public Result(){}

    public static<T> Result<T> build(Integer code, String message, T data){
        Result<T> result = new Result<>();
        if (data != null){
            result.setData(data);
        }
        result.setCode(code);
        result.setMessage(message);
        return result;
    }

    /**
     * 操作成功
     * @param data  baseCategory1List
     * @param <T>
     * @return
     */
    public static<T> Result<T> success(T data){
        return build(200, "success", data);
    }

    public static<T> Result<T> success(){
        return success(null);
    }

    /**
     * 操作失败
     * @param data
     * @param <T>
     * @return
     */
    public static<T> Result<T> fail(T data){
        return build(400, "fail", data);
    }

    public static<T> Result<T> fail(){
        return fail(null);
    }

    public Result<T> message(String msg){
        this.setMessage(msg);
        return this;
    }

    public Result<T> code(Integer code){
        this.setCode(code);
        return this;
    }
}
