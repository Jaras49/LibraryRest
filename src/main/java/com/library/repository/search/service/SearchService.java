package com.library.repository.search.service;

import com.library.domain.book.Book;
import com.library.domain.rent.Rent;
import com.library.domain.title.Title;
import com.library.domain.user.User;
import com.library.repository.book.BookDao;
import com.library.repository.rent.RentDao;
import com.library.repository.title.TitleDao;
import com.library.repository.user.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private TitleDao titleDao;
    @Autowired
    private RentDao rentDao;
    @Autowired
    private BookDao bookDao;
    @Autowired
    private UserDao userDao;

    public List<Book> findBooksByTitle(String title) {

        LOGGER.info("Searching for books with title: {}", title);
        List<Book> books = new ArrayList<>();
        List<Title> titles = titleDao.findAllByTitle(title);
        for (Title t : titles) {
            books.addAll(t.getBooks());
        }
        LOGGER.info("Found {} books with title: {}", books.size(), title);
        return books;
    }

    public List<Book> findAvailableBooksByTitle(String title) {

        LOGGER.info("Looking for available books with title: {}", title);
        List<Book> books = findBooksByTitle(title).stream()
                .filter(book -> book.getStatus() == Book.Status.AVAILABLE)
                .collect(Collectors.toList());
        LOGGER.info("Found {} available books with title {}", books.size(), title);

        return books;
    }

    public List<Book> findRentedBooks() {

        LOGGER.info("Looking for rented books");
        List<Book> rentedBooks = bookDao.findRented();
        LOGGER.info("Found {} rented books", rentedBooks.size());

        return rentedBooks;
    }

    public List<Book> findAvailableBooks() {

        LOGGER.info("Looking for available books");
        List<Book> availableBooks = bookDao.findAvailable();
        LOGGER.info("Found {} available books", availableBooks.size());

        return availableBooks;
    }

    public List<Book> findLostBooks() {

        LOGGER.info("Looking for lost books");
        List<Book> lostBooks = bookDao.findLost();
        LOGGER.info("Found {} lost books", lostBooks.size());

        return lostBooks;
    }

    public List<Book> findDestroyedBooks() {

        LOGGER.info("Looking for destroyed books");
        List<Book> destroyedBooks = bookDao.findDestroyed();
        LOGGER.info("Found {} destroyed books");

        return destroyedBooks;
    }

    public List<Book> findAllBooks() {

        LOGGER.info("Fetching all library books");
        List<Book> allBooks = bookDao.findAll();
        LOGGER.info("Found {} books in library", allBooks.size());

        return allBooks;
    }

    public List<Title> findTitlesPublishedBeforeYear(int year) {

        LOGGER.info("Looking for titles published before {}", year);
        List<Title> publishedBeforeYear = titleDao.findPublishedBeforeYear(year);
        LOGGER.info("Found {} titles published before year: {}", publishedBeforeYear.size(), year);

        return publishedBeforeYear;
    }

    public List<Title> findAllTitlesByTitle(String title) {

        LOGGER.info("Looking for titles with title {}", title);
        List<Title> allTitles = titleDao.findAllByTitle(title);
        LOGGER.info("Found {} titles with title: {}", allTitles.size(), title);

        return allTitles;
    }

    public List<Title> findTitlesPublishedAfterYear(int year) {

        LOGGER.info("Looking for titles published after year {}", year);
        List<Title> publishedAfterYear = titleDao.findPublishedAfterYear(year);
        LOGGER.info("Found {} titles published after year {}", publishedAfterYear.size(), year);

        return publishedAfterYear;
    }

    public List<Book> findBooksPublishedBeforeYear(int year) {

        LOGGER.info("Looking for books published before year {}", year);
        List<Book> books = new ArrayList<>();
        List<Title> publishedBeforeYear = titleDao.findPublishedBeforeYear(year);
        for (Title title : publishedBeforeYear) {
            books.addAll(title.getBooks());
        }
        LOGGER.info("Found {} books published before year {}", books.size(), year);

        return books;
    }

    public List<Book> findBooksPublishedAfterYear(int year) {

        LOGGER.info("Looking for books published after year {}", year);
        List<Book> books = new ArrayList<>();
        List<Title> publishedAfterYear = titleDao.findPublishedAfterYear(year);
        for (Title title : publishedAfterYear) {
            books.addAll(title.getBooks());
        }
        LOGGER.info("Found {} books published after year {}", books.size(), year);

        return books;
    }

    public List<Title> findTitlesByAuthor(String author) {

        LOGGER.info("Looking for titles with author {}", author);
        List<Title> allByAuthor = titleDao.findAllByAuthor(author);
        LOGGER.info("Found {} titles with author {}", allByAuthor.size(), author);

        return allByAuthor;
    }

    public List<Book> findBooksByAuthor(String author) {

        LOGGER.info("Looking for books with author {}", author);
        List<Book> books = new ArrayList<>();
        List<Title> allByAuthor = titleDao.findAllByAuthor(author);
        for (Title title : allByAuthor) {
            books.addAll(title.getBooks());
        }
        LOGGER.info("Found {} books with author {}", books.size(), author);

        return books;
    }

    public List<Title> findAllTitles() {

        LOGGER.info("Fetching all library titles");
        List<Title> allTitles = titleDao.findAll();
        LOGGER.info("Found {} titles in library", allTitles.size());

        return allTitles;
    }

    public List<User> findAllUsers() {

        LOGGER.info("Fetching library users");
        List<User> allUsers = userDao.findAll();
        LOGGER.info("Found {} library users", allUsers.size());

        return allUsers;
    }

    public List<Rent> findAllRents() {

        LOGGER.info("Fetching library rents");
        List<Rent> allRents = rentDao.findAll();
        LOGGER.info("Found {} library rents", allRents.size());

        return allRents;
    }

    public List<Rent> findRentsWithExpiredReturnDate() {

        LOGGER.info("Looking for rents with expired return date");
        List<Rent> rentsWithExpiredReturnDate = rentDao.findRentsWithExpiredReturnDate();
        LOGGER.info("Found {} rents with expired return date", rentsWithExpiredReturnDate.size());

        return rentsWithExpiredReturnDate;
    }
}
