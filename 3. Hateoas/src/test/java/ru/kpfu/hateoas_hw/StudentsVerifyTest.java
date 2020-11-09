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
import ru.kpfu.hateoas_hw.models.VerificationStatus;
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
public class StudentsVerifyTest {
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
        when(studentsService.verify(7L)).thenReturn(verifiedStudent());
    }

    @Test
    public void verifyStudent() throws Exception{
        mockMvc.perform(put("/students/7/verify")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value(verifiedStudent().getName()))
                .andExpect(jsonPath("$.surname").value(verifiedStudent().getSurname()))
                .andExpect(jsonPath("$.age").value(verifiedStudent().getAge()))
                .andExpect(jsonPath("$.rating").value(verifiedStudent().getRating()))
                .andExpect(jsonPath("$.verificationStatus").value(verifiedStudent().getVerificationStatus().toString()))

                .andDo(document("student_verify", responseFields(
                        fieldWithPath("name").description("Имя"),
                        fieldWithPath("surname").description("Фамилия"),
                        fieldWithPath("age").description("Возраст"),
                        fieldWithPath("rating").description("Рейтинг"),
                        fieldWithPath("verificationStatus").description("Статус верификации")
                )));
    }

    private Student verifiedStudent(){
        return Student.builder()
                .name("Salavat")
                .surname("Nizamov")
                .rating(3000)
                .age(20)
                .verificationStatus(VerificationStatus.VERIFIED)
                .build();
    }

}
