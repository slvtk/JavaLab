package ru.kpfu.hateoas_hw;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import ru.kpfu.hateoas_hw.controllers.ArticlesController;
import ru.kpfu.hateoas_hw.models.Article;
import ru.kpfu.hateoas_hw.models.Student;
import ru.kpfu.hateoas_hw.models.VerificationStatus;
import ru.kpfu.hateoas_hw.repositories.ArticlesRepository;
import ru.kpfu.hateoas_hw.repositories.StudentsRepository;
import ru.kpfu.hateoas_hw.repositories.TestsRepository;
import ru.kpfu.hateoas_hw.services.StudentsService;

import java.util.ArrayList;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
public class ArticleRateTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    TestsRepository testsRepository;

    @Autowired
    StudentsRepository studentsRepository;

    @MockBean
    private StudentsService studentsService;

    @Autowired
    ArticlesRepository articlesRepository;

    @BeforeEach
    public void setUp() {
        Student student = Student.builder()
                .id(7L)
                .name("Salavat")
                .surname("Nizamov")
                .age(20)
                .rating(3000)
                .verificationStatus(VerificationStatus.NOT_VERIFIED)
                .build();
        studentsRepository.save(student);
        System.out.println("Student in BeforeEach " + studentsRepository.findById(7L).toString());
        when(studentsService.rateArticle(4L)).thenReturn(articleThatRated());
    }

    @Test
    public void articlesRateTest() throws Exception {
        mockMvc.perform(put("/articles/4/rate")).andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.title").value(articleThatRated().getTitle()))
                .andExpect(jsonPath("$.text").value(articleThatRated().getText()))
                .andExpect(jsonPath("$._links.like.href").value("http://localhost:8080/articles/4/rate"))
                .andDo(document("article_rate", responseFields(
                        fieldWithPath("title").description("Название поста"),
                        fieldWithPath("text").description("Текст поста"),
                        fieldWithPath("_links.like.href").description("Ссылка на оценку")
                )));
    }

    private Article articleThatRated() {
        return Article.builder()
                .id(4L)
                .title("Some title")
                .text("Some text")
                .studentsWhoLiked(asList(studentsRepository.findById(7L).orElse(new Student())))
                .build();
    }

}
