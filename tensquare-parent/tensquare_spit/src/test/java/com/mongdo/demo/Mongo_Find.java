package com.mongdo.demo;

import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yangfei
 * @Date: 2018/9/26 17:25
 * @Description:    插入文档
 */

public class Mongo_Find {
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
        FindIterable<Document> documents = spit.find();
        for (Document document : documents) {
            String id = document.getString("_id");
            String title = document.getString("title");
            String content = document.getString("content");
            Integer visits = document.getInteger("visits");
            System.out.println("_id: "+id+" ; title: "+title+" ; content: "+content+" ; visits: "+visits);
        }
        // 关闭连接
        mongoClient.close();
    }
}
