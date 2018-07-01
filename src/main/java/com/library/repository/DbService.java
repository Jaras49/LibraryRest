package com.library.repository;

import com.library.domain.book.Book;
import com.library.domain.rent.Rent;
import com.library.domain.title.Title;
import com.library.domain.user.User;
import com.library.exception.BookRepositoryException;
import com.library.exception.RentException;
import com.library.repository.book.BookRepository;
import com.library.repository.rent.RentRepository;
import com.library.repository.search.service.SearchService;
import com.library.repository.user.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DbService {

    private static final Logger LOGGER = LoggerFactory.getLogger(DbService.class);

    @Autowired
    private UserDao userDao;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RentRepository rentRepository;
    @Autowired
    private SearchService searchService;

    public User createNewUser(User user) {
        LOGGER.info("Library user added");
        return userDao.save(user);
    }

    public Book addBook(Title title) {
        return bookRepository.addBook(title);
    }

    public void updateBookStatus(Long id, Book.Status status) {
        try {
            bookRepository.updateBookStatus(id, status);
        } catch (BookRepositoryException e) {
            LOGGER.error(BookRepositoryException.ERR_INVALID_BOOK_ID, e.getMessage(), e);
        }
    }

    public Rent rentBooks(Long userId, Long... bookId) {

        try {
            return rentRepository.rentBooks(userId, bookId);
        } catch (RentException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return null;
    }

    public void returnRent(Long rentId) {

        try {
            rentRepository.returnRent(rentId);
        } catch (RentException e) {
            LOGGER.error(RentException.INVALID_RENT_ID, e.getMessage(), e);
        }
    }

    public void returnBooks(Long... bookID) {
            rentRepository.returnBooks(bookID);
    }

    public List<Book> findBooksByTitle(String title) {
        return searchService.findBooksByTitle(title);
    }

    public List<Book> findAvailableBooksByTitle(String title) {
        return searchService.findAvailableBooksByTitle(title);
    }
}
