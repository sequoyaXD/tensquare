package com.tensquare.friend.service;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Author: yangfei
 * @Date: 2018/10/8 19:32
 * @Description:
 */
@Service
public class FriendService {
    @Autowired
    private FriendDao friendDao ;
    @Autowired
    private NoFriendDao noFriendDao;

    // 注入远程用户微服务接口对象
    @Autowired
    private UserClient userClient ;

    // 添加好友/非好友 type:1 喜欢 , 2 不喜欢

    public void like(String userid ,String friendid, String type) {
    }
    @Transactional
    public int addFriend(String userid, String friendid) {
        // 判断是否添加过该好友
        int count = friendDao.selectCount(userid,friendid);
        if(count>0){
            // 添加过
            return 0;
        }
        // 未添加 , 创建一个好友对象
        Friend friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);

        // 同步关注数 , 同步粉丝数
        userClient.updateFollowCount(userid,1);
        userClient.updateFansCount(friendid,1);

        // 判断对方是否有添加(喜欢)过
        if(friendDao.selectCount(friendid,userid)>0){
            // 对方添加过 , 此时两方islike为1
            friendDao.updateLike(userid,friendid,"1");
            friendDao.updateLike(friendid,userid,"1");

            // 同步关注数 , 同步粉丝数
            userClient.updateFollowCount(friendid,1);
            userClient.updateFansCount(userid,1);

        }
        return 1;
    }

    /**
     *  添加非好友 : 将系统推送的好友拉入黑名单
     */
    public void addNoFriend(String userid, String friendid) {
        // 创建黑名单好友对象
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);

        noFriendDao.save(noFriend);
    }
    /*
        好友删除 :
            删除当前用户好友记录
            更新对方好友记录islike为0
     */
    @Transactional
    public void deleteFriend(String userid,String friendid) {
        friendDao.deleteFriend(userid,friendid);

        // 更新对方好友记录islike为0
        friendDao.updateLike(friendid,userid,"0");

        // 同步用户关注数与粉丝数
        userClient.updateFollowCount(userid,-1);
        userClient.updateFansCount(userid,-1);
    }
}
