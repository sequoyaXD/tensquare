package com.tensquare.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.user.pojo.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface UserDao extends JpaRepository<User,String>,JpaSpecificationExecutor<User>{
    User findByMobile(String mobile);

    // 更新用户关注数
    @Modifying
    @Query("update User u set u.followcount=u.followcount+?2 where u.id=?1")
    void updateFollowCount(String userid, int x);

    // 更新用户粉丝数
    @Modifying
    @Query("update User u set u.fanscount=u.fanscount+?2 where u.id=?1")
    void updateFansCount(String userid, int x);
}
