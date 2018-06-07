package com.library.mapper;

import com.library.domain.user.User;
import com.library.domain.user.UserDto;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserMapper {

    public User mapToUser(UserDto userDto) {
        //userDto.getId(), userDto.getFirstName(), userDto.getLastName(), new Date()
        return new User("Mietek", "Jaromin");
    }
}
