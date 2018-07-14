package com.library.mapper;

import com.library.domain.user.User;
import com.library.domain.user.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserMapperTestSuite {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void shouldMapToUser() {

        //Given
        UserDto userDto = new UserDto(1L, "test", "name");

        //When
        User user = userMapper.mapToUser(userDto);

        //Then
        assertEquals("test", user.getFirstName());
        assertEquals("name", user.getLastName());
    }

    @Test
    public void shouldMapToUserDtoList() {

        //Given
        User user = new User("test", "lastName");
        User user1 = new User("test", "lastName");

        //When
        List<UserDto> userDtos = userMapper.mapToUserDtoList(Arrays.asList(user, user1));

        //Then
        assertEquals(2, userDtos.size());

        userDtos.forEach(userDto -> {
            assertEquals("test", userDto.getFirstName());
            assertEquals("lastName", userDto.getLastName());
        });
    }

    @Test
    public void shouldMapToUserDto() {

        //Given
        User user = new User("test", "name");

        //When
        UserDto userDto = userMapper.mapToUserDto(user);

        //Then
        assertEquals("test", userDto.getFirstName());
        assertEquals("name", userDto.getLastName());
    }
}