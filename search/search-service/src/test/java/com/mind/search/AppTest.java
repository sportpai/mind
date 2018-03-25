package com.mind.search;

import com.mind.search.entity.Node;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

import com.mongodb.client.MongoIterable;
import org.bson.Document;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collection;

/**
 * Created by wangyunlong on 2016/11/18.
 */
@RunWith(value = SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:spring/dubbo-search-provider.xml"})
public class AppTest {

    @Autowired
    private MongoClient mongoClient;
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test() throws Exception{

       System.out.println(mongoClient);

        Collection<DB> dbs = mongoClient.getUsedDatabases();

        MongoDatabase mongoDatabase = mongoClient.getDatabase("node");

        MongoIterable<String> cols =  mongoDatabase.listCollectionNames();

        for(String str:cols){
            System.out.println(str);
        }

        MongoCollection<Document> collections = mongoDatabase.getCollection("node");
        FindIterable<Document> docs =  collections.find();

        for(Document doc:docs){
            System.out.println(doc.toJson());
        }
        System.in.read();
    }

    @Test
    public void testSave(){
        Node node = new Node();
        node.setId(123213l);
        mongoTemplate.save(node);
    }


}