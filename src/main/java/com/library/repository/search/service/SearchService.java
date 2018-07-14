package com.library.repository.search.service;

import com.library.domain.book.Book;
import com.library.domain.title.Title;
import com.library.repository.book.BookDao;
import com.library.repository.rent.RentDao;
import com.library.repository.title.TitleDao;
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
}
