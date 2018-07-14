package com.library.mapper;

import com.library.domain.book.OutgoingRentDto;
import com.library.domain.rent.Rent;
import com.library.domain.user.UserDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class RentMapperTestSuite {

    @InjectMocks
    private RentMapper rentMapper;
    @Mock
    private BookMapper bookMapper;
    @Mock
    private UserMapper userMapper;

    @Test
    public void shouldMapToOutgoingDtoList() {

        //Given
        UserDto userDto = new UserDto(1L, "TEST", "USER");
        Rent rent = new Rent(LocalDate.now().plusMonths(2));
        Rent rent1 = new Rent(LocalDate.now().plusMonths(2));

        when(bookMapper.mapToBookDtoList(Collections.emptyList())).thenReturn(Collections.emptyList());
        when(userMapper.mapToUserDto(rent.getUser())).thenReturn(userDto);
        when(userMapper.mapToUserDto(rent1.getUser())).thenReturn(userDto);

        //When
        List<OutgoingRentDto> outgoingRentDtos = rentMapper.mapToOutgoingRentDtoList(Arrays.asList(rent, rent1));

        //Then
        assertEquals(2, outgoingRentDtos.size());

        outgoingRentDtos.forEach(outgoingRentDto -> {
            assertEquals(LocalDate.now().plusMonths(2), outgoingRentDto.getReturnDate());
            assertEquals(1L, outgoingRentDto.getUser().getId().longValue());
            assertEquals("TEST", outgoingRentDto.getUser().getFirstName());
            assertEquals("USER", outgoingRentDto.getUser().getLastName());
        });
    }
}