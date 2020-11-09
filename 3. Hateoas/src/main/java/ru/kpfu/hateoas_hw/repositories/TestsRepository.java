package ru.kpfu.hateoas_hw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.hateoas_hw.models.Test;

import java.util.List;

public interface TestsRepository extends JpaRepository<Test, Long> {

    Test findTestByArticleId(Long articleId);

    Test findTestById(Long testId);
}
