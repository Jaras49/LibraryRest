package com.library.domain.book;

import com.library.domain.title.TitleDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class BookDto {

    private Long id;
    private TitleDto title;
    private Book.Status status;
}
