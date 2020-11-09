package ru.kpfu.hateoas_hw.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.kpfu.hateoas_hw.services.StudentsService;

@RepositoryRestController
public class TestsController {

    @Autowired
    private StudentsService studentsService;

    @RequestMapping(value = "/tests/{test-id}/pass", method = RequestMethod.PUT)
    public @ResponseBody
    ResponseEntity<?> passTest(@PathVariable("test-id") Long testId) {
        return ResponseEntity.ok(
                EntityModel.of(studentsService.passTest(testId)));
    }
}
