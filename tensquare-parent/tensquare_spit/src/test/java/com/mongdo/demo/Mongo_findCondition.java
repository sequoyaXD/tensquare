package com.mongdo.demo;

import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yangfei
 * @Date: 2018/9/26 17:25
 * @Description:    删除文档
 */

public class Mongo_findCondition {
    public static void main(String[] args) {
        // 创建连接
        String host = "192.168.66.128"; // ip地址
        int port = 27017; //ip端口
        MongoClient mongoClient = new MongoClient(host,port);

        // 获取数据库
        MongoDatabase spitdb = mongoClient.getDatabase("spitdb");
        // 获取对应的集合
        MongoCollection<Document> spit = spitdb.getCollection("spit");
        // 创建条件查询对象
        BasicDBObject bson = new BasicDBObject("_id","005");
        FindIterable<Document> documents = spit.find(bson);
        for (Document document : documents) {
            String id = document.getString("_id");
            String title = document.getString("title");
            String content = document.getString("content");
            Integer visits = document.getInteger("visits");
            System.out.println("_id: "+id+" ; title: "+title+" ; content: "+content+" ; visits: "+visits);
        }
        mongoClient.close();
    }
}
