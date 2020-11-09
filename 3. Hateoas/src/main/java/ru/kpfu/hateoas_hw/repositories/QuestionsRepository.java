package ru.kpfu.hateoas_hw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.hateoas_hw.models.Question;

public interface QuestionsRepository extends JpaRepository<Question,Long> {
}
