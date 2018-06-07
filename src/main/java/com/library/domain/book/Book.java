package com.library.domain.book;

import com.library.domain.rent.Rent;
import com.library.domain.title.Title;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "BOOKS")
@Getter
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(unique = true, name = "ID")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "TITLE_ID")
    private Title title;

    @Column(name = "STATUS")
    @NotNull
    private Status status;

    @ManyToOne
    @JoinColumn(name = "RENT_ID")
    private Rent rent;

    public enum Status {
        available, rented, lost, destroyed;
    }

    public Book(Status status) {
        this.status = status;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public void setTitle(Title title) {
        this.title = title;
    }
}



