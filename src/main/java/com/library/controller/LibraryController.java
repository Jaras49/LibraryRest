package com.library.controller;

import com.library.domain.book.BookDto;
import com.library.domain.rent.RentDto;
import com.library.domain.title.TitleDto;
import com.library.domain.user.UserDto;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/library")
public class LibraryController extends AbstractController {

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
}
