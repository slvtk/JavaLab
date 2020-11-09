package ru.kpfu.hateoas_hw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.hateoas_hw.models.Answer;

public interface AnswersRepository extends JpaRepository<Answer, Long> {
}
