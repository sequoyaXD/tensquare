package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Author: yangfei
 * @Date: 2018/9/27 18:35
 * @Description:
 */

public interface ArticleDao extends ElasticsearchRepository<Article,String> {
    Page<Article> findByTitleOrContentLike(String keywords, String keywords1, Pageable pageable);
}
