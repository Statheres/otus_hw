package com.otus.hw.hw08.api.session;

public interface SessionManager extends AutoCloseable {
    void beginSession();

    void commitSession();

    void rollbackSession();

    void close();

    DBSession getCurrentSession();
}
