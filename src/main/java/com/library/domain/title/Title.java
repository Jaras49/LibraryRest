package com.library.domain.title;

import com.library.domain.book.Book;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "TITLES")
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
            fetch = FetchType.EAGER,
            mappedBy = "title",
            targetEntity = Book.class)
    private List<Book> books = new ArrayList<>();

    public Title(String titleName, String author, int yearOfPublication) {

        this.titleName = titleName;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Title)) return false;
        Title title = (Title) o;
        return yearOfPublication == title.yearOfPublication &&
                Objects.equals(id, title.id) &&
                Objects.equals(titleName, title.titleName) &&
                Objects.equals(author, title.author) &&
                Objects.equals(books, title.books);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, titleName, author, yearOfPublication, books);
    }
}
