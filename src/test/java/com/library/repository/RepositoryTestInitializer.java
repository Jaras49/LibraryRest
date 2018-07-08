package com.library.repository;

import com.library.domain.book.Book;
import com.library.domain.rent.Rent;
import com.library.domain.title.Title;
import com.library.domain.user.User;
import com.library.repository.book.BookRepository;
import com.library.repository.rent.RentDao;
import com.library.repository.rent.RentRepository;
import com.library.repository.title.TitleDao;
import com.library.repository.user.UserDao;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @deprecated
 */
@DataJpaTest
@RunWith(SpringRunner.class)
public abstract class RepositoryTestInitializer {

    private static final Logger LOGGER = LoggerFactory.getLogger(RepositoryTestInitializer.class);

    @Autowired
    private static TitleDao titleDao;
    @Autowired
    private static UserDao userDao;
    @Autowired
    private static RentDao rentDao;
/**
    @BeforeClass
    public static void initializeDatabase() {

        LOGGER.info("Initializing database test data");

        //Init new users
        User testUser = new User("testUser", "lastNameOfTestUser");
        User testUser1 = new User("userTest", "testUserLastName");
        User testUser2 = new User("userUser", "SomeUser");

        //Init Books
        Title title = new Title("HelloWord", "JavaProgrammer", 2016);
        Title title1 = new Title("TestTitle", "TestAuthor", 1992);

        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        Book book4 = new Book();
        Book book5 = new Book();

        Book book6 = new Book();
        Book book7 = new Book();
        Book book8 = new Book();
        Book book9 = new Book();

        book1.setStatus(Book.Status.DESTROYED);
        book2.setStatus(Book.Status.LOST);
        book6.setStatus(Book.Status.LOST);

        title.getBooks().addAll(Arrays.asList(book1, book2, book3, book4, book5));
        book3.setTitle(title);
        book4.setTitle(title);
        book5.setTitle(title);

        title1.getBooks().addAll(Arrays.asList(book6, book7, book8, book9));
        book6.setTitle(title1);
        book7.setTitle(title1);
        book8.setTitle(title1);
        book9.setTitle(title1);

        //Init rents
        Rent rent = new Rent(LocalDate.now().plusMonths(2));
        Rent rent1 = new Rent(LocalDate.now().plusMonths(1));

        rent.setUser(testUser);
        rent.getRentedBooks().addAll(Arrays.asList(book4, book5, book9));
        book4.setRent(rent);
        book4.setStatus(Book.Status.RENTED);
        book5.setRent(rent);
        book5.setStatus(Book.Status.RENTED);
        book9.setRent(rent);
        book9.setStatus(Book.Status.RENTED);
        testUser.getRents().add(rent);

        rent1.setUser(testUser1);
        rent1.getRentedBooks().addAll(Arrays.asList(book3, book8));
        book3.setStatus(Book.Status.RENTED);
        book3.setRent(rent1);
        book8.setStatus(Book.Status.RENTED);
        book8.setRent(rent1);
        testUser1.getRents().add(rent1);

        //Persist
        userDao.save(testUser);
        userDao.save(testUser1);
        userDao.save(testUser2);

        titleDao.save(title);
        titleDao.save(title1);

        rentDao.save(rent);
        rentDao.save(rent1);

        LOGGER.info("Test data initialized successfully");
    }
    */
}