package com.storage.cloud.service.impl;

import com.storage.cloud.domain.User;
import com.storage.cloud.domain.builder.UserBuilder;
import com.storage.cloud.dto.UserAuthDto;
import com.storage.cloud.service.UserAuthenticationService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserAuthenticationServiceImpl implements UserAuthenticationService {

    private static final Logger logger = LoggerFactory.getLogger(UserAuthenticationServiceImpl.class);

    private PasswordEncoder passwordEncoder;

    @Override
    public User registration(UserAuthDto userData) {
        //dev only, remove in production
        logger.debug(String.format("Registered user: email: %s | password: %s", userData.getEmail(), userData.getPassword()));

        return UserBuilder
                .create()
                .withEmail(userData.getEmail())
                .withPassword(passwordEncoder.encode(userData.getPassword()))
                .withAvatar("default")
                .build();
    }

    @Override
    public User authenticate(User user, UserAuthDto userAuthDto) {
        if (passwordEncoder.matches(userAuthDto.getPassword(), user.getPassword())) {
            return user;
        } else {
            return null;
        }
    }
}
