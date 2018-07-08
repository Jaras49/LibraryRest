package com.library.repository.rent;

import com.library.domain.book.Book;
import com.library.domain.rent.Rent;
import com.library.domain.user.User;
import com.library.exception.RentException;
import com.library.repository.book.BookDao;
import com.library.repository.title.TitleDao;
import com.library.repository.user.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotNull;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:ddl_auto_none.properties")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql.sql")
public class RentRepositoryTestSuite {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentRepositoryTestSuite.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private RentDao rentDao;
    @Autowired
    private TitleDao titleDao;
    @Autowired
    private RentRepository rentRepository;

    //TODO
    @Test
    public void shouldRentBooks() throws RentException {


        rentDao.findRents().forEach(System.out::println);


        assertEquals(0, userDao.findById(3L).get().getRents().size());
        assertEquals(2, rentDao.findAll().size());
        assertEquals(1, userDao.findById(2L).get().getRents().size());
        assertEquals(1, userDao.findById(1L).get().getRents().size());
        assertEquals(2, rentDao.findAll().size());
        assertEquals(3, userDao.findAll().size());
        assertEquals(2, titleDao.findAll().size());
        assertEquals(11, bookDao.findAll().size());

        rentRepository.rentBooks(3L, 7L, 15L);

        //Then
        assertEquals(1, userDao.findByFirstName("UserTest").size());
        assertEquals(3, userDao.findAll().size());
        assertEquals(3, rentDao.findAll().size());

        Book book = bookDao.findById(15L).get();
        assertEquals(Book.Status.RENTED,  book.getStatus());

        assertEquals(Book.Status.RENTED, bookDao.findById(7L).get().getStatus());

        User user = userDao.findById(3L).get();
        assertEquals("User", user.getFirstName());
        List<Rent> rents = user.getRents();

        assertEquals(2, rents.size());
        Book.@NotNull Status status = rents.get(0).getRentedBooks().get(0).getStatus();
        assertEquals(Book.Status.RENTED, status);
        assertEquals(2, rents.size());
        assertEquals(2, rents.get(0).getRentedBooks().size());
        assertEquals(2 , rents.get(1).getRentedBooks().size());

    }

}