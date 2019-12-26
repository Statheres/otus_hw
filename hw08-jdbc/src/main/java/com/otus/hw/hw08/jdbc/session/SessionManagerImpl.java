package com.otus.hw.hw08.jdbc.session;

import com.otus.hw.hw08.api.session.DBSession;
import com.otus.hw.hw08.api.session.SessionManager;
import com.otus.hw.hw08.api.session.SessionManagerException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class SessionManagerImpl implements SessionManager {
    private static final int TIMEOUT = 5;

    private final DataSource dataSource;

    private Connection connection;
    private DBSession dbSession;

    public SessionManagerImpl(DataSource dataSource) {
        if (dataSource == null) {
            throw new SessionManagerException("DataSource in null");
        }
        this.dataSource = dataSource;
    }

    @Override
    public void beginSession() {
        try {
            connection = dataSource.getConnection();
            dbSession = new DBSessionImpl(connection);
        } catch (SQLException e) {
            throw new SessionManagerException(e);
        }
    }

    @Override
    public void commitSession() {
        checkConnection();
        try {
            connection.commit();
        } catch (SQLException e) {
            throw new SessionManagerException(e);
        }
    }

    @Override
    public void rollbackSession() {
        checkConnection();
        try {
            connection.rollback();
        } catch (SQLException e) {
            throw new SessionManagerException(e);
        }
    }

    @Override
    public void close() {
        checkConnection();
        try {
            connection.close();
        } catch (SQLException e) {
            throw new SessionManagerException(e);
        }
    }

    @Override
    public DBSession getCurrentSession() {
        checkConnection();
        return dbSession;
    }

    private void checkConnection() {
        try {
            if (connection == null || !connection.isValid(TIMEOUT)) {
                throw new SessionManagerException("Connection is invalid");
            }
        } catch (SQLException ex) {
            throw new SessionManagerException(ex);
        }
    }
}
