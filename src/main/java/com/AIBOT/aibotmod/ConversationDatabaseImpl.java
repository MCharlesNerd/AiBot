package com.AIBOT.aibotmod;

import java.util.HashMap;
import java.util.Map;

public class ConversationDatabaseImpl extends ConversationDatabase {
    private static Map<String, String> conversations = new HashMap<>();

    @Override
    public void saveConversation(String conversationId, String message) {
        // implementation of saving a conversation
    }

    @Override
    public String getConversation(String conversationId) {
        return conversations.get(conversationId);
    }

    public void loadConversations() {
        conversations = new HashMap<>();
        // load conversations from file or database
    }
}