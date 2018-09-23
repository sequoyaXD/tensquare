package com.tensquare.base.dao;

import com.tensquare.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Author: yangfei
 * @Date: 2018/9/23 17:31
 * @Description:
 */

public interface LabelDao extends JpaRepository<Label,String>,JpaSpecificationExecutor<Label>{
}
