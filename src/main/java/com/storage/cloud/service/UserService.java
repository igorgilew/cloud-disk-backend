package com.storage.cloud.service;

import com.storage.cloud.domain.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByEmail(String email);

    User save(User user);

    Iterable<User> getAllUsers();

}
