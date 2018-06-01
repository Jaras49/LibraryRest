package com.library.controller;

import com.library.domain.user.UserDto;
import com.library.mapper.UserMapper;
import com.library.repository.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/library")
public class LibraryController {

    @Autowired
    private DbService dbService;
    @Autowired
    private UserMapper userMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/createUser")
    public void createUser(@RequestBody UserDto userDto) {
        dbService.createNewUser(userMapper.mapToUser(userDto));
    }
}
