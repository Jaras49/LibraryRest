package com.library.mapper;

import com.library.domain.book.Book;
import com.library.domain.book.BookDto;
import com.library.domain.title.TitleDto;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMapper {

    public List<BookDto> mapToBookDtoList (List<Book> books) {

        return books.stream()
                .map(book -> new BookDto(book.getId(),
                        new TitleDto(
                                book.getTitle().getId(),
                                book.getTitle().getTitleName(),
                                book.getTitle().getAuthor(),
                                book.getTitle().getYearOfPublication()),
                        book.getStatus()))
                .collect(Collectors.toList());
    }
}
