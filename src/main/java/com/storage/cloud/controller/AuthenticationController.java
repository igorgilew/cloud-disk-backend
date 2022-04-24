package com.storage.cloud.controller;

import com.storage.cloud.domain.User;
import com.storage.cloud.dto.UserAuthDto;
import com.storage.cloud.dto.UserDto;
import com.storage.cloud.mapper.UserMapper;
import com.storage.cloud.security.JwtUtil;
import com.storage.cloud.service.UserAuthenticationService;
import com.storage.cloud.service.UserService;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
@AllArgsConstructor
public class AuthenticationController {

    private static final Logger logger = LoggerFactory.getLogger(AuthenticationController.class);

    private UserService userService;

    private UserAuthenticationService userAuthenticationService;

    private UserMapper userMapper;

    private JwtUtil jwtUtil;

    @PostMapping("/registration")
    public ResponseEntity<?> registration(@RequestBody UserAuthDto userData) {

        Optional<User> candidate = userService.findByEmail(userData.getEmail());

        if (candidate.isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Пользователь с email: " + userData.getEmail() +
                    " уже зарегистрирован в системе.");
        }

        User user = userAuthenticationService.registration(userData);

        user = userService.save(user);

        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UserAuthDto authData) {
        return baseLogin(authData.getEmail(), authData.getPassword());
    }

    @GetMapping("/login")
    public ResponseEntity<?> loginByToken(HttpServletRequest request) {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String jwt = authorizationHeader.replace("Bearer ", "");

        if (jwtUtil.isTokenValid(jwt)) {
            String email = jwtUtil.getEmailFromToken(jwt);
            String password = jwtUtil.getPasswordFromToken(jwt);

            return baseLogin(email, password);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    private ResponseEntity<?> baseLogin(String email, String password) {
        Optional<User> candidate = userService.findByEmail(email);

        User authenticatedUser = candidate.map(user -> userAuthenticationService.authenticate(user, password))
                .orElseThrow(()-> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid login or password"));

        //генерация jwt, упаковка в headers
        String jwt = jwtUtil.generateToken(authenticatedUser);

        UserDto userDto = userMapper.getDto(authenticatedUser);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + jwt)
                .body(userDto);
    }

}
