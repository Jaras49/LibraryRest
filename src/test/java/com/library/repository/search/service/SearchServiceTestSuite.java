package com.library.repository.search.service;

import com.library.domain.book.Book;
import com.library.domain.rent.Rent;
import com.library.domain.title.Title;
import com.library.domain.user.User;
import com.library.repository.book.BookDao;
import com.library.repository.rent.RentDao;
import com.library.repository.title.TitleDao;
import com.library.repository.user.UserDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class SearchServiceTestSuite {

    @InjectMocks
    private SearchService searchService;
    @Mock
    private TitleDao titleDao;
    @Mock
    private BookDao bookDao;
    @Mock
    private UserDao userDao;
    @Mock
    private RentDao rentDao;

    @Test
    public void findBooksByTitle() {

        //Given
        Title title = new Title("Title", "Author", 2000);
        title.getBooks().add(new Book());
        title.getBooks().add(new Book());
        title.getBooks().add(new Book());

        when(titleDao.findAllByTitle("Title")).thenReturn(Collections.singletonList(title));

        //When
        List<Book> books = searchService.findBooksByTitle("Title");

        //Then
        assertEquals(3, books.size());

    }

    @Test
    public void findAvailableBooksByTitle() {

        //Given
        Title title = new Title("Title", "Author", 2000);
        Book book = new Book();
        book.setStatus(Book.Status.LOST);
        Book book1 = new Book();

        title.getBooks().addAll(Arrays.asList(book, book1));

        when(titleDao.findAllByTitle("Title")).thenReturn(Collections.singletonList(title));

        //When
        List<Book> books = searchService.findAvailableBooksByTitle("Title");

        //Then
        assertEquals(1, books.size());
    }

    @Test
    public void findRentedBooks() {

        //Given
        when(bookDao.findRented()).thenReturn(Arrays.asList(new Book(), new Book()));

        //When
        List<Book> rentedBooks = searchService.findRentedBooks();

        //Then
        assertEquals(2, rentedBooks.size());
    }

    @Test
    public void findAvailableBooks() {

        //Given
        when(bookDao.findAvailable()).thenReturn(Arrays.asList(new Book()));

        //When
        List<Book> availableBooks = searchService.findAvailableBooks();

        //Then
        assertEquals(1, availableBooks.size());
    }

    @Test
    public void findLostBooks() {

        //Given
        when(bookDao.findLost()).thenReturn(Arrays.asList(new Book(), new Book(), new Book()));

        //When
        List<Book> lostBooks = searchService.findLostBooks();

        //then
        assertEquals(3, lostBooks.size());
    }

    @Test
    public void findDestroyedBooks() {

        //Given
        when(bookDao.findDestroyed()).thenReturn(Arrays.asList(new Book(), new Book()));

        //When
        List<Book> destroyedBooks = searchService.findDestroyedBooks();

        //Then
        assertEquals(2,destroyedBooks.size() );
    }

    @Test
    public void findAllBooks() {

        //Given
        when(bookDao.findAll()).thenReturn(Arrays.asList(new Book(), new Book()));

        //When
        List<Book> allBooks = searchService.findAllBooks();

        //Then
        assertEquals(2, allBooks.size());
    }

    @Test
    public void findTitlesPublishedBeforeYear() {

        //Given
        Title title = new Title("Title", "Author", 2001);
        when(titleDao.findPublishedBeforeYear(2000)).thenReturn(Collections.singletonList(title));

        //When
        List<Title> titlesPublishedBeforeYear = searchService.findTitlesPublishedBeforeYear(2000);

        //Then
        assertEquals(1, titlesPublishedBeforeYear.size());
    }

    @Test
    public void findAllTitlesByTitle() {

        //Given
        Title title = new Title("Title", "Author", 2000);
        when(titleDao.findAllByTitle("Title")).thenReturn(Collections.singletonList(title));

        //When
        List<Title> allTitlesByTitle = searchService.findAllTitlesByTitle("Title");

        //Then
        assertEquals(1, allTitlesByTitle.size());
    }

    @Test
    public void findTitlesPublishedAfterYear() {

        //Given
        Title title = new Title("TestTitle", "Author", 2000);
        Title title1 = new Title("Test", "Author", 2001);
        when(titleDao.findPublishedAfterYear(1999)).thenReturn(Arrays.asList(title, title1));

        //When
        List<Title> titlesPublishedAfterYear = searchService.findTitlesPublishedAfterYear(1999);

        //Then
        assertEquals(2, titlesPublishedAfterYear.size());
    }

    @Test
    public void findBooksPublishedBeforeYear() {

        //Given
        Title title = new Title("Test", "Author", 2000);
        title.getBooks().addAll(Arrays.asList(new Book(), new Book()));

        when(titleDao.findPublishedBeforeYear(2001)).thenReturn(Collections.singletonList(title));

        //when
        List<Book> booksPublishedBeforeYear = searchService.findBooksPublishedBeforeYear(2001);

        //Then
        assertEquals(2, booksPublishedBeforeYear.size());
    }

    @Test
    public void findBooksPublishedAfterYear() {

        //Given
        Title title = new Title("Test", "Author", 2000);
        title.getBooks().addAll(Arrays.asList(new Book(), new Book()));

        when(titleDao.findPublishedAfterYear(1999)).thenReturn(Collections.singletonList(title));

        //When
        List<Book> booksPublishedAfterYear = searchService.findBooksPublishedAfterYear(1999);

        //Then
        assertEquals(2, booksPublishedAfterYear.size());
    }

    @Test
    public void findTitlesByAuthor() {

        //Given
        Title title = new Title("Test", "Author", 2000);
        when(titleDao.findAllByAuthor("Author")).thenReturn(Collections.singletonList(title));

        //When
        List<Title> titlesByAuthor = searchService.findTitlesByAuthor("Author");

        //Then
        assertEquals(1, titlesByAuthor.size());
    }

    @Test
    public void findBooksByAuthor() {

        //Given
        Title title = new Title("Test", "Author", 1999);
        title.getBooks().addAll(Arrays.asList(new Book(), new Book(), new Book()));

        when(titleDao.findAllByAuthor("Author")).thenReturn(Collections.singletonList(title));

        //When
        List<Book> booksByAuthor = searchService.findBooksByAuthor("Author");

        //Then
        assertEquals(3, booksByAuthor.size());
    }

    @Test
    public void findAllTitles() {

        //Given
        Title title = new Title("Test", "Author", 1999);
        when(titleDao.findAll()).thenReturn(Collections.singletonList(title));

        //When
        List<Title> allTitles = searchService.findAllTitles();

        //Then
        assertEquals(1, allTitles.size());
    }

    @Test
    public void findAllUsers() {

        //Given
        when(userDao.findAll()).thenReturn(Arrays.asList(new User("xx", "yy"), new User("test", "test")));

        //When
        List<User> allUsers = searchService.findAllUsers();

        //Then
        assertEquals(2, allUsers.size());
    }

    @Test
    public void findAllRents() {

        //Given
        when(rentDao.findAll()).thenReturn(Arrays.asList(new Rent(LocalDate.now().plusMonths(2)), new Rent(LocalDate.now().plusMonths(2))));

        //When
        List<Rent> allRents = searchService.findAllRents();

        //Then
        assertEquals(2, allRents.size());
    }

    @Test
    public void findRentsWithExpiredReturnDate() {

        //Given
        when(rentDao.findRentsWithExpiredReturnDate()).thenReturn(Collections.singletonList(new Rent(LocalDate.now().minusMonths(1))));

        //When
        List<Rent> rentsWithExpiredReturnDate = searchService.findRentsWithExpiredReturnDate();

        //Then
        assertEquals(1, rentsWithExpiredReturnDate.size());
    }
}