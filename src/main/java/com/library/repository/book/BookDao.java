package com.library.repository.book;

import com.library.domain.book.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface BookDao extends CrudRepository<Book, Long> {

    @Override
    Book save(Book book);

    @Override
    List<Book> findAll();
}
