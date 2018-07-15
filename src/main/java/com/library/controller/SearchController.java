package com.library.controller;

import com.library.domain.book.BookDto;
import com.library.domain.rent.OutgoingRentDto;
import com.library.domain.title.TitleDto;
import com.library.domain.user.UserDto;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/search")
public class SearchController extends AbstractController {

    @RequestMapping(method = RequestMethod.GET, value = "/findByTitle")
    public List<BookDto> findBooksByTitle(@RequestParam String title) {
        return bookMapper.mapToBookDtoList(dbService.findBooksByTitle(title));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAvailableByTitle")
    public List<BookDto> findAvailableBooksByTitle(@RequestParam String title) {
        return bookMapper.mapToBookDtoList(dbService.findAvailableBooksByTitle(title));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findRented")
    public List<BookDto> findRentedBooks() {
        return bookMapper.mapToBookDtoList(dbService.findRentedBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAvailableBooks")
    public List<BookDto> findAvailableBooks() {
        return bookMapper.mapToBookDtoList(dbService.findAvailableBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findLostBooks")
    public List<BookDto> findLostBooks() {
        return bookMapper.mapToBookDtoList(dbService.findLostBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findDestroyedBooks")
    public List<BookDto> findDestroyedBooks() {
        return bookMapper.mapToBookDtoList(dbService.findDestroyedBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAllBooks")
    public List<BookDto> findAllBooks() {
        return bookMapper.mapToBookDtoList(dbService.findAllBooks());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findTitlesPublishedBefore")
    public List<TitleDto> findTitlesPublishedBeforeYear(@RequestParam int year) {
        return titleMapper.mapToTitleDtoList(dbService.findTitlesPublishedBeforeYear(year));
    }

    @RequestMapping(method = RequestMethod.GET, value = "findAllTitlesByTitle")
    public List<TitleDto> findAllTitlesByTitle(@RequestParam String title) {
        return titleMapper.mapToTitleDtoList(dbService.findAllTitlesByTitle(title));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findTitlesPublishedAfter")
    public List<TitleDto> findTitlesPublishedAfterYear(@RequestParam int year) {
        return titleMapper.mapToTitleDtoList(dbService.findTitlesPublishedAfterYear(year));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findBooksPublishedBefore")
    public List<BookDto> findBooksPublishedBeforeYear(@RequestParam int year) {
        return bookMapper.mapToBookDtoList(dbService.findBooksPublishedBeforeYear(year));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findBooksPublishedAfter")
    public List<BookDto> findBooksPublishedAfterYear(@RequestParam int year) {
        return bookMapper.mapToBookDtoList(dbService.findBooksPublishedAfterYear(year));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findTitlesByAuthor")
    public List<TitleDto> findTitlesByAuthor(@RequestParam String author) {
        return titleMapper.mapToTitleDtoList(dbService.findTitlesByAuthor(author));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findBooksByAuthor")
    public List<BookDto> findBooksByAuthor(@RequestParam String author) {
        return bookMapper.mapToBookDtoList(dbService.findBooksByAuthor(author));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAllTitles")
    public List<TitleDto> findAllTitles() {
        return titleMapper.mapToTitleDtoList(dbService.findAllTitles());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAllUsers")
    public List<UserDto> findAllUsers() {
        return userMapper.mapToUserDtoList(dbService.findAllUsers());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findAllRents")
    public List<OutgoingRentDto> findAllRents() {
        return rentMapper.mapToOutgoingRentDtoList(dbService.findAllRents());
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findRentsWithExpiredReturn")
    public List<OutgoingRentDto> findRentsWithExpiredReturnDate() {
        return rentMapper.mapToOutgoingRentDtoList(dbService.findRentsWithExpiredReturnDate());
    }
}
