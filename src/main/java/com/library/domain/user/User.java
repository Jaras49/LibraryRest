package com.library.domain.user;

import com.library.repository.DbService;
import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
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

    public static void main(String[] args) {

        /**
        User user = new User(1L, "Jarek,", "Godwin", new Date());
        DbService dbService = new DbService();
        dbService.createNewUser(user);
        System.out.println(user.getAccountCreationDate());
*/
    }
}
