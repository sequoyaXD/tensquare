package com.tensquare.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @Author: yangfei
 * @Date: 2018/10/9 20:32
 * @Description:    配置中心微服务
 */
@SpringBootApplication
@EnableConfigServer //  开启配置中心服务端
public class ConfigApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConfigApplication.class,args);
    }
}
