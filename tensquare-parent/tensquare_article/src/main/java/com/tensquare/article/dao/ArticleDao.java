package com.tensquare.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.tensquare.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    @Modifying // 标记当前的方法执行的是更新操作,必须带此注解
    @Query("update Article set state='1' where id=?1")
    void examine(String articleId);
    @Modifying
    @Query("update Article set thumbup=(thumbup+1) where id=?1")
    void thumbup(String articleId);
}
