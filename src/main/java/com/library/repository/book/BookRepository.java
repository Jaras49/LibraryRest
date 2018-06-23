package com.library.repository.book;

import com.library.domain.book.Book;
import com.library.domain.title.Title;
import com.library.repository.title.TitleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class BookRepository {

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
        } else {
            title.getBooks().add(book);
            book.setTitle(title);
            bookDao.save(book);
        }
        return book;
    }
}
