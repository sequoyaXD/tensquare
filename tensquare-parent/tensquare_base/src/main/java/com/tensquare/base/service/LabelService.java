package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

    // 抽取方法用于创建Specification对象用于条件查询
    private Specification createSpecification(Map searchMap) {
        return new Specification() {
            /**
             * @param root  跟对象 , 用于获取实体类属性名
             * @param criteriaQuery
             * @param criteriaBuilder 用于创建条件对象
             * @return
             */
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                // 创建List集合封装条件对象
                List<Predicate> predicateList = new ArrayList<>();
                // 根据labelname查询
                if(searchMap.get("labelname")!=null&& !"".equals((String)searchMap.get("labelname"))){
                    predicateList.add(criteriaBuilder.like(root.get("labelname").as(String.class),"%"+(String)searchMap.get("labelname")+"%"));
                }
                // 根据state查询
                if(searchMap.get("state")!=null && !"".equals((String)searchMap.get("state"))){
                    predicateList.add(criteriaBuilder.equal(root.get("state"),(String)searchMap.get("state")));
                }
                // 根据recommend查询
                if(searchMap.get("recommend")!=null && !"".equals((String)searchMap.get("recommend"))){
                    predicateList.add(criteriaBuilder.equal(root.get("recommend").as(String.class),(String)searchMap.get("recommend")));
                }
                // 定义数组,存储封装查询条件
                Predicate[] preArray = new Predicate[predicateList.size()];
                return criteriaBuilder.and(predicateList.toArray(preArray));
            }
        };
    }
    // 条件查询
    public List<Label> findSearch(Map searchMap) {
        // 调用方法 , 组装查询条件
        Specification spec = createSpecification(searchMap);
        List<Label> list = labelDao.findAll(spec);
        return list;
    }

    // 分页+条件查询
    public Page<Label> findSearch(Map searchMap,int page,int size){
        // 调用方法 , 组装查询条件
        Specification spec = createSpecification(searchMap);
        //PageRequest类静态方法of()返回一个Pageable实现类对象
        // 注意,spring data jpa的分页是从0开始的 , 所以这里查询第1页的话,应该要减1,即 page-1
        Page<Label> labelPage = labelDao.findAll(spec, PageRequest.of(page-1,size));
        return labelPage;
    }
    // 分页+查询+排序
    public Page<Label> findSearch(Map searchMap,int page,int size,String direction){
        Specification spec = createSpecification(searchMap);
        Sort sort = null ;
        if("ASC".equalsIgnoreCase(direction)){
            sort = new Sort(Sort.Direction.ASC);
        }else{
            sort = new Sort(Sort.Direction.DESC);
        }
        Page<Label> labelPage = labelDao.findAll(spec,PageRequest.of(page,size,sort));
        return labelPage;
    }
}
