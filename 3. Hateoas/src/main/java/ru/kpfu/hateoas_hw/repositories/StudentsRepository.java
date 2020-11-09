package ru.kpfu.hateoas_hw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.hateoas_hw.models.Student;

import java.util.Optional;

public interface StudentsRepository extends JpaRepository<Student,Long> {

    Student findStudentById(Long studentId);

}
