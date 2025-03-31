package com.example.reactive.quarkus.personal.finance.service;

import com.example.reactive.quarkus.personal.finance.converter.UserConverter;
import com.example.reactive.quarkus.personal.finance.model.response.UserResponseDto;
import com.example.reactive.quarkus.personal.finance.repository.UserRepository;
import io.smallrye.mutiny.Uni;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserService {
    private final UserRepository userRepository;
    private final UserConverter userConverter;

    public UserService(UserRepository userRepository, UserConverter userConverter) {
        this.userRepository = userRepository;
        this.userConverter = userConverter;
    }

    public Uni<UserResponseDto> getUserById(long id) {
        return userRepository.getUserById(id)
                .map(userConverter::toDto);
    }
}
