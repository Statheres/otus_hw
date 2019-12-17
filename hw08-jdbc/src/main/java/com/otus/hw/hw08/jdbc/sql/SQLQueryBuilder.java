package com.otus.hw.hw08.jdbc.sql;

import java.lang.reflect.Field;
import java.util.List;

public interface SQLQueryBuilder {
    String buildDropTableQuery();

    String buildCreateTableQuery();

    String buildSelectQuery();

    String buildUpdateQuery();

    String buildInsertQuery();

    String buildIsTableExistsQuery();

    List<Field> getAccessibleFields();

    Field getIdField();
}
