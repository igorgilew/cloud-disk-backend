package com.storage.cloud.controller;

import com.storage.cloud.domain.User;
import com.storage.cloud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class UserController {

    private UserService userService;

    @GetMapping(path = "/users")
    public Iterable<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
