package ru.kpfu.mongodb.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;

public class Main {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RepConf.class);
        MongoTemplate mongoTemplate = applicationContext.getBean(MongoTemplate.class);
        //CREATE
        User user = User.builder()._id("2").name("temp").build();
        mongoTemplate.save(user,"users");
        //UPDATE
        user.setName("edited");
        mongoTemplate.save(user,"users");
        //DELETE
        mongoTemplate.remove(user,"users");
    }
}
