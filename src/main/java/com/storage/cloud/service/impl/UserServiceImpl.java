package com.storage.cloud.service.impl;

import com.storage.cloud.domain.User;
import com.storage.cloud.repository.UserRepository;
import com.storage.cloud.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public Iterable<User> getAllUsers() {
        return userRepository.findAll();
    }
}
