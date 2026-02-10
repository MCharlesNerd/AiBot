package com.AIBOT.aibotmod;

import java.util.HashMap;
import java.util.Map;

public class ConversationDatabase {
    private static Map<String, String> conversations = new HashMap<>();

    public void saveConversation(String conversationId, String message) {
        conversations.put(conversationId, message);
    }

    public String getConversation(String conversationId) {
        return conversations.get(conversationId);
    }

    public void loadConversations() {
    }
}
