package com.tensquare.search.service;

import com.tensquare.search.dao.ArticleDao;
import com.tensquare.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import util.IdWorker;

/**
 * @Author: yangfei
 * @Date: 2018/9/27 18:40
 * @Description:
 */
@Service
public class ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Autowired
    private IdWorker idWorker ;
    // 添加
    public void add(Article article){
        article.setId(idWorker.nextId()+"");
        articleDao.save(article);
    }
    //搜索
    public Page<Article> findSearch(String keywords, int page, int size) {
       return articleDao.findByTitleOrContentLike(keywords,keywords, PageRequest.of(page-1,size));
    }
}
