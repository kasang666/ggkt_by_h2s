package com.h2s.ggkt;
// -*-coding:utf-8 -*-

/*
 * File       : MyException.java
 * Time       ：2022/10/23 19:59
 * Author     ：hhs
 * version    ：java8
 * Description：
 */

import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
    private Integer code;
    private String message;
    public BusinessException(Integer code, String message){
        this.code = code;
        this.message = message;
    }
    public BusinessException(String message){
        this.code = 20001;
        this.message = message;
    }
    public BusinessException(){
        this.code = 20001;
        this.message = "BusinessException";
    }
}
