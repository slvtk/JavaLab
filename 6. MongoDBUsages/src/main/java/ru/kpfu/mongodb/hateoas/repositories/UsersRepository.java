package ru.kpfu.mongodb.hateoas.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RestResource;
import ru.kpfu.mongodb.hateoas.models.User;

import java.util.List;

public interface UsersRepository extends MongoRepository<User, String> {

    @RestResource(path = "underAge", rel = "underAge")
    @Query(value = "{age: {$lt: ?0}}")
    List<User> findUnderAge(@Param("age") Integer age);

}
