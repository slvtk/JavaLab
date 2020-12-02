package ru.kpfu.mongodb.jpa;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface CoursesRepository extends MongoRepository<Course, String> {
}
