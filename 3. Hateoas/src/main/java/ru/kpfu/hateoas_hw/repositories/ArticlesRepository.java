package ru.kpfu.hateoas_hw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import ru.kpfu.hateoas_hw.models.Article;

import java.util.List;

@RepositoryRestController
public interface ArticlesRepository extends JpaRepository<Article, Long> {

    List<Article> findAllByTitle(String title);

    Article findArticleById(Long articleId);
}
