package com.tensquare.manager;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import util.JwtUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yangfei
 * @Date: 2018/10/9 17:05
 * @Description:    后台网关过滤器 : 作预处理请求认证
 */
@Component
public class ManagerFilter extends ZuulFilter{
    /**
     *  设置过滤器类型 :
     *      pre : 可以在请求路由之前被调用
     *      route : 在路由请求时候调用
     *      post : 在route和error过滤器之后调用
     *      error: 处理请求发生错误时被调用
     */
    @Override
    public String filterType() {
        // return "route";
        // return "post";
        // return "error";
        return "pre";
    }

    /**
     * 过滤器的执行顺序 :
     *      数值越大优先级越低
     */
    @Override
    public int filterOrder() {
        return 0;   //数值越大优先级越低
    }

    /**
     * 是否执行过滤器 :
     *      false :不执行
     *      true : 执行
     */
    @Override
    public boolean shouldFilter() {
        return true;
    }

    /**
     *  过滤器的逻辑 :
     *      return :
     *          null : 放行
     */
    // 注入JwtUtil
    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public Object run() throws ZuulException {

        System.out.println("执行了后台网关zuul的过滤器....");

        // 获取请求对象
        RequestContext currentContext = RequestContext.getCurrentContext();
        HttpServletRequest request = currentContext.getRequest();

        // 放行管理员登录的请求
        String uri = request.getRequestURI();
        if(uri.indexOf("/admin/login")>0){
            // 是登录的请求 , 放行
            return null;
        }

        // 获取认证token
        String auth = request.getHeader("Authorization");
        // 判断验证信息是否合法
        if(auth!= null && auth.startsWith("Bearer ")){
            // 截取token
            String token = auth.substring(7);
            // 解析token
            Claims claims = jwtUtil.parseJWT(token);
            // 判断是否是以管理员身份登录
            if(claims!=null && "admin".equals(claims.get("roles"))){
                // 放行
                //将头部信息带回到网关后续请求里面
                currentContext.addZuulRequestHeader("Authorization",auth);
                return null;
            }
        }
        // 终止请求 , 返回提示信息
        currentContext.setResponseBody("请以管理员身份登录....");
        currentContext.setResponseStatusCode(401);  // 状态码401,表示权限不足
        // 设置响应的字符集合类型
        currentContext.getResponse().setContentType("text/html;charset=utf-8");
        // false , 表示不放行
        currentContext.setSendZuulResponse(false);
        return null;
    }
}
