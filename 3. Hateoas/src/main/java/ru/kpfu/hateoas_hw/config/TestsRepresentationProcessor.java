package ru.kpfu.hateoas_hw.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.kpfu.hateoas_hw.controllers.TestsController;
import ru.kpfu.hateoas_hw.models.Test;
import ru.kpfu.hateoas_hw.repositories.StudentsRepository;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TestsRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Test>> {

    @Autowired
    private StudentsRepository studentsRepository;

    @Override
    public EntityModel<Test> process(EntityModel<Test> model) {
        Test test = model.getContent();
        if (test != null && !test.getStudentsWhoPassed().contains(studentsRepository.findStudentById(Integer.toUnsignedLong(7)))) {
            model.add(linkTo(methodOn(TestsController.class)
                            .passTest(test.getId())).withRel("pass"));
        }
        return model;
    }
}