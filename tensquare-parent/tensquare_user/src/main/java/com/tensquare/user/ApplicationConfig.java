package com.tensquare.user;

import com.tensquare.user.filter.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @Author: yangfei
 * @Date: 2018/10/4 16:32
 * @Description:        添加拦截器到环境中
 */
@Configuration
public class ApplicationConfig extends WebMvcConfigurationSupport {

    // 注入自定义的拦截器
    @Autowired
    private JwtFilter jwtFilter ;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        // 将自定义的拦截器添加进去
        registry.addInterceptor(jwtFilter)
                .addPathPatterns("/**") //拦截所有
                .excludePathPatterns("/**/login");  //排除登录相关
    }
}
