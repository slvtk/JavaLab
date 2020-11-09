package ru.kpfu.hateoas_hw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.kpfu.hateoas_hw.models.*;
import ru.kpfu.hateoas_hw.repositories.*;

import static java.util.Arrays.asList;


@SpringBootApplication
public class HateoasHwApplication {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(HateoasHwApplication.class, args);
        StudentsRepository studentsRepository = context.getBean(StudentsRepository.class);
        TestsRepository testsRepository = context.getBean(TestsRepository.class);
        ArticlesRepository articlesRepository = context.getBean(ArticlesRepository.class);
        CommentsRepository commentsRepository = context.getBean(CommentsRepository.class);
        AnswersRepository answersRepository = context.getBean(AnswersRepository.class);
        QuestionsRepository questionsRepository = context.getBean(QuestionsRepository.class);



        Test animals = Test.builder()
                .title("Animals")
                .questions("1 test questions")
                .complexity(2500)
                .build();
        Test java = Test.builder()
                .title("Java")
                .questions("2 test questions")
                .complexity(3500)
                .build();
        Test sql = Test.builder()
                .title("Базы данных")
                .questions("3 test questions")
                .complexity(5500)
                .build();

        testsRepository.saveAll(asList(animals, java, sql));

        Article firstArt = Article.builder()
                .title("Java is simple")
                .text("first text")
                .test(java)
                .build();
        Article secondArt = Article.builder()
                .title("SQL is hard")
                .text("Second text")
                .test(sql)
                .build();
        Article thirdArt = Article.builder()
                .title("Animals are beautiful")
                .text("Third text")
                .test(animals)
                .build();

        articlesRepository.saveAll(asList(firstArt, secondArt, thirdArt));

        Student salavat = Student.builder()
                .name("Salavat")
                .surname("Nizamov")
                .articles(asList(firstArt,secondArt))
                .likedArticles(asList(thirdArt))
                .passedTests(asList(sql))
                .age(20)
                .rating(3000)
                .verificationStatus(VerificationStatus.NOT_VERIFIED)
                .build();

        Student ivan = Student.builder()
                .name("Ivan")
                .surname("Ivanov")
                .articles(asList(thirdArt))
                .likedArticles(asList(secondArt))
                .passedTests(asList(java))
                .age(15)
                .rating(4000)
                .verificationStatus(VerificationStatus.NOT_VERIFIED)
                .build();

        Student egor = Student.builder()
                .name("Egor")
                .surname("Egorov")
                .passedTests(asList(animals))
                .age(27)
                .rating(6000)
                .verificationStatus(VerificationStatus.NOT_VERIFIED)
                .build();

        studentsRepository.saveAll(asList(salavat, ivan, egor));


        Comment firstComm = Comment.builder()
                .article(secondArt)
                .student(salavat)
                .title("Cool")
                .text("so cool")
                .build();
        Comment secondComm = Comment.builder()
                .article(firstArt)
                .student(ivan)
                .title("Bad")
                .text("so bad")
                .build();
        commentsRepository.saveAll(asList(firstComm, secondComm));

        Question firstQuestion = Question.builder()
                .student(egor)
                .text("how?")
                .build();
        Question secondQuestion = Question.builder()
                .student(salavat)
                .text("how2?")
                .build();
        questionsRepository.saveAll(asList(firstQuestion, secondQuestion));

        Answer firstAnswer = Answer.builder()
                .question(firstQuestion)
                .student(salavat)
                .text("idk")
                .build();
        Answer secondAnswer = Answer.builder()
                .question(firstQuestion)
                .student(ivan)
                .text("i know")
                .build();
        Answer thirdAnswer = Answer.builder()
                .question(secondQuestion)
                .student(egor)
                .text("i know but its hard")
                .build();
        answersRepository.saveAll(asList(firstAnswer, secondAnswer, thirdAnswer));
    }
}
