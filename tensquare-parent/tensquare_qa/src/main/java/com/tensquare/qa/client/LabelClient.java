package com.tensquare.qa.client;

import com.tensquare.qa.client.impl.LabelClientImpl;
import entity.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: yangfei
 * @Date: 2018/10/8 18:51
 * @Description:    远程调用微服务的接口
 */
// value : 远程微服务名 ; fallback : 远程调用失败处理类
@FeignClient(value = "tensquare-base",fallback = LabelClientImpl.class) // 注入的微服务名称
public interface LabelClient {
    /*
        注意 :
            @RequestMapping(value = "/label/{id}"
                必须加上label
            @PathVariable("id")  必须指定参数名
     */
    // 查询一个
    @RequestMapping(value = "/label/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable("id") String id);

}
