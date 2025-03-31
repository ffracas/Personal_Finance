package com.example.reactive.quarkus.personal.finance.controller;

import com.example.reactive.quarkus.personal.finance.model.response.UserResponseDto;
import com.example.reactive.quarkus.personal.finance.service.UserService;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;

@Path("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/getUser/{userId}")
    public Uni<UserResponseDto> getUserById(long userId) {
        return userService.getUserById(userId);
    }
}
