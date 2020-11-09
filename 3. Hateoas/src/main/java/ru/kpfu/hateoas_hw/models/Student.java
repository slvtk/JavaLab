package ru.kpfu.hateoas_hw.models;

import lombok.*;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String surname;
    private Integer age;
    private Integer rating;
    @Enumerated(value = EnumType.STRING)
    private VerificationStatus verificationStatus;

    @OneToMany(mappedBy = "student")
    private List<Comment> comments;

    @OneToMany(mappedBy = "student")
    private List<Question> questions;

    @OneToMany(mappedBy = "student")
    private List<Answer> answers;

    @OneToMany
    @JoinColumn(name = "author_id")
    private List<Article> articles;

    @ManyToMany
    @JoinTable(name = "liked_articles",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "article_id", referencedColumnName = "id"))
    private List<Article> likedArticles;

    @ManyToMany
    @JoinTable(name = "passed_tests",
            joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "test_id", referencedColumnName = "id"))
    private List<Test> passedTests;

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", rating=" + rating +
                ", verify=" + verificationStatus +
                '}';
    }

    public void changeRating(Test test) {
        if (!this.passedTests.contains(test)) {
            this.rating = this.rating + test.getComplexity() / 10;
        }
    }
}
