package com.tensquare.friend.dao;

import com.tensquare.friend.pojo.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: yangfei
 * @Date: 2018/10/8 19:32
 * @Description:
 */
public interface NoFriendDao extends JpaRepository<NoFriend,String>,JpaSpecificationExecutor<NoFriend>{
}
