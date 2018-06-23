package com.library.repository;

import com.library.domain.book.Book;
import com.library.domain.rent.Rent;
import com.library.domain.title.Title;
import com.library.domain.user.User;
import com.library.repository.book.BookRepository;
import com.library.repository.rent.RentRepository;
import com.library.repository.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private RentRepository rentRepository;

    public User createNewUser(User user) {
        return userDao.save(user);
    }

    public Book addBook(Title title) {
        return bookRepository.addBook(title);
    }

    public Rent rentBooks(Long userId, Long... bookId) {
        return rentRepository.rentBooks(userId, bookId);
    }
}
