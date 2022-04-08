package com.storage.cloud.repository;

import com.storage.cloud.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {
    Optional<User> findUserByEmail(String email);
}
