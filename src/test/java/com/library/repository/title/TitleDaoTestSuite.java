package com.library.repository.title;

import com.library.domain.title.Title;
import com.library.repository.AbstractDaoTest;
import org.junit.*;
import java.util.List;

import static org.junit.Assert.*;

public class TitleDaoTestSuite extends AbstractDaoTest {

    @Before
    public void setUp() throws Exception {

        //Given
        titleDao.save(new Title("JAVA CORE", "xyz", 2000));
        titleDao.save(new Title("HEAD FIRST JAVA", "xyz", 2001));
        titleDao.save(new Title("CLEAN CODE", "xyz", 2005));
        titleDao.save(new Title("W PUSTYNI I W PUSZCZY", "SIENKIEWICZ", 1992));
        titleDao.save(new Title("W PUSTYNI I W DESZCZU", "NOWAK", 1992));
        titleDao.save(new Title("WITH DRAGONS AND W PUSTYNI", "JAROMINSKI", 2005));
    }

    @Test
    public void shouldFindPublishedBeforeYear() {

        //When
        List<Title> publishedBeforeYear = titleDao.findPublishedBeforeYear(2000);

        //then
        assertEquals(2, publishedBeforeYear.size());
    }

    @Test
    public void shouldFindPublishedAfterYear() {

        //When
        List<Title> publishedAfterYear = titleDao.findPublishedAfterYear(2000);

        //Then
        assertEquals(3, publishedAfterYear.size());
    }

    @Test
    public void shouldFindAllByTitle() {

        //When
        List<Title> java = titleDao.findAllByTitle("JAVA");
        List<Title> pustyni = titleDao.findAllByTitle("PUSTYNI");

        //then
        assertEquals(2, java.size());
        assertEquals(3, pustyni.size());
    }

    @Test
    public void shouldFindAllByAuthor() {

        //When
        List<Title> allByAuthor = titleDao.findAllByAuthor("xy");

        //then
        assertEquals(3, allByAuthor.size());
    }
}