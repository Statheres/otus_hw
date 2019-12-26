package com.otus.hw.hw08.jdbc.sql;

import com.otus.hw.hw08.jdbc.sql.exception.IdFieldNotFoundException;

import java.util.HashMap;
import java.util.Map;

public class H2SQLQueryBuilder<T> extends AbstractSQLQueryBuilder<T> {
    private static final Map<Class<?>, String> dbMappedTypes = new HashMap<Class<?>, String>() {{
        put(String.class, "varchar(256)");
        put(Long.class, "long");
        put(Integer.class, "int");
    }};

    public H2SQLQueryBuilder(Class<T> objectClass) throws IdFieldNotFoundException {
        super(objectClass);
    }

    @Override
    protected Map<Class<?>, String> getMappedTypes() {
        return dbMappedTypes;
    }
}
