package com.mongdo.demo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: yangfei
 * @Date: 2018/9/26 17:25
 * @Description:    删除文档
 */

public class Mongo_findAll {
    public static void main(String[] args) {
        // 指定ip和端口
        String host = "192.168.66.128";
        int port = 27017;
        // 创建MongoDB客户端对象
        MongoClient mongoClient = new MongoClient(host,port);
        // 获取数据库
        MongoDatabase spitdb = mongoClient.getDatabase("spitdb");
        // 获取集合
        MongoCollection<Document> spit = spitdb.getCollection("spit");
        // 创建集合封装查询条件
        List<BasicDBObject> basicList = new ArrayList<>();
        basicList.add(new BasicDBObject("title","java超级工程师"));
        basicList.add(new BasicDBObject("visits",100));
        // 将集合转化为查询对象
        BasicDBObject basicDBObject = new BasicDBObject("$and",basicList);

        FindIterable<Document> documents = spit.find(basicDBObject);
        // 遍历
        for (Document document : documents) {
            String id = document.getString("_id");
            String title = document.getString("title");
            String content = document.getString("content");
            Integer visits = document.getInteger("visits");
            System.out.println("_id: "+id+" ; title: "+title+" ; content: "+content+" ; visits: "+visits);
        }
        // 释放资源
        mongoClient.close();
    }
}
