package com.library.domain.user;

import com.library.repository.user.UserDao;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class UserDaoTestSuite {

    @Autowired
    private UserDao userDao;

    @Before
    public void setUp() {

        //Given
        User user = new User(1L, "Andrzej", "Nowak", new Date());
        User user1 = new User(2L, "Andrzej", "Kowal", new Date());

        userDao.save(user);
        userDao.save(user1);
    }

    @After
    public void tearDown() throws Exception {

        //CleanUp
        userDao.deleteAll();
    }

    @Test
    public void shouldFindByFirstName() {

        //When
        List<User> users = userDao.findByFirstName("Andrzej");

        //Then
        assertEquals(2, users.size());
    }

    @Test
    public void shouldFindByLastName() {

        //When
        List<User> users = userDao.findByLastName("Nowak");

        //Then
        assertEquals(1, users.size());
    }
}