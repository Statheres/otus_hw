package com.otus.hw.hw08.jdbc.sql;

import com.otus.hw.hw08.jdbc.annotation.Id;
import com.otus.hw.hw08.jdbc.sql.exception.IdFieldNotFoundException;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

abstract class AbstractSQLQueryBuilder<T> implements SQLQueryBuilder {
    private final Class<T> objectClass;
    private final List<Field> accessibleFields;
    private final Field idField;

    public AbstractSQLQueryBuilder(Class<T> objectClass) throws IdFieldNotFoundException {
        this.objectClass = objectClass;

        this.accessibleFields = Arrays.stream(objectClass.getDeclaredFields())
                .filter(field -> getMappedTypes().containsKey(field.getType()))
                .collect(Collectors.toList());

        this.idField = accessibleFields.stream()
                .filter(field -> !Objects.isNull(field.getAnnotation(Id.class)))
                .findFirst()
                .orElseThrow(IdFieldNotFoundException::new);

        this.accessibleFields.remove(this.idField);
    }

    @Override
    public String buildCreateTableQuery() {
        StringBuilder builder = new StringBuilder();

        builder
                .append("create table ")
                .append(objectClass.getSimpleName())
                .append('(');

        accessibleFields.forEach(
                field -> {
                    builder
                            .append(field.getName())
                            .append(' ')
                            .append(getMappedTypes().get(field.getType()))
                            .append(',');
                }
        );

        builder
                .append(idField.getName())
                .append(' ')
                .append(getMappedTypes().get(idField.getType()))
                .append(' ')
                .append("auto_increment")
                .append(')');

        return builder.toString();
    }

    @Override
    public String buildSelectQuery() {
        return String.format("select * from %s where %s=?", objectClass.getSimpleName(), idField.getName());
    }

    @Override
    public String buildUpdateQuery() {
        StringBuilder builder = new StringBuilder();

        builder
                .append("update ")
                .append(objectClass.getSimpleName())
                .append(" set ");

        accessibleFields.forEach(
                field -> {
                    builder
                            .append(field.getName())
                            .append("=?,");
                }
        );

        builder
                .deleteCharAt(builder.length() - 1)
                .append(" where ")
                .append(idField.getName())
                .append("=?");

        return builder.toString();
    }

    @Override
    public String buildInsertQuery() {
        StringBuilder builder = new StringBuilder();

        builder
                .append("insert into ")
                .append(objectClass.getSimpleName())
                .append('(');

        accessibleFields.forEach(
                field -> {
                    builder
                            .append(field.getName())
                            .append(',');
                }
        );

        builder
                .deleteCharAt(builder.length() - 1)
                .append(')')
                .append(" values(");

        accessibleFields.forEach(
                field -> {
                    builder.append("?,");
                }
        );

        builder
                .deleteCharAt(builder.length() - 1)
                .append(')');

        return builder.toString();
    }

    @Override
    public String buildDropTableQuery() {
        return String.format("drop table %s if exists", objectClass.getSimpleName());
    }

    @Override
    public String buildIsTableExistsQuery() {
        return String.format(
                "select * from INFORMATION_SCHEMA.TABLES where TABLE_NAME='%s'",
                objectClass.getSimpleName().toUpperCase()
        );
    }

    @Override
    public List<Field> getAccessibleFields() {
        return accessibleFields;
    }

    @Override
    public Field getIdField() {
        return idField;
    }

    protected abstract Map<Class<?>, String> getMappedTypes();
}
