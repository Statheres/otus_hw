package com.otus.hw.hw08.jdbc;

import com.otus.hw.hw08.jdbc.sql.SQLQueryBuilder;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.List;

public class DBExecutor<T> {
    private final Class<T> objectClass;
    private final SQLQueryBuilder queryBuilder;

    public DBExecutor(Class<T> objectClass, SQLQueryBuilder queryBuilder) {
        this.queryBuilder = queryBuilder;
        this.objectClass = objectClass;
    }

    public long create(Connection connection, T value) throws SQLException, IllegalAccessException {
        try (PreparedStatement ps = connection.prepareStatement(queryBuilder.buildInsertQuery(), Statement.RETURN_GENERATED_KEYS)) {
            List<Field> accessibleFields = queryBuilder.getAccessibleFields();
            for (int i = 0; i < accessibleFields.size(); ++i) {
                ps.setObject(i + 1, accessibleFields.get(i).get(value));
            }

            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                return rs.getLong(1);
            }
        }
    }

    public void update(Connection connection, T value) throws SQLException, IllegalAccessException {
        try (PreparedStatement ps = connection.prepareStatement(queryBuilder.buildUpdateQuery())) {
            List<Field> accessibleFields = queryBuilder.getAccessibleFields();
            for (int i = 0; i < accessibleFields.size(); ++i) {
                ps.setObject(i + 1, accessibleFields.get(i).get(value));
            }

            Field idField = queryBuilder.getIdField();
            ps.setObject(accessibleFields.size() + 1, idField.get(value));

            ps.execute();
        }
    }

    public <T> T load(Connection connection, long id) throws SQLException, IllegalAccessException, InstantiationException {
        try (PreparedStatement ps = connection.prepareStatement(queryBuilder.buildSelectQuery())) {
            ps.setObject(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                T obj = null;

                if (rs.next()) {
                    obj = (T) objectClass.newInstance();

                    Field idField = queryBuilder.getIdField();
                    idField.set(obj, rs.getObject(idField.getName()));

                    for (Field field : queryBuilder.getAccessibleFields()) {
                        field.set(obj, rs.getObject(field.getName()));
                    }
                }

                return obj;
            }
        }
    }

    public boolean isTableExists(Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(queryBuilder.buildIsTableExistsQuery())) {
            try (ResultSet rs = ps.executeQuery()) {
                return rs.next();
            }
        }
    }

    public void createTable(Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(queryBuilder.buildCreateTableQuery())) {
            ps.executeUpdate();
        }
    }

    public void dropTable(Connection connection) throws SQLException {
        try (PreparedStatement ps = connection.prepareStatement(queryBuilder.buildDropTableQuery())) {
            ps.executeUpdate();
        }
    }
}
