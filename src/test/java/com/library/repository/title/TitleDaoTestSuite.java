package com.library.repository.title;

import com.library.domain.title.Title;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TitleDaoTestSuite {

    @Autowired
    private TitleDao titleDao;

    @Before
    public void setUp() throws Exception {

        //Given
        //titleDao.save(new Title(1L, "JAVA CORE", "xyz", 2000));
        //titleDao.save(new Title(2L, "HEAD FIRST JAVA", "xyz", 2001));
        //titleDao.save(new Title(3L, "CLEAN CODE", "xyz", 2005));
        //titleDao.save(new Title(4L, "W PUSTYNI I W PUSZCZY", "SIENKIEWICZ", 1992));
        //titleDao.save(new Title(5L, "W PUSTYNI I W DESZCZU", "NOWAK", 1992));
        //titleDao.save(new Title(6L, "WITH DRAGONS AND W PUSTYNI", "JAROMIN", 2005));
    }

    @After
    public void tearDown() throws Exception {

        //CleanUp
        titleDao.deleteAll();
    }

    @Test
    public void shouldFindByTitle() {

            //When
            Optional<Title> java = titleDao.findByTitle("JAVA CORE");

            //Then
            assertEquals("JAVA CORE", java.get().getTitleName());
    }

    @Test
    public void shouldFindAllByTitle() {

        //When
        List<Title> titles = titleDao.findAllByTitle("W PUSTYNI");

        //Then
        assertEquals(3, titles.size());
    }

    @Test
    public void shouldFindPublishedBeforeYear() {

        //When
        List<Title> publishedBeforeYear = titleDao.findPublishedBeforeYear(2000);

        //Then
        assertEquals(2, publishedBeforeYear.size());
    }

    @Test
    public void shouldFindPublishedAfterYear() {

        //When
        List<Title> publishedAfterYear = titleDao.findPublishedAfterYear(1992);

        //Then
        assertEquals(4, publishedAfterYear.size());
    }

    @Test
    public void findByAuthor() {

        //When
        List<Title> list1 = titleDao.findByAuthor("xyz");
        List<Title> list2 = titleDao.findByAuthor("NOWAK");

        //Then
        assertEquals(3, list1.size());
        assertEquals(1, list2.size());
    }
}