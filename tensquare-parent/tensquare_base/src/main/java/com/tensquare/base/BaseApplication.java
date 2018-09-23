package com.tensquare.base;

import entity.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @Author: yangfei
 * @Date: 2018/9/23 17:25
 * @Description:    基础微服务启动类
 */
@SpringBootApplication
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
