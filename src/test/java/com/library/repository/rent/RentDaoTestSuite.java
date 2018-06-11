package com.library.repository.rent;

import com.library.domain.book.Book;
import com.library.domain.rent.Rent;
import com.library.domain.title.Title;
import com.library.domain.user.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RentDaoTestSuite {

    @Autowired
    private RentDao rentDao;

    @Test
    public void testRent() {

        //Given
        Title title = new Title("JAVA CORE", "xyz", 2000);
        Book book1 = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        Book book4 = new Book();

        book1.setTitle(title);
        book2.setTitle(title);
        book3.setTitle(title);
        book4.setTitle(title);

        Rent rent = new Rent(LocalDate.of(2019, 12,15));

        book1.setRent(rent);
        book2.setRent(rent);
        book3.setRent(rent);
        book4.setRent(rent);

        List<Book> list = new ArrayList<>();
        list.add(book1);
        list.add(book2);
        list.add(book3);
        list.add(book4);

        User user = new User("Mieczysław", "Bolek");

        user.getRents().add(rent);
        rent.setUser(user);
        rent.getRentedBooks().addAll(list);

        title.getBooks().addAll(list);


        //When
        rentDao.save(rent);
       //rentDao.deleteById(282L);

        //Then

    }
}