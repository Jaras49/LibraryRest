package com.library.repository.title;

import com.library.domain.title.Title;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
@Repository
public interface TitleDao extends CrudRepository<Title, Long> {

    @Query
    Optional<Title> findByTitle(@Param("TITLE") String title);

    @Query
    List<Title> findPublishedBeforeYear(@Param("YEAR_OF_PUBLICATION") int year);

    @Query(nativeQuery = true)
    List<Title> findAllByTitle(@Param("TITLE") String title);

    @Query(nativeQuery = true)
    List<Title> findPublishedAfterYear(@Param("YEAR_OF_PUBLICATION") int year);

    List<Title> findByAuthor(String author);

    @Override
    Optional<Title> findById(Long id);

    @Override
    Title save(Title title);


}
