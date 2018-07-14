package com.library.domain.book;

import com.library.domain.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class OutgoingRentDto {

    private Long rentId;
    private Date rentDate;
    private LocalDate returnDate;
    private List<BookDto> rentedBooks = new ArrayList<>();
    private UserDto user;
}
