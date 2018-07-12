package com.library.repository.book;

import com.library.domain.book.Book;
import com.library.domain.title.Title;
import com.library.exception.BookRepositoryException;
import com.library.repository.AbstractRepositoryTest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class BookRepositoryTestSuite extends AbstractRepositoryTest {

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void shouldAddBook() {

        //Given
        assertEquals(11, bookDao.findAll().size());
        assertEquals(2, titleDao.findAll().size());
        assertEquals(5, titleDao.findByTitleNameAndAuthorAndYearOfPublication("BookBook", "Author", 1992)
                .get().getBooks().size());

        Title title = new Title("BookBook", "Author", 1992);

        //When
        bookRepository.addBook(title);

        //Then
        assertEquals(12, bookDao.findAll().size());
        assertEquals(2, titleDao.findAll().size());

        assertEquals(6, titleDao.findByTitleNameAndAuthorAndYearOfPublication("BookBook", "Author", 1992)
                .get().getBooks().size());
    }

    @Test
    public void shouldAddBookAndTitle() {

        //Given
        Title title = new Title("test", "test", 2005);

        //When
        bookRepository.addBook(title);

        //Then
        assertEquals(3, titleDao.findAll().size());
        assertEquals(12, bookDao.findAll().size());

        assertEquals(1, titleDao.findByTitleNameAndAuthorAndYearOfPublication("test", "test", 2005)
                .get().getBooks().size());
    }

    @Test
    public void shouldUpdateBookStatus() throws BookRepositoryException {

        //When
        bookRepository.updateBookStatus(5L, Book.Status.RENTED);
        bookRepository.updateBookStatus(6L, Book.Status.LOST);
        bookRepository.updateBookStatus(7L, Book.Status.DESTROYED);
        bookRepository.updateBookStatus(8L, Book.Status.AVAILABLE);

        //Then
        assertEquals(Book.Status.RENTED, bookDao.findById(5L).get().getStatus());
        assertEquals(Book.Status.LOST, bookDao.findById(6L).get().getStatus());
        assertEquals(Book.Status.DESTROYED, bookDao.findById(7L).get().getStatus());
        assertEquals(Book.Status.AVAILABLE, bookDao.findById(8L).get().getStatus());
    }

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    public void shouldThrowException() throws BookRepositoryException {

        expectedException.expect(BookRepositoryException.class);
        expectedException.expectMessage(BookRepositoryException.ERR_INVALID_BOOK_ID);

        bookRepository.updateBookStatus(100L, Book.Status.DESTROYED);
    }
}