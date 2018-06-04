package com.library.domain.title;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TitleDto {

    private Long id;
    private String title;
    private String author;
    private int yearOfPublication;
}
