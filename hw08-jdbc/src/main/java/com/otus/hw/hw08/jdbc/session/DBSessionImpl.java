package com.otus.hw.hw08.jdbc.session;

import com.otus.hw.hw08.api.session.DBSession;

import java.sql.Connection;

public class DBSessionImpl implements DBSession {
    private final Connection connection;

    public DBSessionImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public Connection getConnection() {
        return connection;
    }
}
