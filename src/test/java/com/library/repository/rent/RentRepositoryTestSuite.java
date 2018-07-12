package com.library.repository.rent;

import com.library.domain.book.Book;
import com.library.domain.rent.Rent;
import com.library.domain.user.User;
import com.library.exception.RentException;
import com.library.repository.book.BookDao;
import com.library.repository.user.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.LongStream;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestPropertySource("classpath:ddl_auto_none.properties")
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:sql.sql")
public class RentRepositoryTestSuite {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private RentDao rentDao;
    @Autowired
    private RentRepository rentRepository;

    @Test
    public void shouldRentBooks() throws RentException {

        //When
        rentRepository.rentBooks(3L, 7L, 15L);

        //Then
        assertEquals(1, userDao.findByFirstName("UserTest").size());
        assertEquals(3, userDao.findAll().size());
        assertEquals(3, rentDao.findAll().size());

        Book book = bookDao.findById(15L).get();
        assertEquals(Book.Status.RENTED, book.getStatus());

        assertEquals(Book.Status.RENTED, bookDao.findById(7L).get().getStatus());

        User user = userDao.findById(3L).get();
        assertEquals("User", user.getFirstName());
        List<Rent> rents = user.getRents();

        assertEquals(2, rents.size());
        Book.@NotNull Status status = rents.get(0).getRentedBooks().get(0).getStatus();
        assertEquals(Book.Status.RENTED, status);
        assertEquals(2, rents.get(0).getRentedBooks().size());
        assertEquals(2, rents.get(1).getRentedBooks().size());
    }

    @Test
    public void shouldReturnRent() throws RentException {

        //When
        rentRepository.returnRent(33L);

        //Then
        assertEquals(1, rentDao.findAll().size());
        assertFalse(rentDao.findById(33L).isPresent());

        assertEquals(0, userDao.findById(1L).get().getRents().size());

        LongStream.of(8, 9, 11, 12)
                .mapToObj(value -> bookDao.findById(value).get())
                .forEach(book -> {
                    assertNull(book.getRent());
                    assertEquals(Book.Status.AVAILABLE, book.getStatus());
                });
    }

    @Test
    public void shouldReturnBooks() {

        //When
        rentRepository.returnBooks(12L, 13L);

        //Then
        assertEquals(1, rentDao.findAll().size());
        assertEquals(3, rentDao.findById(33L).get().getRentedBooks().size());

        LongStream.of(12, 13)
                .mapToObj(value -> bookDao.findById(value).get())
                .forEach(book -> {
                    assertNull(book.getRent());
                    assertEquals(Book.Status.AVAILABLE, book.getStatus());
                });
    }

    @Test
    public void shouldThrowRentException() {

        try {
            rentRepository.rentBooks(100L, 5L, 6L);
        } catch (RentException e) {
            assertEquals(RentException.INVALID_USER_ID, e.getMessage());
        }
        try {
            rentRepository.rentBooks(1L, 100L, 150L);
        } catch (RentException e) {
            assertEquals(RentException.NOTHING_RENTED, e.getMessage());
        }try {
            rentRepository.returnRent(100L);
        } catch (RentException e) {
            assertEquals(RentException.INVALID_RENT_ID, e.getMessage());
        }
    }
}