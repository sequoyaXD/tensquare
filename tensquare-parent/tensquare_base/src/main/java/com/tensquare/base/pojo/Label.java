package com.tensquare.base.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: yangfei
 * @Date: 2018/9/23 17:27
 * @Description: 标签实体类
 */
@Entity
@Table(name = "tb_label")
public class Label implements Serializable {
    @Id
    private String id;  //编号
    private String labelname;   //标签名
    private String state;   //状态 , 0无效,1有效
    private Integer count;  //使用数量
    private String recommend;   //是否推荐
    private Integer fans;   // 粉丝数

    public Label() {
    }

    public Label(String id, String labelname, String state, Integer count, String recommend, Integer fans) {

        this.id = id;
        this.labelname = labelname;
        this.state = state;
        this.count = count;
        this.recommend = recommend;
        this.fans = fans;
    }

    public String getId() {

        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabelname() {
        return labelname;
    }

    public void setLabelname(String labelname) {
        this.labelname = labelname;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
    }

    public Integer getFans() {
        return fans;
    }

    public void setFans(Integer fans) {
        this.fans = fans;
    }
}
