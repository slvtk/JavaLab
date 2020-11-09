package ru.kpfu.hateoas_hw.models;

import lombok.*;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {

    @Id
    @GeneratedValue
    private Long id;

    @Column(unique=true)
    private String title;
    private String text;

    @OneToMany(mappedBy = "article")
    private List<Comment> comments;

    @OneToOne
    @JoinColumn(name = "test_id")
    private Test test;

    @ManyToMany(mappedBy = "likedArticles")
    private List<Student> studentsWhoLiked;

    @ManyToOne
    @JoinColumn(name = "author_id")
    private Student author;

    @Override
    public String toString() {
        return "Article{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}

