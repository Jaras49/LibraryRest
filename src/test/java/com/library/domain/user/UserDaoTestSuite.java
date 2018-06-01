package com.library.domain.user;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDaoTestSuite {

    @Autowired
    UserDao userDao;

    @Test
    public void shouldCreateNewUser() {

        //Given
        User user = new User(1L, "Andrzej", "Godwin", new Date());

        //When
        userDao.save(user);

        //Then
    }

}