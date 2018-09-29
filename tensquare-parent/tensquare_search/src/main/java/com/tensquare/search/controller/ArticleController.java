package com.tensquare.search.controller;

import com.tensquare.search.pojo.Article;
import com.tensquare.search.service.ArticleService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: yangfei
 * @Date: 2018/9/27 18:40
 * @Description:
 */
@CrossOrigin
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService articleService ;

    // 添加
    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public Result add(@RequestBody Article article){
        return new Result(true,StatusCode.OK,"添加成功");
    }
    /*
        文章搜索
     */
    @RequestMapping(value = "/search/{keywords}/{page}/{size}",method = RequestMethod.GET)
    public Result findSearch(@PathVariable String keywords,@PathVariable int page,@PathVariable int size){

        Page<Article> pageResult =  articleService.findSearch(keywords,page,size);

        return new Result(true, StatusCode.OK,"搜索成功",new PageResult<>(pageResult.getTotalElements(),pageResult.getContent()));
    }
}
