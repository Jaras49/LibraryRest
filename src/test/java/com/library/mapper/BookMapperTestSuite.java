package com.library.mapper;

import com.library.domain.book.Book;
import com.library.domain.book.BookDto;
import com.library.domain.title.Title;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BookMapperTestSuite {

    @Autowired
    private BookMapper bookMapper;

    @Test
    public void shouldMapToBookDtoList() {

        //Given
        Title title = new Title("NAME", "AUTHOR", 2000);
        Book book = new Book();
        book.setTitle(title);
        Book book1 = new Book();
        book1.setTitle(title);

        //When
        List<BookDto> bookDtos = bookMapper.mapToBookDtoList(Arrays.asList(book, book1));

        //Then
        assertEquals(2, bookDtos.size());

        bookDtos.forEach(bookDto -> {
            assertEquals("NAME", bookDto.getTitle().getTitle());
            assertEquals("AUTHOR", bookDto.getTitle().getAuthor());
            assertEquals(2000, bookDto.getTitle().getYearOfPublication());
        });
    }
}