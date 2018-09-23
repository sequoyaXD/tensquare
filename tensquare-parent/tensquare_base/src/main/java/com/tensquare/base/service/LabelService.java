package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import entity.IdWorker;
import entity.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: yangfei
 * @Date: 2018/9/23 20:11
 * @Description:    标签服务层
 */
@Service
public class LabelService {
    @Autowired
    private LabelDao labelDao;
    @Autowired
    private IdWorker idWorker;

    // 查询全部
    public List<Label> findAll(){
        return labelDao.findAll();
    }
    // 查询一个
    public Label findById(String id){
        return labelDao.findById(id).get();
    }
    // 保存
    public void save(Label label){
        label.setId(idWorker.nextId()+"");
        labelDao.save(label);
    }
    // 修改 --- spring-data-jpa中save方法,id存在,表示修改,id不存在表示添加
    public void update(Label label){
        labelDao.save(label);
    }
    // 删除
    public void delete(String id){
        labelDao.deleteById(id);
    }
}
