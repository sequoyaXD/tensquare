package com.tensquare.qa.client.impl;

import com.tensquare.qa.client.LabelClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @Author: yangfei
 * @Date: 2018/10/9 16:08
 * @Description:    远程调用微服务接口失败处理类
 */
@Component
public class LabelClientImpl implements LabelClient{
    @Override
    public Result findById(String id) {
        return new Result(false, StatusCode.REMOTE_ERROR,"远程调用失败,熔断器开启.....");
    }
}
