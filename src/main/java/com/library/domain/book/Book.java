package com.library.domain.book;

import com.library.domain.rent.Rent;
import com.library.domain.title.Title;
import lombok.Getter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(
                name = "Book.findRented",
                query = "FROM Book WHERE Status = 1"
        ),
        @NamedQuery(
                name = "Book.findAvailable",
                query = "FROM Book WHERE Status = 0"
        ),
        @NamedQuery(
                name = "Book.findLost",
                query = "FROM Book WHERE Status = 2"
        ),
        @NamedQuery(
                name = "Book.findDestroyed",
                query = "FROM Book WHERE Status = 3"
        )
})
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
        AVAILABLE, RENTED, LOST, DESTROYED
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id) &&
                Objects.equals(title, book.title) &&
                status == book.status &&
                Objects.equals(rent, book.rent);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, title, status, rent);
    }
}



