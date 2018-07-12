package com.library.repository.book;

import com.library.domain.book.Book;
import com.library.domain.title.Title;
import com.library.exception.BookRepositoryException;
import com.library.repository.title.TitleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class BookRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(BookRepository.class);

    @Autowired
    private BookDao bookDao;

    @Autowired
    private TitleDao titleDao;

    public Book addBook(Title title) {

        Optional<Title> titleFromDb = titleDao.findByTitleNameAndAuthorAndYearOfPublication(
                title.getTitleName(), title.getAuthor(), title.getYearOfPublication());

        Book book = new Book();
        if (titleFromDb.isPresent()) {
            book.setTitle(titleFromDb.get());
            titleFromDb.get().getBooks().add(book);
            bookDao.save(book);
            LOGGER.info("Book " + book.getTitle().getTitleName() + " added to the library");
        } else {
            title.getBooks().add(book);
            book.setTitle(title);
            titleDao.save(title);
            LOGGER.info("Book " + book.getTitle().getTitleName() + " to the Library for the first time");
        }
        return book;
    }

    public void updateBookStatus(Long id, Book.Status status) throws BookRepositoryException {

        Optional<Book> optionalBook = bookDao.findById(id);
        if (optionalBook.isPresent()) {
            Book book = optionalBook.get();
            book.setStatus(status);
            bookDao.save(book);
            LOGGER.info("Updated book with id: {} status to: {}", id, status);
        } else {
            LOGGER.error("Failed to update book status");
            throw new BookRepositoryException(BookRepositoryException.ERR_INVALID_BOOK_ID);
        }
    }
}
