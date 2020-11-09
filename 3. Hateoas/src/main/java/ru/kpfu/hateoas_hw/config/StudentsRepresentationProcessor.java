package ru.kpfu.hateoas_hw.config;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;
import ru.kpfu.hateoas_hw.controllers.StudentsController;
import ru.kpfu.hateoas_hw.models.Student;
import ru.kpfu.hateoas_hw.models.VerificationStatus;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class StudentsRepresentationProcessor implements RepresentationModelProcessor<EntityModel<Student>> {
    @Override
    public EntityModel<Student> process(EntityModel<Student> model) {
        Student student = model.getContent();
        if (student != null) {
            if (student.getVerificationStatus().equals(VerificationStatus.NOT_VERIFIED)) {
                model.add(linkTo(methodOn(StudentsController.class)
                        .verify(student.getId())).withRel("verify"));
            }
        }
        return model;
    }
}
