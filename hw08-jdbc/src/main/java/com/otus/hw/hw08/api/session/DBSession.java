package com.otus.hw.hw08.api.session;

import java.sql.Connection;

public interface DBSession {
    Connection getConnection();
}
