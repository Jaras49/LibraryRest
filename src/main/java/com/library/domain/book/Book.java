package com.library.domain.book;

import com.library.domain.title.Title;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BOOKS")
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private enum Status {
        available, rented, lost, destroyed;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "TITLE_ID")
    private Title title;

    @Column(name = "STATUS")
    @NotNull
    private Status status;
}



