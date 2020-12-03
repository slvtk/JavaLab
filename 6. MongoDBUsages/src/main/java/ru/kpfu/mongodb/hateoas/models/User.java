package ru.kpfu.mongodb.hateoas.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

@Data
@AllArgsConstructor
@Document(collection = "users")
public class User {
    @Id
    private String _id;
    private Integer age;
    private String name;
    private String surname;
}
