package com.library.mapper;

import com.library.domain.user.User;
import com.library.domain.user.UserDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class UserMapper {

    public User mapToUser(UserDto userDto) {

        return new User(userDto.getFirstName(), userDto.getLastName());
    }

    public List<UserDto> mapToUserDtoList(List<User> users) {

        return users.stream()
                .map(user -> new UserDto(
                        user.getId(),
                        user.getFirstName(),
                        user.getLastName()))
                .collect(toList());
    }
}
