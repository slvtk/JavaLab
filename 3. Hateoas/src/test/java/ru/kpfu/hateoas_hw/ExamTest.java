package ru.kpfu.hateoas_hw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.kpfu.hateoas_hw.models.Student;
import ru.kpfu.hateoas_hw.repositories.StudentsRepository;
import ru.kpfu.hateoas_hw.repositories.TestsRepository;
import ru.kpfu.hateoas_hw.services.StudentsService;
import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.payload.PayloadDocumentation.fieldWithPath;
import static org.springframework.restdocs.payload.PayloadDocumentation.responseFields;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@SpringBootTest
@AutoConfigureRestDocs(outputDir = "target/snippets")
public class ExamTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TestsRepository testsRepository;

    @Autowired
    StudentsRepository studentsRepository;

    @MockBean
    private StudentsService studentsService;

    @BeforeEach
    public void setUp() {
        when(studentsService.passTest(1L)).thenReturn(studentWhoPassed());
    }

    @Test
    public void passingExamTest() throws Exception{
        mockMvc.perform(put("/tests/1/pass")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(studentWhoPassed().getTitle()))
                .andExpect(jsonPath("$.questions").value(studentWhoPassed().getQuestions()))
                .andExpect(jsonPath("$.complexity").value(studentWhoPassed().getComplexity()))

                .andDo(document("pass_test", responseFields(
                        fieldWithPath("title").description("Название теста"),
                        fieldWithPath("complexity").description("Сложность теста"),
                        fieldWithPath("questions").description("Вопросы теста")
                )));
    }

    private ru.kpfu.hateoas_hw.models.Test studentWhoPassed(){
        return ru.kpfu.hateoas_hw.models.Test.builder()
                .id(1L)
                .title("Animals")
                .complexity(2500)
                .questions("Tasks for 1 test")
                .studentsWhoPassed(asList(studentsRepository.findStudentById(7L)))
                .build();
    }

}
