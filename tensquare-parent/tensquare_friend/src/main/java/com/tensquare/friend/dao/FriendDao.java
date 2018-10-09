package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @Author: yangfei
 * @Date: 2018/10/8 19:32
 * @Description:
 */
public interface FriendDao extends JpaRepository<Friend,String>,JpaSpecificationExecutor<Friend>{
    // 查询用户添加好友记录
    @Query("select count(f) from Friend f where f.userid=?1 and f.friendid=?2 ")
    int selectCount(String userid,String friendid);

    // 更新用户好友islike
    @Modifying
    @Query("update Friend set islike=?3 where userid=?1 and friendid=?2")
    void updateLike(String userid, String friendid, String s);

    // 好友删除
    @Modifying
    @Query("delete from Friend f where f.userid=?1 and f.friendid=?2")
    void deleteFriend(String userid, String friendid);
}
