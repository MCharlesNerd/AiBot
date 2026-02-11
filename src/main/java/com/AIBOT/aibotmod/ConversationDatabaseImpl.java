package com.AIBOT.aibotmod;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

//public class ConversationDatabaseImpl extends ConversationDatabase {
//    private static Map<String, String> conversations = new HashMap<>();
//
//    @Override
//    public void saveConversation(String conversationId, String message) {
//        // implementation of saving a conversation
//    }
//
//    @Override
//    public String getConversation(String conversationId) {
//        return conversations.get(conversationId);
//    }
//
//    public void loadConversations() {
//        //conversations = new HashMap<>();
//        // load conversations from file or database
//        try (FileReader reader = new FileReader("conversations.txt")) {
//            BufferedReader bufferedReader = new BufferedReader(reader);
//            String line;
//            while ((line = bufferedReader.readLine()) != null) {
//                String[] parts = line.split(",");
//                if (parts.length == 2) {
//                    conversations.put(parts[0], parts[1]);
//                }
//            }
//        } catch (IOException e) {
//            System.err.println("Error loading conversations: " + e.getMessage());
//        }
//    }
//}

public class ConversationDatabaseImpl extends ConversationDatabase {
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