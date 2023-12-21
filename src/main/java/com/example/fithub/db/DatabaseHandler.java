package com.example.fithub.db;

import java.util.List;

public interface DatabaseHandler {
    List<String> fetchData(String tableName, List<String> parameters);
    void insertData(String tableName, List<String> columnNames, List<String> values);
    boolean authenticateUser(String username, String password);
}
