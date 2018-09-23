package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
        return new Result(true,"查询成功", StatusCode.OK,labelService.findAll());
    }
    // 查询一个
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true,"查询成功",StatusCode.OK,labelService.findById(id));
    }
    // 添加
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Label label){
        labelService.save(label);
        return new Result(true,"添加成功",StatusCode.OK);
    }
    // 修改
    @RequestMapping(value = "/{labelId}",method = RequestMethod.PUT)
    public Result update(@PathVariable("labelId") String id, @RequestBody Label label){
        label.setId(id);
        labelService.update(label);
        return new Result(true,"修改成功",StatusCode.OK);
    }
    // 删除
    @RequestMapping(value = "/{labelId}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable String labelId){
        labelService.delete(labelId);
        return new Result(true,"删除成功", StatusCode.OK);
    }
}
