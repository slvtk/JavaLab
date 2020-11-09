package ru.kpfu.hateoas_hw.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Test {
    @Id
    @GeneratedValue
    public Long id;
    private String questions;
    private String title;
    private Integer complexity;

    @OneToOne(mappedBy = "test")
    private Article article;

    @ManyToMany(mappedBy = "passedTests")
    private List<Student> studentsWhoPassed;

    @Override
    public String toString() {
        return "Test{" +
                "id=" + id +
                ", questions='" + questions + '\'' +
                ", title='" + title + '\'' +
                ", complexity=" + complexity +
                ", article=" + article +
                '}';
    }
}
