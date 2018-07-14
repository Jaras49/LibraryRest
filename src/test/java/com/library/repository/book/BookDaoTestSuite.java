package com.library.repository.book;

import com.library.domain.book.Book;
import com.library.repository.AbstractDaoTest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class BookDaoTestSuite extends AbstractDaoTest {

    @Before
    public void setUp() {

        //Given
        Book book = new Book();
        book.setStatus(Book.Status.RENTED);
        Book book1 = new Book();
        book1.setStatus(Book.Status.RENTED);
        Book book2 = new Book();
        book2.setStatus(Book.Status.DESTROYED);
        Book book3 = new Book();
        book3.setStatus(Book.Status.LOST);
        Book book4 = new Book();
        Book book5 = new Book();
        Book book6 = new Book();

        bookDao.save(book);
        bookDao.save(book1);
        bookDao.save(book2);
        bookDao.save(book3);
        bookDao.save(book4);
        bookDao.save(book5);
        bookDao.save(book6);
        assertEquals(7, bookDao.findAll().size());
    }

    @Test
    public void shouldFindRented() {

        //When
        List<Book> books = bookDao.findRented();

        //Then
        assertEquals(2, books.size());
    }

    @Test
    public void shouldFindAvailable() {

        //When
        List<Book> availableBooks = bookDao.findAvailable();

        //Then
        assertEquals(3, availableBooks.size());
    }

    @Test
    public void shouldFindLost() {

        //When
        List<Book> lostBooks = bookDao.findLost();

        //Then
        assertEquals(1, lostBooks.size());
    }

    @Test
    public void shouldFindDestroyed() {

        //When
        List<Book> destroyedBooks = bookDao.findDestroyed();

        //Then
        assertEquals(1, destroyedBooks.size());
    }
}