package com.tensquare.search;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @Author: yangfei
 * @Date: 2018/9/27 18:07
 * @Description:
 */
@SpringBootApplication  //搜索微服务启动类
public class SearchApplication {
    public static void main(String[] args) {
        SpringApplication.run(SearchApplication.class);
    }
    @Bean
    public IdWorker createIdWorker(){
        return new IdWorker(1,1);
    }
}
