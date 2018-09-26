package com.tensquare.qa.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Author: yangfei
 * @Date: 2018/9/25 20:01
 * @Description:    标签-->问题 中间表
 */
@Entity
@Table(name="tb_pl")
@IdClass(value = Pl.class)  // 标记该类为中间表
public class Pl implements Serializable{
    // 联合主键
    @Id
    private String problemid;
    @Id
    private String labelid;

    public Pl() {
    }

    public Pl(String problemid, String labelid) {
        this.problemid = problemid;
        this.labelid = labelid;
    }

    public String getProblemid() {
        return problemid;
    }

    public void setProblemid(String problemid) {
        this.problemid = problemid;
    }

    public String getLabelid() {
        return labelid;
    }

    public void setLabelid(String labelid) {
        this.labelid = labelid;
    }
}
