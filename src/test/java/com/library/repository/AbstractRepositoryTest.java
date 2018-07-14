package com.library.repository;

import com.library.repository.book.BookDao;
import com.library.repository.rent.RentDao;
import com.library.repository.title.TitleDao;
import com.library.repository.user.UserDao;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:ddl_auto_none.properties")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:dbInitData.sql")
public abstract class AbstractRepositoryTest {

    @Autowired
    protected TitleDao titleDao;
    @Autowired
    protected BookDao bookDao;
    @Autowired
    protected UserDao userDao;
    @Autowired
    protected RentDao rentDao;
}