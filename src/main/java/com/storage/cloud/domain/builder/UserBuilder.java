package com.storage.cloud.domain.builder;

import com.storage.cloud.domain.User;
import com.storage.cloud.domain.UserFile;

import java.util.List;

public class UserBuilder {

    private static final UserBuilder builder = new UserBuilder();

    private final Integer diskspace = 30*1024*1024;

    private static User user;

    private UserBuilder() {}

    public static UserBuilder create() {
        user = new User();
        return builder;
    }

    public UserBuilder withEmail(String email) {
        user.setEmail(email);
        return builder;
    }

    public UserBuilder withPassword(String password) {
        user.setPassword(password);
        return builder;
    }

    public UserBuilder withAvatar(String avatar) {
        user.setAvatar(avatar);
        return builder;
    }

    public UserBuilder withFile(UserFile file) {
        user.getFiles().add(file);
        return builder;
    }

    public UserBuilder withFiles(List<UserFile> files) {
        user.getFiles().addAll(files);
        return builder;
    }

    public User build() {
        user.setDiskSpace(diskspace);
        return user;
    }

}
