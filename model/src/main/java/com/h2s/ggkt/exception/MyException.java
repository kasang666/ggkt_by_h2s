package com.h2s.ggkt.exception;
// -*-coding:utf-8 -*-

/*
 * File       : MyException.java
 * Time       ：2022/10/23 0:24
 * Author     ：hhs
 * version    ：java8
 * Description：
 */

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyException extends RuntimeException{
    private Integer code;
    private String msg;

}
