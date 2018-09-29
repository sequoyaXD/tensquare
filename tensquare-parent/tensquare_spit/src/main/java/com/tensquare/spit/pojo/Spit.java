package com.tensquare.spit.pojo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: yangfei
 * @Date: 2018/9/26 19:10
 * @Description:    吐槽实体类
 */
@Document(collection = "spit") //指定该实体类与MongoDB中集合的映射
public class Spit implements Serializable{
    @Id
    private String id;
    private String content; //吐槽内容
    private Date publishtime;//发布日期
    private String userid;//发布人ID
    private String nickname;//发布人昵称
    private Integer visits;//浏览量
    private Integer thumbup;//点赞数
    private Integer share;//分享数
    private Integer comment;//回复数
    private String state;//是否可见
    private String parentid;//父id

    public Spit(String id, String content, Date publishtime, String userid, String nickname, Integer visits, Integer thumbup, Integer share, Integer comment, String state, String parentid) {
        this.id = id;
        this.content = content;
        this.publishtime = publishtime;
        this.userid = userid;
        this.nickname = nickname;
        this.visits = visits;
        this.thumbup = thumbup;
        this.share = share;
        this.comment = comment;
        this.state = state;
        this.parentid = parentid;
    }

    public Spit() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public void setPublishtime(Date publishtime) {
        this.publishtime = publishtime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getVisits() {
        return visits;
    }

    public void setVisits(Integer visits) {
        this.visits = visits;
    }

    public Integer getThumbup() {
        return thumbup;
    }

    public void setThumbup(Integer thumbup) {
        this.thumbup = thumbup;
    }

    public Integer getShare() {
        return share;
    }

    public void setShare(Integer share) {
        this.share = share;
    }

    public Integer getComment() {
        return comment;
    }

    public void setComment(Integer comment) {
        this.comment = comment;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }
}
