package com.tensquare.friend.controller;

import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: yangfei
 * @Date: 2018/10/8 19:31
 * @Description:
 */
@RestController
@CrossOrigin //跨域
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService ;
    @Autowired
    private HttpServletRequest request;
    // 添加好友/非好友 , type:1 喜欢 , 2 不喜欢
    @RequestMapping(value = "/like/{friendid}/{type}",method = RequestMethod.PUT)
    public Result like(@PathVariable String friendid,@PathVariable String type){
        // 解析token
        Claims claims = (Claims) request.getAttribute("user_claims");
        if (claims==null){
            return new Result(true,StatusCode.ACCESS_ERROR,"请登录后再添加");
        }
        // 1. 添加好友
        if("1".equals(type)){
            int flag = friendService.addFriend(claims.getId(),friendid);
            // 0 , 表示添加
            if(flag == 0){
                return new Result(false , StatusCode.REPEATE_ERROR , "您已经添加过该好友了");
            }
            return new Result(true, StatusCode.OK,"添加成功");
        }else {
            //2. 添加非好友 : 将系统推送的好友拉入黑名单
            friendService.addNoFriend(claims.getId(),friendid);
            return new Result(true , StatusCode.OK , "添加非好友成功");
        }

    }

    // 删除好友
    @RequestMapping(value = "/{friendid}",method = RequestMethod.DELETE)
    public Result deleteFriend(@PathVariable String friendid){
        // 判断用户是否登录
        Claims claims = (Claims) request.getAttribute("user_claims");
        if(claims==null){
            return new Result(false,StatusCode.ACCESS_ERROR,"请重新登录");
        }

        friendService.deleteFriend(claims.getId(),friendid) ;
        return new Result(true,StatusCode.OK,"删除成功");
    }
}
