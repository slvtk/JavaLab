package ru.kpfu.hateoas_hw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kpfu.hateoas_hw.services.StudentsService;

@RepositoryRestController
public class ArticlesController {

    @Autowired
    private StudentsService studentsService;

    @RequestMapping(value = "/articles/{article-id}/rate", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> rateArticle(@PathVariable("article-id") Long articleId) {
        return ResponseEntity.ok(
                EntityModel.of(studentsService.rateArticle(articleId)));
    }
}
