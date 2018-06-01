package com.library.repository;

import com.library.domain.user.User;
import com.library.domain.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DbService {

    @Autowired
    private UserDao userDao;

    public void createNewUser(User user) {
        userDao.save(user);
    }
}
