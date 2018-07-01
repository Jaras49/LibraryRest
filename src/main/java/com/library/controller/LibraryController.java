package com.library.controller;

import com.library.domain.book.BookDto;
import com.library.domain.rent.RentDto;
import com.library.domain.title.TitleDto;
import com.library.domain.user.UserDto;
import com.library.mapper.BookMapper;
import com.library.mapper.TitleMapper;
import com.library.mapper.UserMapper;
import com.library.repository.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/library")
public class LibraryController {

    @Autowired
    private DbService dbService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private TitleMapper titleMapper;
    @Autowired
    private BookMapper bookMapper;

    @RequestMapping(method = RequestMethod.POST, value = "/createUser", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
        dbService.createNewUser(userMapper.mapToUser(userDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/addBook", consumes = APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody TitleDto titleDto) {
        dbService.addBook(titleMapper.mapToTitle(titleDto));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/rent", consumes = APPLICATION_JSON_VALUE)
    public void rentBooks(@RequestBody RentDto rentDto) {
        dbService.rentBooks(rentDto.getUserId(), rentDto.getBooksId());
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/returnRent")
    public void returnRent(@RequestParam Long rentId) {
        dbService.returnRent(rentId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/returnBook", consumes = APPLICATION_JSON_VALUE)
    public void returnBook(@RequestBody RentDto rentDto) {
        dbService.returnBooks(rentDto.getBooksId());
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/updateStatus", consumes = APPLICATION_JSON_VALUE)
    public void updateBookStatus(@RequestBody BookDto bookDto) {
        dbService.updateBookStatus(bookDto.getId(), bookDto.getStatus());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findByTitle")
    public List<BookDto> findBooksByTitle(@RequestParam String title) {
        return bookMapper.mapToBookDtoList(dbService.findBooksByTitle(title));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAvailableByTitle")
    public List<BookDto> findAvailableBooksByTitle(@RequestParam String title) {
        return bookMapper.mapToBookDtoList(dbService.findAvailableBooksByTitle(title));
    }
}
