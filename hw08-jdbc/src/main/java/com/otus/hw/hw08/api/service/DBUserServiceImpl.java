package com.otus.hw.hw08.api.service;

import com.otus.hw.hw08.api.dao.UserDao;
import com.otus.hw.hw08.api.model.User;
import com.otus.hw.hw08.api.session.SessionManager;

import java.util.Optional;

public class DBUserServiceImpl implements DBUserService {
    private final UserDao userDao;

    public DBUserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public long saveUser(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                long userId = userDao.save(user);
                sessionManager.commitSession();
                return userId;
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DBServiceException(e);
            }
        }
    }

    @Override
    public Optional<User> getUser(long id) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                return userDao.findById(id);
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DBServiceException(e);
            }
        }
    }

    @Override
    public void updateUser(User user) {
        try (SessionManager sessionManager = userDao.getSessionManager()) {
            sessionManager.beginSession();
            try {
                userDao.update(user);
                sessionManager.commitSession();
            } catch (Exception e) {
                sessionManager.rollbackSession();
                throw new DBServiceException(e);
            }
        }
    }
}
