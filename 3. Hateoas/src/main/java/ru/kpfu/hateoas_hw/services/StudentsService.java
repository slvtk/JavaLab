package ru.kpfu.hateoas_hw.services;

import ru.kpfu.hateoas_hw.models.Article;
import ru.kpfu.hateoas_hw.models.Student;
import ru.kpfu.hateoas_hw.models.Test;

public interface StudentsService {

    Test passTest(Long testId );

    Article rateArticle(Long articleId);

    Student verify(Long studentId);

}
