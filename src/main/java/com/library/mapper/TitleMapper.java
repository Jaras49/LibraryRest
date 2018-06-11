package com.library.mapper;

import com.library.domain.title.Title;
import com.library.domain.title.TitleDto;
import org.springframework.stereotype.Component;

@Component
public class TitleMapper {

    public Title mapToTitle (TitleDto titleDto) {

        return new Title(titleDto.getTitle(), titleDto.getAuthor(), titleDto.getYearOfPublication());
    }
}
