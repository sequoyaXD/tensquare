package com.tensuare.web;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yangfei
 * @Date: 2018/10/9 17:05
 * @Description:
 */
@Component
public class WebFilter extends ZuulFilter{
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
     *      由于Zuul前台网关的过滤器会拦截请求头信息 , 以至于传不到后台
     *      这里需要把认证的token放到网关的后续的网关请求中
     */
    @Override
    public Object run() throws ZuulException {
        System.out.println("执行了zuul的过滤器....");
        // 获取当前请求头的信息
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();
        String auth = request.getHeader("Authorization");
        // 判断
        if(auth!=null && !"".equals(auth)){
            //将头部信息带回到网关后续请求里面
            context.addZuulRequestHeader("Authorization",auth);
        }
        return null;

    }
}
