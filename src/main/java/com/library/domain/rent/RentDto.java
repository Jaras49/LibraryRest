package com.library.domain.rent;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class RentDto {

    private Long userId;
    private Long[] booksId;
}
