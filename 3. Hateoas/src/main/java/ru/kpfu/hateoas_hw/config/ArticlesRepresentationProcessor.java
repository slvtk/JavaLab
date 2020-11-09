package ru.kpfu.hateoas_hw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.kpfu.hateoas_hw.controllers.ArticlesController;
import ru.kpfu.hateoas_hw.models.Article;
import ru.kpfu.hateoas_hw.repositories.StudentsRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ArticlesRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Article>> {


    @Autowired
    StudentsRepository studentsRepository;

    @Override
    public EntityModel<Article> process(EntityModel<Article> model) {
        Article article = model.getContent();
        if (article != null) {
            System.out.println(article.getStudentsWhoLiked());
            if (!article.getStudentsWhoLiked().contains(studentsRepository.findStudentById(7L))) {
                model.add(linkTo(methodOn(ArticlesController.class)
                        .rateArticle(article.getId())).withRel("like"));
            } else {
                model.add(linkTo(methodOn(ArticlesController.class)
                        .rateArticle(article.getId())).withRel("unlike"));
            }
        }
        return model;
    }
}
