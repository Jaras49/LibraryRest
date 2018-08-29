package com.library.controller;

import com.library.domain.book.Book;
import com.library.domain.book.BookDto;
import com.library.domain.rent.OutgoingRentDto;
import com.library.domain.rent.Rent;
import com.library.domain.title.Title;
import com.library.domain.title.TitleDto;
import com.library.domain.user.User;
import com.library.domain.user.UserDto;
import com.library.mapper.BookMapper;
import com.library.mapper.RentMapper;
import com.library.mapper.TitleMapper;
import com.library.mapper.UserMapper;
import com.library.repository.DbService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SearchController.class)
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc(secure = false)
public class SearchControllerTestSuite {

    private static final String FIND_BY_TITLE_URL = "/search/findByTitle";
    private static final String FIND_AVAILABLE_BOOKS_BY_TITLE_URL = "/search/findAvailableByTitle";
    private static final String FIND_RENTED_BOOKS_URL = "/search/findRented";
    private static final String FIND_AVAILABLE_BOOKS_URL = "/search/findAvailableBooks";
    private static final String FIND_LOST_BOOKS_URL = "/search/findLostBooks";
    private static final String FIND_DESTROYED_BOOKS_URL = "/search/findDestroyedBooks";
    private static final String FIND_ALL_BOOKS_URL = "/search/findAllBooks";
    private static final String FIND_TITLES_PUBLISHED_BEFORE_URL = "/search/findTitlesPublishedBefore";
    private static final String FIND_ALL_TITLES_BY_TITLE_URL = "/search/findAllTitlesByTitle";
    private static final String FIND_TITLES_PUBLISHED_AFTER_URL = "/search/findTitlesPublishedAfter";
    private static final String FIND_BOOKS_PUBLISHED_BEFORE_URL = "/search/findBooksPublishedBefore";
    private static final String FIND_BOOKS_PUBLISHED_AFTER_URL = "/search/findBooksPublishedAfter";
    private static final String FIND_TITLES_BY_AUTHOR_URL = "/search/findTitlesByAuthor";
    private static final String FIND_BOOKS_BY_AUTHOR_URL = "/search/findBooksByAuthor";
    private static final String FIND_ALL_TITLES_URL = "/search/findAllTitles";
    private static final String FIND_ALL_USERS_URL = "/search/findAllUsers";
    private static final String FIND_ALL_RENTS_URL = "/search/findAllRents";
    private static final String FIND_RENTS_WITH_EXPIRED_RETURN_URL = "/search/findRentsWithExpiredReturn";

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private RentMapper rentMapper;
    @MockBean
    private DbService dbService;
    @MockBean
    private UserMapper userMapper;
    @MockBean
    private TitleMapper titleMapper;
    @MockBean
    private BookMapper bookMapper;

    @Test
    public void shouldFindBooksByTitle() throws Exception {

        //Given
        Title title = new Title("title", "author", 1992);
        Book book = new Book();
        book.setTitle(title);
        Book book1 = new Book();
        book1.setTitle(title);

        TitleDto titleDto = new TitleDto(1L, "title", "author", 1992);
        BookDto bookDto = new BookDto(2L, titleDto, Book.Status.AVAILABLE);
        BookDto bookDto1 = new BookDto(3L, titleDto, Book.Status.AVAILABLE);


        when(dbService.findBooksByTitle("title")).thenReturn(Arrays.asList(book, book1));
        when(bookMapper.mapToBookDtoList(Arrays.asList(book, book1))).thenReturn(Arrays.asList(bookDto, bookDto1));

        //When & Then
        mockMvc.perform(get(FIND_BY_TITLE_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("title", "title"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title.title", is("title")))
                .andExpect(jsonPath("$[0].title.author", is("author")));
    }

    @Test
    public void shouldFindAvailableBooksByTitle() throws Exception {

        //Given
        Title title = new Title("title", "author", 1992);
        Book book = new Book();
        book.setTitle(title);
        Book book1 = new Book();
        book1.setTitle(title);

        TitleDto titleDto = new TitleDto(1L, "title", "author", 1992);
        BookDto bookDto = new BookDto(2L, titleDto, Book.Status.AVAILABLE);
        BookDto bookDto1 = new BookDto(3L, titleDto, Book.Status.AVAILABLE);


        when(dbService.findAvailableBooksByTitle("title")).thenReturn(Arrays.asList(book, book1));
        when(bookMapper.mapToBookDtoList(Arrays.asList(book, book1))).thenReturn(Arrays.asList(bookDto, bookDto1));

        //When & Then
        mockMvc.perform(get(FIND_AVAILABLE_BOOKS_BY_TITLE_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("title", "title"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].title.title", is("title")))
                .andExpect(jsonPath("$[1].title.author", is("author")));
    }

    @Test
    public void shouldFindRentedBooks() throws Exception {

        //Given
        Title title = new Title("title", "author", 1992);
        Book book = new Book();
        book.setTitle(title);
        Book book1 = new Book();
        book1.setTitle(title);

        TitleDto titleDto = new TitleDto(1L, "title", "author", 1992);
        BookDto bookDto = new BookDto(2L, titleDto, Book.Status.RENTED);
        BookDto bookDto1 = new BookDto(3L, titleDto, Book.Status.RENTED);


        when(dbService.findAvailableBooks()).thenReturn(Arrays.asList(book, book1));
        when(bookMapper.mapToBookDtoList(Arrays.asList(book, book1))).thenReturn(Arrays.asList(bookDto, bookDto1));

        //When & Then
        mockMvc.perform(get(FIND_AVAILABLE_BOOKS_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title.title", is("title")))
                .andExpect(jsonPath("$[0].title.author", is("author")));
    }

    @Test
    public void shouldFindAvailableBooks() throws Exception {

        //Given
        Title title = new Title("title", "author", 1992);
        Book book = new Book();
        book.setTitle(title);
        Book book1 = new Book();
        book1.setTitle(title);

        TitleDto titleDto = new TitleDto(1L, "title", "author", 1992);
        BookDto bookDto = new BookDto(2L, titleDto, Book.Status.AVAILABLE);
        BookDto bookDto1 = new BookDto(3L, titleDto, Book.Status.AVAILABLE);


        when(dbService.findRentedBooks()).thenReturn(Arrays.asList(book, book1));
        when(bookMapper.mapToBookDtoList(Arrays.asList(book, book1))).thenReturn(Arrays.asList(bookDto, bookDto1));

        //When & Then
        mockMvc.perform(get(FIND_RENTED_BOOKS_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].title.title", is("title")))
                .andExpect(jsonPath("$[1].title.author", is("author")));
    }

    @Test
    public void shouldFindLostBooks() throws Exception {

        //Given
        Title title = new Title("title", "author", 1992);
        Book book = new Book();
        book.setTitle(title);
        Book book1 = new Book();
        book1.setTitle(title);

        TitleDto titleDto = new TitleDto(1L, "title", "author", 1992);
        BookDto bookDto = new BookDto(2L, titleDto, Book.Status.LOST);
        BookDto bookDto1 = new BookDto(3L, titleDto, Book.Status.LOST);


        when(dbService.findLostBooks()).thenReturn(Arrays.asList(book, book1));
        when(bookMapper.mapToBookDtoList(Arrays.asList(book, book1))).thenReturn(Arrays.asList(bookDto, bookDto1));

        //When & Then
        mockMvc.perform(get(FIND_LOST_BOOKS_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].title.title", is("title")))
                .andExpect(jsonPath("$[1].title.author", is("author")));

    }

    @Test
    public void shouldFindDestroyedBooks() throws Exception {

        //Given
        Title title = new Title("title", "author", 1992);
        Book book = new Book();
        book.setTitle(title);
        Book book1 = new Book();
        book1.setTitle(title);

        TitleDto titleDto = new TitleDto(1L, "title", "author", 1992);
        BookDto bookDto = new BookDto(2L, titleDto, Book.Status.DESTROYED);
        BookDto bookDto1 = new BookDto(3L, titleDto, Book.Status.DESTROYED);


        when(dbService.findDestroyedBooks()).thenReturn(Arrays.asList(book, book1));
        when(bookMapper.mapToBookDtoList(Arrays.asList(book, book1))).thenReturn(Arrays.asList(bookDto, bookDto1));

        //When & Then
        mockMvc.perform(get(FIND_DESTROYED_BOOKS_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].title.title", is("title")))
                .andExpect(jsonPath("$[1].title.author", is("author")));
    }

    @Test
    public void findAllBooks() throws Exception {

        //Given
        Title title = new Title("title", "author", 1992);
        Book book = new Book();
        book.setTitle(title);
        Book book1 = new Book();
        book1.setTitle(title);

        TitleDto titleDto = new TitleDto(1L, "title", "author", 1992);
        BookDto bookDto = new BookDto(2L, titleDto, Book.Status.LOST);
        BookDto bookDto1 = new BookDto(3L, titleDto, Book.Status.DESTROYED);


        when(dbService.findAllBooks()).thenReturn(Arrays.asList(book, book1));
        when(bookMapper.mapToBookDtoList(Arrays.asList(book, book1))).thenReturn(Arrays.asList(bookDto, bookDto1));

        //When & Then
        mockMvc.perform(get(FIND_ALL_BOOKS_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].title.title", is("title")))
                .andExpect(jsonPath("$[1].title.author", is("author")));
    }

    @Test
    public void findTitlesPublishedBeforeYear() throws Exception {

        //Given
        Title title = new Title("test", "author", 1999);
        TitleDto titleDto = new TitleDto(1L, "test", "author", 1999);

        when(dbService.findTitlesPublishedBeforeYear(2000)).thenReturn(Collections.singletonList(title));
        when(titleMapper.mapToTitleDtoList(Collections.singletonList(title))).thenReturn(Collections.singletonList(titleDto));

        //When & Then
        mockMvc.perform(get(FIND_TITLES_PUBLISHED_BEFORE_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("year", "2000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("test")))
                .andExpect(jsonPath("$[0].author", is("author")))
                .andExpect(jsonPath("$[0].yearOfPublication", is(1999)));
    }

    @Test
    public void findAllTitlesByTitle() throws Exception {

        //Given
        Title title = new Title("test", "author", 1999);
        TitleDto titleDto = new TitleDto(1L, "test", "author", 1999);

        when(dbService.findAllTitlesByTitle("title")).thenReturn(Collections.singletonList(title));
        when(titleMapper.mapToTitleDtoList(Collections.singletonList(title))).thenReturn(Collections.singletonList(titleDto));

        //When & Then
        mockMvc.perform(get(FIND_ALL_TITLES_BY_TITLE_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("title", "title"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("test")))
                .andExpect(jsonPath("$[0].author", is("author")))
                .andExpect(jsonPath("$[0].yearOfPublication", is(1999)));
    }


    @Test
    public void findTitlesPublishedAfterYear() throws Exception {

        //Given
        Title title = new Title("test", "author", 1999);
        TitleDto titleDto = new TitleDto(1L, "test", "author", 1999);

        when(dbService.findTitlesPublishedAfterYear(1980)).thenReturn(Collections.singletonList(title));
        when(titleMapper.mapToTitleDtoList(Collections.singletonList(title))).thenReturn(Collections.singletonList(titleDto));

        //When & Then
        mockMvc.perform(get(FIND_TITLES_PUBLISHED_AFTER_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("year", "1980"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("test")))
                .andExpect(jsonPath("$[0].author", is("author")))
                .andExpect(jsonPath("$[0].yearOfPublication", is(1999)));
    }

    @Test
    public void findBooksPublishedBeforeYear() throws Exception {

        //Given
        Title title = new Title("title", "author", 1992);
        Book book = new Book();
        book.setTitle(title);
        Book book1 = new Book();
        book1.setTitle(title);

        TitleDto titleDto = new TitleDto(1L, "title", "author", 1992);
        BookDto bookDto = new BookDto(2L, titleDto, Book.Status.DESTROYED);
        BookDto bookDto1 = new BookDto(3L, titleDto, Book.Status.DESTROYED);


        when(dbService.findBooksPublishedBeforeYear(2000)).thenReturn(Arrays.asList(book, book1));
        when(bookMapper.mapToBookDtoList(Arrays.asList(book, book1))).thenReturn(Arrays.asList(bookDto, bookDto1));

        //When & Then
        mockMvc.perform(get(FIND_BOOKS_PUBLISHED_BEFORE_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("year", "2000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].title.title", is("title")))
                .andExpect(jsonPath("$[1].title.author", is("author")));
    }

    @Test
    public void findBooksPublishedAfterYear() throws Exception {

        //Given
        Title title = new Title("title", "author", 1992);
        Book book = new Book();
        book.setTitle(title);
        Book book1 = new Book();
        book1.setTitle(title);

        TitleDto titleDto = new TitleDto(1L, "title", "author", 1992);
        BookDto bookDto = new BookDto(2L, titleDto, Book.Status.DESTROYED);
        BookDto bookDto1 = new BookDto(3L, titleDto, Book.Status.DESTROYED);


        when(dbService.findBooksPublishedAfterYear(1990)).thenReturn(Arrays.asList(book, book1));
        when(bookMapper.mapToBookDtoList(Arrays.asList(book, book1))).thenReturn(Arrays.asList(bookDto, bookDto1));

        //When & Then
        mockMvc.perform(get(FIND_BOOKS_PUBLISHED_AFTER_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("year", "1990"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].title.title", is("title")))
                .andExpect(jsonPath("$[1].title.author", is("author")));
    }

    @Test
    public void findTitlesByAuthor() throws Exception {

        //Given
        Title title = new Title("test", "author", 1999);
        TitleDto titleDto = new TitleDto(1L, "test", "author", 1999);

        when(dbService.findTitlesByAuthor("author")).thenReturn(Collections.singletonList(title));
        when(titleMapper.mapToTitleDtoList(Collections.singletonList(title))).thenReturn(Collections.singletonList(titleDto));

        //When & Then
        mockMvc.perform(get(FIND_TITLES_BY_AUTHOR_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("author", "author"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("test")))
                .andExpect(jsonPath("$[0].author", is("author")))
                .andExpect(jsonPath("$[0].yearOfPublication", is(1999)));
    }

    @Test
    public void findBooksByAuthor() throws Exception {

        //Given
        Title title = new Title("title", "author", 1992);
        Book book = new Book();
        book.setTitle(title);
        Book book1 = new Book();
        book1.setTitle(title);

        TitleDto titleDto = new TitleDto(1L, "title", "author", 1992);
        BookDto bookDto = new BookDto(2L, titleDto, Book.Status.DESTROYED);
        BookDto bookDto1 = new BookDto(3L, titleDto, Book.Status.DESTROYED);


        when(dbService.findBooksByAuthor("author")).thenReturn(Arrays.asList(book, book1));
        when(bookMapper.mapToBookDtoList(Arrays.asList(book, book1))).thenReturn(Arrays.asList(bookDto, bookDto1));

        //When & Then
        mockMvc.perform(get(FIND_BOOKS_BY_AUTHOR_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("author", "author"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[1].title.title", is("title")))
                .andExpect(jsonPath("$[1].title.author", is("author")));
    }

    @Test
    public void findAllTitles() throws Exception {

        //Given
        Title title = new Title("test", "author", 1999);
        TitleDto titleDto = new TitleDto(1L, "test", "author", 1999);

        when(dbService.findAllTitles()).thenReturn(Collections.singletonList(title));
        when(titleMapper.mapToTitleDtoList(Collections.singletonList(title))).thenReturn(Collections.singletonList(titleDto));

        //When & Then
        mockMvc.perform(get(FIND_ALL_TITLES_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].title", is("test")))
                .andExpect(jsonPath("$[0].author", is("author")))
                .andExpect(jsonPath("$[0].yearOfPublication", is(1999)));
    }

    @Test
    public void findAllUsers() throws Exception {

        //Given
        List<User> users = Arrays.asList(new User("test", "user"), new User("test", "user"));
        List<UserDto> userDtos = Arrays.asList(new UserDto(1L, "test", "user"),
                new UserDto(1L, "test", "user"));

        when(dbService.findAllUsers()).thenReturn(users);
        when(userMapper.mapToUserDtoList(users)).thenReturn(userDtos);

        //When & Then
        mockMvc.perform(get(FIND_ALL_USERS_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].firstName", is("test")));
    }

    @Test
    public void findAllRents() throws Exception {

        //Given
        Rent rent = new Rent(LocalDate.now().plusMonths(2));
        Rent rent1 = new Rent(LocalDate.now().plusMonths(3));
        OutgoingRentDto outgoingRentDto = new OutgoingRentDto(1L, new Date(), LocalDate.now().plusMonths(2), Collections.emptyList(), null);
        OutgoingRentDto outgoingRentDto1 = new OutgoingRentDto(2L, new Date(), LocalDate.now().plusMonths(3), Collections.emptyList(), null);

        when(dbService.findAllRents()).thenReturn(Arrays.asList(rent, rent1));
        when(rentMapper.mapToOutgoingRentDtoList(Arrays.asList(rent, rent1))).thenReturn(Arrays.asList(outgoingRentDto, outgoingRentDto1));

        //When & Then
        mockMvc.perform(get(FIND_ALL_RENTS_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }

    @Test
    public void findRentsWithExpiredReturnDate() throws Exception {

        //Given
        Rent rent = new Rent(LocalDate.now().plusMonths(2));
        Rent rent1 = new Rent(LocalDate.now().plusMonths(3));
        OutgoingRentDto outgoingRentDto = new OutgoingRentDto(1L, new Date(), LocalDate.now().minusMonths(2), Collections.emptyList(), null);
        OutgoingRentDto outgoingRentDto1 = new OutgoingRentDto(2L, new Date(), LocalDate.now().minusMonths(3), Collections.emptyList(), null);

        when(dbService.findRentsWithExpiredReturnDate()).thenReturn(Arrays.asList(rent, rent1));
        when(rentMapper.mapToOutgoingRentDtoList(Arrays.asList(rent, rent1))).thenReturn(Arrays.asList(outgoingRentDto, outgoingRentDto1));

        //When & Then
        mockMvc.perform(get(FIND_RENTS_WITH_EXPIRED_RETURN_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)));
    }
}