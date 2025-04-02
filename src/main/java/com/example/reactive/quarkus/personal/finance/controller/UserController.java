package com.example.reactive.quarkus.personal.finance.controller;

import com.example.reactive.quarkus.personal.finance.model.request.UserRequestDto;
import com.example.reactive.quarkus.personal.finance.model.response.UserResponseDto;
import com.example.reactive.quarkus.personal.finance.service.UserService;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.*;

import java.util.Set;

@Path("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GET
    @Path("/getUser/{userId}")
    public Uni<UserResponseDto> getUserById(@PathParam("userId") long userId) {
        return userService.getUserById(userId);
    }

    @GET
    @Path("/getAllUser")
    public Multi<Set<UserResponseDto>> getAllUser() {
        return userService.getAllUser();
    }

    @POST
    @Path("/createUser")
    public Uni<UserResponseDto> createUser(UserRequestDto userRequestDto) {
        return userService.createUser(userRequestDto);
    }

    @PUT
    @Path("/updateUser")
    public Uni<UserResponseDto> updateUser(UserRequestDto userRequestDto) {
        return userService.updateUser(userRequestDto);
    }

}
