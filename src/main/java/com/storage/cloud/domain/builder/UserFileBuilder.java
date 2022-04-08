package com.storage.cloud.domain.builder;

import com.storage.cloud.domain.UserFile;

import java.math.BigDecimal;

public class UserFileBuilder {

    private static final UserFileBuilder builder = new UserFileBuilder();

    private static UserFile userFile;

    private UserFileBuilder() {

    }

    public static UserFileBuilder create() {
        userFile = new UserFile();
        return builder;
    }

    public UserFileBuilder withName(String name) {
        userFile.setName(name);
        return builder;
    }

    public UserFileBuilder withType(String type) {
        userFile.setType(type);
        return builder;
    }

    public UserFileBuilder withAccessLink(String accessLink) {
        userFile.setAccessLink(accessLink);
        return builder;
    }

    public UserFileBuilder withSize(BigDecimal size) {
        userFile.setSize(size);
        return builder;
    }

    public UserFileBuilder withUserId(String userId) {
        userFile.setUserId(userId);
        return builder;
    }

    public UserFileBuilder withParentFile(UserFile parentFile) {
        userFile.setParentFile(parentFile);
        return builder;
    }

    public UserFile build() {
        return userFile;
    }
}
