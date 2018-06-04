package com.library.domain.user;

import com.library.repository.user.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDaoTestSuite {

    @Autowired
    private UserDao userDao;

    @Test
    public void shouldCreateNewUser() {

        //Given
        User user = new User(1L, "Andrzej", "Nowak", new Date());
        User user1 = new User(2L, "Andrzej", "Kowal", new Date());

        //When
        userDao.save(user);
        userDao.save(user1);

        //Then
        assertEquals(2, userDao.findByFirstName("Andrzej").size());
        assertEquals(1, userDao.findByLastName("Nowak").size());

    }

}