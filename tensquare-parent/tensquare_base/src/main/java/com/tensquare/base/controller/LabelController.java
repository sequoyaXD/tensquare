package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: yangfei
 * @Date: 2018/9/23 17:32
 * @Description:
 */
@RestController
@CrossOrigin //解决前端跨域请求的问题
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    // 查询全部
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功", labelService.findAll());
    }
    // 查询一个
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",labelService.findById(id));
    }
    // 添加
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true,StatusCode.OK,"添加成功");
    }
    // 修改
    @RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
    public Result update(@PathVariable("labelId") String id, @RequestBody Label label){
        label.setId(id);
        labelService.update(label);
        return new Result(true,StatusCode.OK,"修改成功");
    }
    // 删除
    @RequestMapping(value = "/{labelId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String labelId){
        labelService.delete(labelId);
        return new Result(true, StatusCode.OK,"删除成功");
    }
    // 条件搜索
    @RequestMapping(value = "/search",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap){
        List<Label> labelList = labelService.findSearch(searchMap);
        return new Result(true,StatusCode.OK,"搜索成功", labelList);
    }
    // 分页+条件查询
    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size){
        Page<Label> labelPage =  labelService.findSearch(searchMap,page,size);
        return new Result(true,StatusCode.OK,"搜索成功",new PageResult<>(labelPage.getTotalElements(),labelPage.getContent()));
    }
    // 分页+排序+条件查询
    @RequestMapping(value = "/search/{page}/{size}/{sort}",method = RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap,@PathVariable int page,@PathVariable int size,@PathVariable String sort){
        Page<Label> labelPage =  labelService.findSearch(searchMap,page,size,sort);
        return new Result(true,StatusCode.OK,"搜索成功",new PageResult<>(labelPage.getTotalElements(),labelPage.getContent()));
    }
}
