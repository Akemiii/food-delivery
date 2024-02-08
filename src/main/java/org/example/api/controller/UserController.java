package org.example.api.controller;


import lombok.RequiredArgsConstructor;
import org.example.api.dto.request.user.CreateUserRequest;
import org.example.api.dto.response.user.UserResponse;
import org.example.factory.UserDomainFactory;
import org.example.service.UserService;
import org.example.util.ObjectMapperUtil;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {
    private final UserService service;
    private final ObjectMapperUtil objectMapperUtil;
    private final UserDomainFactory userDomainFactory;

    @GetMapping("{userId}")
    public UserResponse get(@PathVariable UUID userId) {
        final var user = service.findById(userId);

        return objectMapperUtil.map(user, UserResponse.class);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse create(@RequestBody @Validated CreateUserRequest request) {
        final var user = service.create(
                userDomainFactory.toCreate(request)
        );

        return objectMapperUtil.map(user, UserResponse.class);
    }

}
