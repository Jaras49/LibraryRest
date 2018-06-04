package com.library.repository;

import com.library.domain.user.User;
import com.library.repository.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbService {

    @Autowired
    private UserDao userDao;

    public User createNewUser(User user) {
        return userDao.save(user);
    }
}
