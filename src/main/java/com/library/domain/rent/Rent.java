package com.library.domain.rent;

import com.library.domain.book.Book;
import com.library.domain.user.User;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Entity
@Table(name = "RENTS")
@Getter
public class Rent {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ID", unique = true)
    private Long id;

    @Column(name = "RENT_DATE")
    @NotNull
    private Date rentDate;

    @Column(name = "RETURN_DATE")
    @NotNull
    private LocalDate returnDate;

    @OneToMany(
            //cascade = {CascadeType.PERSIST, CascadeType.MERGE},
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            mappedBy = "rent",
            targetEntity = Book.class)
    private List<Book> rentedBooks = new ArrayList<>();

    @ManyToOne(//cascade = {CascadeType.PERSIST, CascadeType.MERGE})
            cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID")
    private User user;

    public Rent(LocalDate returnDate) {
        this.rentDate = new Date();
        this.returnDate = returnDate;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
