package com.library.controller;


import com.library.domain.title.TitleDto;
import com.library.domain.user.UserDto;
import com.library.mapper.TitleMapper;
import com.library.mapper.UserMapper;
import com.library.repository.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/library")
public class LibraryController {

    @Autowired
    private DbService dbService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TitleMapper titleMapper;


    @RequestMapping(method = RequestMethod.POST, value = "/createUser", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        dbService.createNewUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody TitleDto titleDto) {
        dbService.addBook(titleMapper.mapToTitle(titleDto));
    }
}
