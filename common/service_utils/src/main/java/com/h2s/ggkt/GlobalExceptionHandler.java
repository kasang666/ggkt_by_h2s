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
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;


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

    /**
     * 捕获验证失败异常
     * @param exception
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result exceptionHandler(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            if (errors != null) {
                errors.forEach(p -> {
                    FieldError fieldError = (FieldError) p;
                    log.warn("Bad Request Parameters: dto entity [{}],field [{}],message [{}]",fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
                    stringBuilder.append(fieldError.getDefaultMessage());
                });
            }
        }
        return Result.fail(stringBuilder.toString());
    }
}
