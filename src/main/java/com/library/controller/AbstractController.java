package com.library.controller;

import com.library.mapper.BookMapper;
import com.library.mapper.RentMapper;
import com.library.mapper.TitleMapper;
import com.library.mapper.UserMapper;
import com.library.repository.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
abstract class AbstractController {

    @Autowired
    protected RentMapper rentMapper;
    @Autowired
    protected DbService dbService;
    @Autowired
    protected UserMapper userMapper;
    @Autowired
    protected TitleMapper titleMapper;
    @Autowired
    protected BookMapper bookMapper;
}
