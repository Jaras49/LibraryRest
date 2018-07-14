package com.library.mapper;

import com.library.domain.title.Title;
import com.library.domain.title.TitleDto;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class TitleMapper {

    public Title mapToTitle (TitleDto titleDto) {

        return new Title(titleDto.getTitle(), titleDto.getAuthor(), titleDto.getYearOfPublication());
    }

    public List<TitleDto> mapToTitleDtoList(List<Title> titles) {

        return titles.stream()
                .map(title -> new TitleDto(
                        title.getId(),
                        title.getTitleName(),
                        title.getAuthor(),
                        title.getYearOfPublication()))
                .collect(toList());
    }
}
