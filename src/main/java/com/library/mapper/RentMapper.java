package com.library.mapper;

import com.library.domain.book.Book;
import com.library.domain.book.BookDto;
import com.library.domain.book.OutgoingRentDto;
import com.library.domain.rent.Rent;
import com.library.domain.user.User;
import com.library.domain.user.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class RentMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private BookMapper bookMapper;

    public List<OutgoingRentDto> mapToOutgoingRentDtoList(List<Rent> rents) {

        return rents.stream()
                .map(rent -> new OutgoingRentDto(
                        rent.getId(),
                        rent.getRentDate(),
                        rent.getReturnDate(),
                        mapToBookDtoList(rent.getRentedBooks()),
                        mapToUserDto(rent.getUser())))
                .collect(toList());
    }

    private List<BookDto> mapToBookDtoList(List<Book> books) {
        return bookMapper.mapToBookDtoList(books);
    }

    private UserDto mapToUserDto(User user) {
        return userMapper.mapToUserDto(user);
    }
}
