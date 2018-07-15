package com.library.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.domain.book.Book;
import com.library.domain.book.BookDto;
import com.library.domain.rent.RentDto;
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
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LibraryController.class)
@RunWith(SpringRunner.class)
public class LibraryControllerTestSuite {

    private static final String CREATE_USER_URL = "/library/createUser";
    private static final String ADD_BOOK_URL = "/library/addBook";
    private static final String RENT_BOOKS_URL = "/library/rent";
    private static final String RETURN_RENT_URL = "/library/returnRent";
    private static final String RETURN_BOOK_URL = "/library/returnBook";
    private static final String UPDATE_BOOK_STATUS_URL = "/library/updateStatus";

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
    public void shouldCreateUser() throws Exception {

        //Given
        UserDto userDto = new UserDto(1L, "TestUser", "test");
        User user = new User("TestUser", "test");

        when(userMapper.mapToUser(userDto)).thenReturn(user);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(user);

        //When & Then
        mockMvc.perform(post(CREATE_USER_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldAddBook() throws Exception {

        //Given
        TitleDto titleDto = new TitleDto(1L, "TITLE", "AUTHOR", 2000);
        Title title = new Title("TITLE", "AUTHOR", 2000);

        when(titleMapper.mapToTitle(titleDto)).thenReturn(title);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(title);

        //When & Then
        mockMvc.perform(post(ADD_BOOK_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldRentBooks() throws Exception {

        //Given
        Long[] booksIDs = new Long[]{2L, 3L};
        RentDto rentDto = new RentDto(1L, booksIDs);

        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writeValueAsString(rentDto);

        //When & Then
        mockMvc.perform(post(RENT_BOOKS_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnRent() throws Exception {

        //When & Then
        mockMvc.perform(delete(RETURN_RENT_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .param("rentId", "1"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldReturnBook() throws Exception {

        //Given
        Long[] booksIds = new Long[]{2L, 3L};
        RentDto rentDto = new RentDto(1L, booksIds);

        String json = new ObjectMapper().writeValueAsString(rentDto);

        //When && Then
        mockMvc.perform(put(RETURN_BOOK_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateBookStatus() throws Exception {

        //Given
        TitleDto title = new TitleDto(1L, "TITLE", "AUTHOR", 2000);
        BookDto bookDto = new BookDto(2L, title, Book.Status.RENTED);

        String json = new ObjectMapper().writeValueAsString(bookDto);

        //When & then
        mockMvc.perform(put(UPDATE_BOOK_STATUS_URL).contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(json))
                .andExpect(status().isOk());
    }
}