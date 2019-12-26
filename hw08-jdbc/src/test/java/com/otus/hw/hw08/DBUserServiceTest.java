package com.otus.hw.hw08;

import com.otus.hw.hw08.api.dao.UserDao;
import com.otus.hw.hw08.api.model.User;
import com.otus.hw.hw08.api.service.DBUserService;
import com.otus.hw.hw08.api.service.DBUserServiceImpl;
import com.otus.hw.hw08.api.session.SessionManager;
import com.otus.hw.hw08.h2.DataSourceH2;
import com.otus.hw.hw08.jdbc.DBExecutor;
import com.otus.hw.hw08.jdbc.dao.UserDaoImpl;
import com.otus.hw.hw08.jdbc.session.SessionManagerImpl;
import com.otus.hw.hw08.jdbc.sql.H2SQLQueryBuilder;
import com.otus.hw.hw08.jdbc.sql.exception.IdFieldNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class DBUserServiceTest {
    private DBExecutor<User> dbExecutor;
    private SessionManager sessionManager;
    private DBUserService userService;
    private UserDao userDao;

    private User testUser;

    @BeforeEach
    void setUp() throws IdFieldNotFoundException, SQLException {
        sessionManager = new SessionManagerImpl(new DataSourceH2());
        dbExecutor = new DBExecutor<>(User.class, new H2SQLQueryBuilder<>(User.class));
        userDao = new UserDaoImpl(dbExecutor, sessionManager);
        userService = new DBUserServiceImpl(userDao);

        sessionManager.beginSession();
        dbExecutor.createTable(sessionManager.getCurrentSession().getConnection());
        sessionManager.commitSession();

        testUser = new User("TestUser", 35);
    }

    @AfterEach
    void tearDown() throws SQLException {
        sessionManager.beginSession();
        dbExecutor.dropTable(sessionManager.getCurrentSession().getConnection());
        sessionManager.commitSession();
    }

    @Test
    void saveUser_Ok() {
        userService.saveUser(testUser);
    }

    @Test
    void getUser_Ok() {
        long id = userService.saveUser(testUser);
        User user = userService.getUser(id).get();
        Assertions.assertEquals(testUser, user);
    }

    @Test
    void getUser_Fail() {
        Assertions.assertTrue(userService.getUser(100500).isEmpty());
    }

    @Test
    void updateUser_Ok() {
        long id = userService.saveUser(testUser);

        User userToUpdate = userService.getUser(id).get();
        userToUpdate.name = "UpdatedUser";

        userService.updateUser(userToUpdate);

        User updatedUser = userService.getUser(id).get();

        Assertions.assertEquals(userToUpdate, updatedUser);
    }
}
