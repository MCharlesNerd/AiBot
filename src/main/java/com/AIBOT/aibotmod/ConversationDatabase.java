package com.AIBOT.aibotmod;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

//public class ConversationDatabase {
//    private static Map<String, String> conversations = new HashMap<>();
//
//    public void saveConversation(String conversationId, String message) {
//        conversations.put(conversationId, message);
//    }
//
//    public String getConversation(String conversationId) {
//        return conversations.get(conversationId);
//    }
//}

public class ConversationDatabase {
    private Connection connection;

    public void init() throws SQLException {
        String url = "jdbc:sqlite:conversations.db";
        connection = DriverManager.getConnection(url);
        createTable();
    }

    private void createTable() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS conversations (id TEXT PRIMARY KEY, message TEXT)";
        connection.createStatement().execute(sql);
    }

    public void saveConversation(String id, String message) throws SQLException {
        String sql = "INSERT INTO conversations VALUES (?, ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        statement.setString(2, message);
        statement.executeUpdate();
    }

    public String getConversation(String id) throws SQLException {
        String sql = "SELECT message FROM conversations WHERE id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getString("message");
        } else {
            return null;
        }
    }

    public void close() throws SQLException {
        connection.close();
    }
}
