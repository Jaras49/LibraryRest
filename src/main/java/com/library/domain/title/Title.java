package com.library.domain.title;

import com.library.domain.book.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@NamedQueries({
        @NamedQuery(
                name = "Title.findByTitle",
                query = "FROM Title WHERE titleName = :TITLE"
        ),
        @NamedQuery(
                name = "Title.findPublishedBeforeYear",
                query = "FROM Title WHERE yearOfPublication < :YEAR_OF_PUBLICATION"
        )
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Title.findAllByTitle",
                query = "SELECT * FROM TITLES WHERE TITLE_NAME LIKE CONCAT ('%', :TITLE, '%')",
                resultClass = Title.class
        ),
        @NamedNativeQuery(
                name = "Title.findPublishedAfterYear",
                query = "SELECT * FROM TITLES WHERE YEAR_OF_PUBLICATION > :YEAR_OF_PUBLICATION",
                resultClass = Title.class
        )
})

@Entity
@Table(name = "TITLES")
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Title {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "TITLE_ID")
    private Long id;

    @NotNull
    @Column(name = "TITLE_NAME", unique = true)
    private String titleName;

    @NotNull
    @Column(name = "AUTHOR")
    private String author;

    @NotNull
    @Column(name = "YEAR_OF_PUBLICATION")
    private int yearOfPublication;

    @OneToMany(
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "title",
            targetEntity = Book.class)
    private List<Book> books = new ArrayList<>();
}
