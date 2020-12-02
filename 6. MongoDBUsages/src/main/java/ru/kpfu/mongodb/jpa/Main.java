package ru.kpfu.mongodb.jpa;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(RepConf.class);
        UsersRepository usersRepository = context.getBean(UsersRepository.class);
        //CREATE
        User salavat = User.builder()._id("1").age(21).name("new").surname("new").build();
        usersRepository.save(salavat);
        //UPDATE
        salavat.setName("salavat");
        usersRepository.save(salavat);
        //DELETE
        usersRepository.deleteByName("new");
    }
}
