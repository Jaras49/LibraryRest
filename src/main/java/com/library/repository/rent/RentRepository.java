package com.library.repository.rent;

import com.library.domain.book.Book;
import com.library.domain.rent.Rent;
import com.library.domain.user.User;
import com.library.exception.RentException;
import com.library.repository.book.BookDao;
import com.library.repository.user.UserDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
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

    public Rent rentBooks(Long userId, Long... bookId) throws RentException {

        Rent rent = new Rent(getReturnDate());
        boolean doRent = false;

        Optional<User> optionalUser = userDao.findById(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            rent.setUser(user);

            for (Long id : bookId) {
                Optional<Book> optionalBook = bookDao.findById(id);
                if (optionalBook.isPresent()) {
                    Book book = optionalBook.get();
                    book.setRent(rent);
                    book.setStatus(Book.Status.rented);

                    rent.getRentedBooks().add(book);

                    doRent = true;

                    LOGGER.info("Book {} rented", id);
                } else {
                    LOGGER.warn("No book with id {}", id);
                }
            }
        } else {
            LOGGER.error("Failed to rent books");
            throw new RentException(RentException.INVALID_USER_ID);
        }
        if (doRent) {
            LOGGER.info("Books rented successfully");
            return rentDao.save(rent);
        }
        LOGGER.error("Failed to rent books");
        throw new RentException(RentException.NOTHING_RENTED);
    }

    public void returnRent(Long rentId) throws RentException {

        Optional<Rent> rent = rentDao.findById(rentId);
        if (rent.isPresent()) {
            rent.get().getRentedBooks().forEach(book -> {
                book.setRent(null);
                book.setStatus(Book.Status.available);
                LOGGER.info("Returned book {}", book.getId());
            });
            rentDao.deleteById(rentId);
            LOGGER.info("Returned rent {}", rentId);
        } else {
            LOGGER.error("Failed to return rent");
            throw new RentException(RentException.INVALID_RENT_ID);
        }
    }

    public void returnBooks(Long... bookId) {

        for (Long id : bookId) {
            Optional<Book> optional = bookDao.findById(id);
            if (optional.isPresent()) {
                Book book = optional.get();
                book.setStatus(Book.Status.available);

                Rent rent = book.getRent();
                List<Book> rentedBooks = rent.getRentedBooks();
                rentedBooks.remove(book);

                book.setRent(null);

                rentDao.save(rent);
                deleteEmptyRent(rent);

                LOGGER.info("Returned book {}", id);
            } else {
                LOGGER.warn("No book with id {}", id);
            }
        }
    }

    private LocalDate getReturnDate() {
        return LocalDate.now().plusMonths(2);
    }

    private void deleteEmptyRent(Rent rent) {
        if (rent.getRentedBooks().isEmpty()) {
            rentDao.delete(rent);
        }
    }
}
