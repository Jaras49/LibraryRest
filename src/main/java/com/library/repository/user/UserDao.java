package com.library.repository.user;

import com.library.domain.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface UserDao extends CrudRepository<User, Long> {

    @Override
    Optional<User> findById(Long id);

    @Override
    User save(User user);

    List<User> findByLastName(String lastName);

    List<User> findByFirstName(String firstName);

    @Override
    void deleteById(Long id);
}
