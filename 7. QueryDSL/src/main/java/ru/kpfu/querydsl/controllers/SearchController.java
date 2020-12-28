package ru.kpfu.querydsl.controllers;

import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.kpfu.querydsl.dto.UserDto;
import ru.kpfu.querydsl.models.User;
import ru.kpfu.querydsl.repositories.UserRepository;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
public class SearchController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users/search")
    public ResponseEntity<List<UserDto>> searchByPredicate(@QuerydslPredicate(root = User.class) Predicate predicate) {
        return ResponseEntity.ok(StreamSupport.stream(userRepository.findAll(predicate).spliterator(), true)
                .map(user ->
                        UserDto.builder().name(user.getName())
                                .surname(user.getSurname())
                                .build()).collect(Collectors.toList()));
    }
}
