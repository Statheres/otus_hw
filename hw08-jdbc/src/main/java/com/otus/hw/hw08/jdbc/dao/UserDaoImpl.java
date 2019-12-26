package com.otus.hw.hw08.jdbc.dao;

import com.otus.hw.hw08.api.dao.UserDao;
import com.otus.hw.hw08.api.dao.UserDaoException;
import com.otus.hw.hw08.api.model.User;
import com.otus.hw.hw08.api.session.SessionManager;
import com.otus.hw.hw08.jdbc.DBExecutor;

import java.sql.Connection;
import java.util.Optional;

public class UserDaoImpl implements UserDao {
    private final DBExecutor<User> dbExecutor;
    private final SessionManager sessionManager;

    public UserDaoImpl(DBExecutor<User> dbExecutor, SessionManager sessionManager) {
        this.dbExecutor = dbExecutor;
        this.sessionManager = sessionManager;
    }

    @Override
    public Optional<User> findById(long id) {
        try {
            return Optional.ofNullable(dbExecutor.load(getConnection(), id));
        } catch (Exception e) {
            throw new UserDaoException(e);
        }
    }

    @Override
    public long save(User user) {
        try {
            return dbExecutor.create(getConnection(), user);
        } catch (Exception e) {
            throw new UserDaoException(e);
        }
    }

    @Override
    public void update(User user) {
        try {
            dbExecutor.update(getConnection(), user);
        } catch (Exception e) {
            throw new UserDaoException(e);
        }
    }

    @Override
    public SessionManager getSessionManager() {
        return sessionManager;
    }

    private Connection getConnection() {
        return sessionManager.getCurrentSession().getConnection();
    }
}
