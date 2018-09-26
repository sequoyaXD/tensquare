package com.tensquare.base.controller;

import entity.Result;
import entity.StatusCode;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: yangfei
 * @Date: 2018/9/23 20:58
 * @Description:    定义全局异常处理类
 */
@ControllerAdvice
public class BaseExceptionHandle {

    /**
     * 处理异常的方法 , 任何一个方法报错都会执行以下方法
     *
     * @param e
     * @return
     */
    @ExceptionHandler(value = Exception.class)  // 异常方法标记,value为需要捕获的异常类型
    @ResponseBody  // 必须设置响应给前端的数据为JSON格式
    public Result error(Exception e){
        return new Result(false, StatusCode.ERROR,e.getMessage());
    }
}
