package com.library.domain.rent;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RentDto {

    private Long userId;
    private Long[] booksId;
}
