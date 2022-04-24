package com.storage.cloud.service;

import com.storage.cloud.domain.User;
import com.storage.cloud.dto.UserAuthDto;

public interface UserAuthenticationService {
    User registration(UserAuthDto userData);

    User authenticate(User user, String password);

}
