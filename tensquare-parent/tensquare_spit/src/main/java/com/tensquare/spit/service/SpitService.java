package com.tensquare.spit.service;

import com.tensquare.spit.dao.SpitDao;
import com.tensquare.spit.pojo.Spit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import util.IdWorker;

import java.util.List;
import java.util.Optional;

/**
 * @Author: yangfei
 * @Date: 2018/9/26 19:10
 * @Description:
 */
@Service
public class SpitService {
    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 查询全部列表
     * @return
     */
    public List<Spit> findAll() {
        return spitDao.findAll();
    }

    /**
     * 条件查询+分页
     * @param whereMap
     * @param page
     * @param size
     * @return
     */
   /* public Page<Spit> findSearch(Map whereMap, int page, int size) {
        Page<Spit> spitPage = spitDao.findAll();
    }*/


    /**
     * 条件查询
     * @param whereMap
     * @return
     */
   /* public List<Spit> findSearch(Map whereMap) {
        Page<Spit> page = spitDao.findAll()
    }*/

    /**
     * 根据ID查询实体
     * @param id
     * @return
     */
    public Spit findById(String id) {
        return spitDao.findById(id).get();
    }

    /**
     * 增加
     *  业务 :
     *      判断新增吐槽是否有父节点 ,
     *       如果有,说明是新增评论,则父节点对应吐槽的评论人数+1 , 再添加吐槽评论
     *       如果没有 , 说明是新增吐槽 , 直接添加
     */
    public void add(Spit spit) {
        spit.setId(idWorker.nextId()+"");
        String parentid = spit.getParentid();
        // 判断是否是评论
        if(parentid!=null && !"".equals(parentid)){
            // 修改父节点吐槽评论人数
            // 创建查询对象
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(parentid));
            // 创建修改对象
            Update update = new Update();
            update.inc("comment",1);
            // 修改
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    /**
     * 修改
     * @param spit
     */
    public void update(Spit spit) {
        spitDao.save(spit);
    }

    /**
     * 删除
     * @param id
     */
    public void deleteById(String id) {
        spitDao.deleteById(id);
    }

    // 根据父节点 , 查询吐槽评论
    public Page<Spit> comment(String parentid, int page, int size) {
        Page<Spit> spitPage = spitDao.findByParentidOrderByPublishtimeDesc(parentid, PageRequest.of(page - 1, size));
        return spitPage ;
    }

    // 吐槽点赞 , 修改thumbup+1
    // 方式1: 先查询 , 再将值+1 , 最后保存
    /*public void thumbup(String spitId) {
        Spit spit = spitDao.findById(spitId).get();
        spit.setThumbup(spit.getThumbup()+1);
        spitDao.save(spit);
    }*/
    // 方式2 : 注入Mongo模板
    @Autowired
    private MongoTemplate mongoTemplate;
    public void thumbup (String spitId){
        // 1. 创建Query查询对象,添加查询条件
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(spitId));
        // 2. 创建修改对象
        Update update = new Update();
        update.inc("thumbup",1);    // 自增1
        // 3. 使用模板进行修改
        mongoTemplate.updateFirst(query,update,"spit");
    }
}
