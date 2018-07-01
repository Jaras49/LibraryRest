package com.library.repository.search.service;

import com.library.domain.book.Book;
import com.library.domain.title.Title;
import com.library.repository.title.TitleDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SearchService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SearchService.class);

    @Autowired
    private TitleDao titleDao;

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
}
