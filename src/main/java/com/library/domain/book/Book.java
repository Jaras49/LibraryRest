package com.library.domain.book;

import com.library.domain.rent.Rent;
import com.library.domain.title.Title;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "BOOKS")
@Getter
public class Book {

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

    @ManyToOne
    @JoinColumn(name = "RENT_ID")
    private Rent rent;

    public enum Status {
        AVAILABLE, RENTED, LOST, DESTROYED;
    }

    public Book() {
        this.status = Status.AVAILABLE;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public void setTitle(Title title) {
        this.title = title;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}



