package com.tensuare.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;

/**
 * @Author: yangfei
 * @Date: 2018/10/9 16:49
 * @Description:    网站前台微服务网关
 */
@SpringBootApplication
@EnableEurekaClient // 开启Eureka
@EnableZuulProxy    // 开启微服务网关
public class WebApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebApplication.class,args);
    }
}
