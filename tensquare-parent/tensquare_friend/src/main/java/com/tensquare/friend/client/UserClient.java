package com.tensquare.friend.client;

import entity.Result;
import entity.StatusCode;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author: yangfei
 * @Date: 2018/10/8 21:06
 * @Description:    远程调用用户微服务接口
 */
@FeignClient("tensquare-user")
public interface UserClient {

    // 更新用户关注数
    @RequestMapping(value = "/user/updateFollowCount/{userid}/{x}")
    public Result updateFollowCount(@PathVariable("userid") String userid , @PathVariable("x") int x);

    // 更新用户粉丝数
    @RequestMapping(value = "/user/updateFansCount/{userid}/{x}")
    public Result updateFansCount(@PathVariable("userid") String userid , @PathVariable("x") int x);

}
