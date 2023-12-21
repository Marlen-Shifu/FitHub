package com.example.fithub.db;

import com.example.fithub.HelloApplication;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SQLiteHandler implements DatabaseHandler {
    private static final String JDBC_URL = "jdbc:sqlite:" + "src/main/resources/com/example/fithub/database/sqlite.db";

    public SQLiteHandler() {
        createUsersTableIfNotExists();
    }

    private void createUsersTableIfNotExists() {
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(
                     "CREATE TABLE IF NOT EXISTS users (" +
                             "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                             "username TEXT NOT NULL," +
                             "password TEXT NOT NULL," +
                             "email TEXT NOT NULL," +
                             "gender TEXT NOT NULL)"
             )) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean authenticateUser(String username, String password) {
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try (Connection connection = DriverManager.getConnection(JDBC_URL);
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                return resultSet.next(); // If there's at least one result, authentication is successful
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Add the close method for ResultSet
    private void close(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void close(PreparedStatement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private void close(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<String> fetchData(String tableName, List<String> parameters) {
        List<String> data = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = DriverManager.getConnection(JDBC_URL);

            // Build the query dynamically based on tableName and parameters
            StringBuilder queryBuilder = new StringBuilder("SELECT * FROM ");
            queryBuilder.append(tableName);

            if (parameters != null && !parameters.isEmpty()) {
                queryBuilder.append(" WHERE ");
                for (int i = 0; i < parameters.size(); i++) {
                    queryBuilder.append(parameters.get(i));
                    if (i < parameters.size() - 1) {
                        queryBuilder.append(" AND ");
                    }
                }
            }

            String query = queryBuilder.toString();
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                String column1Value = resultSet.getString("column1");
                data.add(column1Value);
                // Process other columns as needed
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(resultSet);
            close(preparedStatement);
            close(connection);
        }

        return data;
    }

    @Override
    public void insertData(String tableName, List<String> columnNames, List<String> values) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(JDBC_URL);

            // Build the query for inserting data dynamically
            StringBuilder queryBuilder = new StringBuilder("INSERT INTO ");
            queryBuilder.append(tableName).append(" (");

            for (int i = 0; i < columnNames.size(); i++) {
                queryBuilder.append(columnNames.get(i));
                if (i < columnNames.size() - 1) {
                    queryBuilder.append(", ");
                }
            }

            queryBuilder.append(") VALUES (");

            for (int i = 0; i < values.size(); i++) {
                queryBuilder.append("?");
                if (i < values.size() - 1) {
                    queryBuilder.append(", ");
                }
            }

            queryBuilder.append(")");

            String query = queryBuilder.toString();
            preparedStatement = connection.prepareStatement(query);

            // Set parameter values
            for (int i = 0; i < values.size(); i++) {
                preparedStatement.setString(i + 1, values.get(i));
            }

            // Execute the query
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            close(preparedStatement);
            close(connection);
        }
    }

    // Implement other methods for SQLite operations
}
