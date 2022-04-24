package com.storage.cloud.mapper;

import com.storage.cloud.domain.User;
import com.storage.cloud.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements ModelMapper<User, UserDto>{
    @Override
    public UserDto getDto(User entity) {

        UserDto userDto = new UserDto();
        userDto.setId(entity.getId());
        userDto.setDiskSpace(entity.getDiskSpace());
        userDto.setEmail(entity.getEmail());
        userDto.setUsedSpace(entity.getUsedSpace());
        userDto.setAvatar(entity.getAvatar());

        return userDto;
    }
}
