package com.library.mapper;

import com.library.domain.user.User;
import com.library.domain.user.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public User mapToUser(UserDto userDto) {

        return new User (userDto.getFirstName(), userDto.getLastName());
    }
}
