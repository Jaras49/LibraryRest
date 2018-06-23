package com.library.repository.rent;

import com.library.domain.book.Book;
import com.library.domain.rent.Rent;
import com.library.repository.book.BookDao;
import com.library.repository.user.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Optional;

@Component
public class RentRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(RentRepository.class);

    @Autowired
    private RentDao rentDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private BookDao bookDao;

    public Rent rentBooks(Long userId, Long... bookId) {

        Rent rent = new Rent(getReturnDate());
        if (userDao.existsById(userId)) {
            rent.setUser(userDao.findById(userId).get());

            for (Long id : bookId) {
                Optional<Book> book = bookDao.findById(id);
                book.get().setRent(rent);
                rent.getRentedBooks().add(book.get());
            }
        }
        return rentDao.save(rent);
    }

    private LocalDate getReturnDate() {
        return LocalDate.now().plusMonths(2);
    }
}
