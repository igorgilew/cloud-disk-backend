package com.storage.cloud.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String id;
    private String avatar;
    private Integer diskSpace;
    private String email;
    private Integer usedSpace;
}
