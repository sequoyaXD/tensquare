package com.tensquare.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @Author: yangfei
 * @Date: 2018/9/26 19:13
 * @Description:    吐槽微服务引导类
 */
@SpringBootApplication
public class SpitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpitApplication.class,args);
    }

    // 创建Idworker
    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1,1);
    }
}
