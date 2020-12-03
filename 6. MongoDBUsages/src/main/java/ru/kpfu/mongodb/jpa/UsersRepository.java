package ru.kpfu.mongodb.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<User, String> {
    void deleteByName(String name);
}
