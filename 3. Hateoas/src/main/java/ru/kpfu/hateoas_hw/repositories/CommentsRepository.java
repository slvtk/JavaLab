package ru.kpfu.hateoas_hw.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.kpfu.hateoas_hw.models.Comment;

public interface CommentsRepository extends JpaRepository<Comment, Long> {
}
