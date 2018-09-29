package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Author: yangfei
 * @Date: 2018/9/26 19:10
 * @Description:
 */

public interface SpitDao extends MongoRepository<Spit,String>{
    // 根据上级id查询吐槽评论
    Page<Spit> findByParentidOrderByPublishtimeDesc(String parentid, Pageable pageable);
}
