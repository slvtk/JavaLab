package ru.kpfu.hateoas_hw.models;

import lombok.*;

import javax.persistence.*;

@ToString(exclude = "article")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private String text;

    @ManyToOne
    @JoinColumn(name = "article_id")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;
}
