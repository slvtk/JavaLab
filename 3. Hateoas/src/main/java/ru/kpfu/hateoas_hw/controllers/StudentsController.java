package ru.kpfu.hateoas_hw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.hateoas_hw.models.Student;
import ru.kpfu.hateoas_hw.services.StudentsService;

@RepositoryRestController
public class StudentsController {
    @Autowired
    private StudentsService studentsService;

    @RequestMapping(value = "/students/{student-id}/verify", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> verify(@PathVariable("student-id") Long studentId) {
        Student student = studentsService.verify(studentId);
        System.out.println(student);
        return  ResponseEntity.ok(
                EntityModel.of(student));
    }
}
