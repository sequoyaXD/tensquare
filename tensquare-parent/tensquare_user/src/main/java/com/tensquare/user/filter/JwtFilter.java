package com.tensquare.user.filter;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: yangfei
 * @Date: 2018/10/4 16:28
 * @Description:    自定义拦截器 , 进行鉴权
 */
@Component
public class JwtFilter extends HandlerInterceptorAdapter {
    @Autowired
    private JwtUtil jwtUtil ;
    // 该方法在执行所有controller方法之前执行 , 返回true,执行下一个拦截器 , 执行完走controller方法
    // 返回false , 直接返回
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 从request请求头中获取token
        String auth = request.getHeader("Authorization");
        if(auth!=null && auth.startsWith("Bearer ")){
            // 截取token字符串
            String token = auth.substring(7);
            // 解析token
            Claims claims = jwtUtil.parseJWT(token);
            // 判断主体信息,进行鉴权
            if(claims!=null){
                //判断用户角色
                if("admin".equals(claims.get("roles"))){
                    // 存入标记 : 管理员
                    request.setAttribute("admin_claims",claims);
                }
                if("user".equals(claims.get("roles"))){
                    // 存入标记 : 普通用户
                    request.setAttribute("user_claims",claims);
                }
            }
        }
        return true;
    }
}
