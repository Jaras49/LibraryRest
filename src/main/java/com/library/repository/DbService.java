package com.library.repository;

import com.library.domain.book.Book;
import com.library.domain.title.Title;
import com.library.domain.user.User;
import com.library.repository.book.BookDao;
import com.library.repository.title.TitleDao;
import com.library.repository.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private TitleDao titleDao;
    @Autowired
    private BookDao bookDao;

    public User createNewUser(User user) {
        return userDao.save(user);
    }

    public Book addBook(Title title) {

        Optional<Title> titleFromDb = titleDao.findByTitleNameAndAuthorAndYearOfPublication(
                title.getTitleName(), title.getAuthor(), title.getYearOfPublication());

        Book book = new Book();
        if (titleFromDb.isPresent()) {
            book.setTitle(titleFromDb.get());
            titleFromDb.get().getBooks().add(book);
            bookDao.save(book);
        } else {
            title.getBooks().add(book);
            book.setTitle(title);
            bookDao.save(book);
        }
        return book;
    }
}
