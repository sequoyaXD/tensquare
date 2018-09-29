package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: yangfei
 * @Date: 2018/9/26 19:11
 * @Description:
 */
@RestController
@RequestMapping("/spit")
@CrossOrigin
public class SpitController {

    @Autowired
    private SpitService spitService;
    @Autowired// 注入redis模板
    private RedisTemplate redisTemplate;
    /**
     * 查询全部数据
     * @return
     */
    @RequestMapping(method= RequestMethod.GET)
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findAll());
    }

    /**
     * 根据ID查询
     * @param id ID
     * @return
     */
    @RequestMapping(value="/{id}",method= RequestMethod.GET)
    public Result findById(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findById(id));
    }


    /**
     * 分页+多条件查询
     * @param searchMap 查询条件封装
     * @param page 页码
     * @param size 页大小
     * @return 分页结果
     */
  /*  @RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
    public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
        Page<Spit> pageList = spitService.findSearch(searchMap, page, size);
        return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Spit>(pageList.getTotalElements(), pageList.getContent()) );
    }*/

    /**
     * 根据条件查询
     * @param searchMap
     * @return
     */
 /*   @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findSearch(searchMap));
    }
*/
    /**
     * 增加
     * @param spit
     */
    @RequestMapping(method=RequestMethod.POST)
    public Result add(@RequestBody Spit spit  ){
        spitService.add(spit);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    /**
     * 修改
     * @param spit
     */
    @RequestMapping(value="/{id}",method= RequestMethod.PUT)
    public Result update(@RequestBody Spit spit, @PathVariable String id ){
        spit.setId(id);
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 删除
     * @param id
     */
    @RequestMapping(value="/{id}",method= RequestMethod.DELETE)
    public Result delete(@PathVariable String id ){
        spitService.deleteById(id);
        return new Result(true, StatusCode.OK,"删除成功");
    }

    // 查询吐槽评论,分页显示
    @RequestMapping(value = "/comment/{parentid}/{page}/{size}",method = RequestMethod.GET)
    public Result comment(@PathVariable String parentid,@PathVariable int page,@PathVariable int size){
        Page<Spit> spitPage =  spitService.comment(parentid,page,size);
        return new Result(true,StatusCode.OK,"查询成功",new PageResult<>(spitPage.getTotalElements(),spitPage.getContent()));
    }
    // 吐槽点赞

    @RequestMapping(value = "/thumbup/{spitId}",method = RequestMethod.PUT)
    public Result thumbup(@PathVariable String spitId){
        // 模拟用户登录
        String userid = "1";
        // 从redis查询该用户是否已经点过赞
        String flag = (String) redisTemplate.opsForValue().get("spit_" + userid + "_" + spitId);
        if(flag!=null){
            // 点过赞
            return new Result(true,StatusCode.OK,"您已经赞过啦!!!");
        }
        spitService.thumbup(spitId);
        // 将数据存入redis
        redisTemplate.opsForValue().set("spit_" + userid + "_" + spitId,"1");
        return new Result(true,StatusCode.OK,"点赞成功");
    }
}
