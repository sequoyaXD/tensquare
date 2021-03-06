package com.tensquare.friend.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: yangfei
 * @Date: 2018/10/8 19:32
 * @Description:
 */
@Entity
@Table(name="tb_friend")
@IdClass(Friend.class)  // 联合主键标识
public class Friend implements Serializable{
    @Id
    private String userid;
    @Id
    private String friendid;
    private String islike ;

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getFriendid() {
        return friendid;
    }

    public void setFriendid(String friendid) {
        this.friendid = friendid;
    }

    public String getIslike() {
        return islike;
    }

    public void setIslike(String islike) {
        this.islike = islike;
    }
}
