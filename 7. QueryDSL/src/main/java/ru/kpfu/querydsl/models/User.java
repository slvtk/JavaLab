package ru.kpfu.querydsl.models;

import com.querydsl.core.annotations.QueryEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;
import javax.persistence.Id;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@QueryEntity
@Document(collection = "users")
public class User {
    @Id
    private String _id;
    private String name;
    private String surname;
    private Integer age;
}
