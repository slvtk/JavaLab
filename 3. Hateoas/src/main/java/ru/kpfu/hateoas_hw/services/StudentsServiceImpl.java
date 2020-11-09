package ru.kpfu.hateoas_hw.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kpfu.hateoas_hw.models.Article;
import ru.kpfu.hateoas_hw.models.Student;
import ru.kpfu.hateoas_hw.models.Test;
import ru.kpfu.hateoas_hw.models.VerificationStatus;
import ru.kpfu.hateoas_hw.repositories.ArticlesRepository;
import ru.kpfu.hateoas_hw.repositories.StudentsRepository;
import ru.kpfu.hateoas_hw.repositories.TestsRepository;

import java.util.Optional;

@Service
public class StudentsServiceImpl implements StudentsService {
    @Autowired
    TestsRepository testsRepository;

    @Autowired
    StudentsRepository studentsRepository;

    @Autowired
    ArticlesRepository articlesRepository;

    @Override
    public Test passTest(Long testId) {
        Test test = testsRepository.findTestById(testId);
        Student student = studentsRepository.findStudentById(Integer.toUnsignedLong(7));
        if (!student.getPassedTests().contains(test)){
            student.changeRating(test);
            student.getPassedTests().add(test);
            System.out.println(student);
        }
        studentsRepository.save(student);
        return test;
    }

    @Override
    public Article rateArticle(Long articleId) {
        Optional<Article> article = articlesRepository.findById(articleId);
        Student student = studentsRepository.getOne(7L);
        if (article.isPresent()) {
            Article article1 = articlesRepository.getOne(articleId);
            if (student.getLikedArticles().contains(article1)) {
                student.getLikedArticles().remove(article1);
            } else {
                student.getLikedArticles().add(article1);
            }
            studentsRepository.save(student);
        }
        return article.orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Student verify(Long studentId) {
        Student student = studentsRepository.findById(studentId).orElseThrow(IllegalArgumentException::new);
        if (student.getVerificationStatus().equals(VerificationStatus.NOT_VERIFIED)){
            student.setVerificationStatus(VerificationStatus.VERIFIED);
            System.out.println("IMHEREER");
            studentsRepository.save(student);
            System.out.println(student);
        }
        System.out.println(student);
        return student;
    }
}
