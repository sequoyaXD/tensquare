package com.mongdo.demo;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yangfei
 * @Date: 2018/9/26 17:25
 * @Description:    插入文档
 */

public class Mongo_Insert {
    public static void main(String[] args) {
        // 创建连接
        String host = "192.168.66.128"; // ip地址
        int port = 27017; //ip端口
        MongoClient mongoClient = new MongoClient(host,port);

        // 获取数据库
        MongoDatabase spitdb = mongoClient.getDatabase("spitdb");

        // 获取对用的集合
        MongoCollection<Document> spit = spitdb.getCollection("spit");
        // 创建Map集合 , 存储文档数据
        Map map = new HashMap();
        map.put("_id","100");
        map.put("_name","jack");
        map.put("_age",20);

        // 创建文档对象
        Document document = new Document(map);
        spit.insertOne(document);
        // 关闭连接
        mongoClient.close();
    }
}
