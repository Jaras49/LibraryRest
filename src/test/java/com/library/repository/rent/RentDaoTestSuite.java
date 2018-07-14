package com.library.repository.rent;

import com.library.domain.rent.Rent;
import com.library.repository.AbstractDaoTest;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.Assert.*;

public class RentDaoTestSuite extends AbstractDaoTest {

    @Test
    public void shouldFindRentsWithExpiredReturnDate() {

        //Given
        Rent rent = new Rent(LocalDate.now().plusMonths(2));
        Rent rent1 = new Rent(LocalDate.now().plusMonths(1));
        Rent rent2 = new Rent(LocalDate.now().minusMonths(1));
        Rent rent3 = new Rent(LocalDate.now().minusMonths(3));
        Rent rent4 = new Rent(LocalDate.now().minusMonths(4));

        rentDao.save(rent);
        rentDao.save(rent1);
        rentDao.save(rent2);
        rentDao.save(rent3);
        rentDao.save(rent4);

        //When
        List<Rent> rentsWithExpiredRentDate = rentDao.findRentsWithExpiredReturnDate();

        //Then
        assertEquals(3, rentsWithExpiredRentDate.size());
    }
}