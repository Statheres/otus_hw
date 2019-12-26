package com.otus.hw.hw08.api.service;

import com.otus.hw.hw08.api.model.User;

import java.util.Optional;

public interface DBUserService {
    long saveUser(User user);

    Optional<User> getUser(long id);

    void updateUser(User user);
}
