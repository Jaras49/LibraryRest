package com.library.mapper;

import com.library.domain.title.Title;
import com.library.domain.title.TitleDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TitleMapperTestSuite {

    @Autowired
    private TitleMapper titleMapper;

    @Test
    public void shouldMapToTitle() {

        //Given
        TitleDto titleDto = new TitleDto(1L, "Title", "Author", 1995);

        //When
        Title title = titleMapper.mapToTitle(titleDto);

        //Then
        assertEquals("Title", title.getTitleName());
        assertEquals("Author", title.getAuthor());
        assertEquals(1995, title.getYearOfPublication());
    }

    @Test
    public void shouldMapToTitleDtoList() {

        //Given
        Title title = new Title("Title", "Author", 2000);
        Title title1 = new Title("Title", "Author", 2000);

        //When
        List<TitleDto> titleDtos = titleMapper.mapToTitleDtoList(Arrays.asList(title, title1));

        //then
        assertEquals(2, titleDtos.size());

        titleDtos.forEach(titleDto -> {
            assertEquals("Title", titleDto.getTitle());
            assertEquals("Author", titleDto.getAuthor());
            assertEquals(2000, titleDto.getYearOfPublication());
        });
    }
}