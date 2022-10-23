package com.h2s.ggkt;
// -*-coding:utf-8 -*-

/*
 * File       : GlobalExceptionHandler.java
 * Time       ：2022/10/23 0:02
 * Author     ：hhs
 * version    ：java8
 * Description：
 */

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public Result globalError(Exception e){
        log.info("捕获全局异常： {}", e.getMessage());
        return Result.fail().message("捕获全局异常");
    }

    @ExceptionHandler(value = BusinessException.class)
    public Result myError(BusinessException e){
        log.error("业务异常： {}", e.getMessage());
        return Result.fail().message(e.getMessage());
    }
}
