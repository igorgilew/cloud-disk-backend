package com.storage.cloud.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Document
@Data
@NoArgsConstructor
public class UserFile {
    @Id
    private String id;

    private String name;

    private String type;

    private String accessLink;

    private BigDecimal size;

    private String userId;

    @DBRef
    private UserFile parentFile;
}
