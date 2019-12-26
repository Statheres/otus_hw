package com.otus.hw.hw08.api.dao;

import com.otus.hw.hw08.api.model.User;
import com.otus.hw.hw08.api.session.SessionManager;

import java.util.Optional;

public interface UserDao {
    Optional<User> findById(long id);

    long save(User user);

    void update(User user);

    SessionManager getSessionManager();
}
