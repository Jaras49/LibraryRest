package com.library.domain.title;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
        @NamedQuery(
                name = "Title.findByTitle",
                query = "FROM TITLES WHERE TITLE_NAME = :TITLE"
        ),
        @NamedQuery(
                name = "Title.findPublishedBeforeYear",
                query = "FROM TITLES WHERE YEAR_OF_PUBLICATION < :YEAR"
        )
})
@NamedNativeQueries({
        @NamedNativeQuery(
                name = "Title.findAllByTitle",
                query = "SELECT * FROM TITLES WHERE TITLE_NAME = :TITLE",
                resultClass = Title.class
        ),
        @NamedNativeQuery(
                name = "Title.findPublishedAfterYear",
                query = "SELECT * FROM TITLES WHERE YEAR_OF_PUBLICATION > :YEAR"
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
    @Column(name = "TITLE_NAME")
    private String titleName;

    @NotNull
    @Column(name = "AUTHOR")
    private String author;

    @NotNull
    @Column(name = "YEAR_OF_PUBLICATION")
    private int yearOfPublication;
}
