package com.library.domain.user;

import com.library.domain.rent.Rent;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
@Table(name = "USERS")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "USER_ID", unique = true)
    private Long id;

    @Column(name = "FIRST_NAME")
    @NotNull
    private String firstName;

    @Column(name = "LAST_NAME")
    @NotNull
    private String lastName;

    @Column(name = "ACCOUNT_CREATION_DATE")
    @NotNull
    private Date accountCreationDate;

    @OneToMany(
            fetch = FetchType.EAGER,
            mappedBy = "user",
            targetEntity = Rent.class)
    private List<Rent> rents = new ArrayList<>();

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountCreationDate = new Date();
    }
}