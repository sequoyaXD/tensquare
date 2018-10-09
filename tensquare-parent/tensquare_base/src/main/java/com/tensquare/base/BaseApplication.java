package com.tensquare.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @Author: yangfei
 * @Date: 2018/9/23 17:25
 * @Description:    基础微服务启动类
 */
@SpringBootApplication
@EnableEurekaClient
public class BaseApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseApplication.class,args);
    }

    // 初始化IdWorker , 并加入spring容器
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
