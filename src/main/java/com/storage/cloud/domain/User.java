package com.storage.cloud.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
@Data
public class User {
    @Id
    private String id;

    @Indexed(unique = true)
    private String email;

    private String password;

    private Integer diskSpace;

    private Integer usedSpace = 0;

    private String avatar;

    private boolean enabled = true;

    @DBRef
    private List<UserFile> files;

    public User() {
        files = new ArrayList<>();
    }
}
