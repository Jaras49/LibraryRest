package com.library.repository;

import com.library.repository.book.BookDao;
import com.library.repository.rent.RentDao;
import com.library.repository.title.TitleDao;
import com.library.repository.user.UserDao;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:ddl_auto_update.properties")
public abstract class AbstractDaoTest {

    @Autowired
    protected BookDao bookDao;
    @Autowired
    protected TitleDao titleDao;
    @Autowired
    protected UserDao userDao;
    @Autowired
    protected RentDao rentDao;

    @After
    public void tearDown() throws Exception {
        bookDao.deleteAll();
        rentDao.deleteAll();
        userDao.deleteAll();
        titleDao.deleteAll();
    }
}
