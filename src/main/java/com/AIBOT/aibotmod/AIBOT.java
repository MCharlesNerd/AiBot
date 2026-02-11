package com.AIBOT.aibotmod;

import net.minecraft.client.Minecraft;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AIBOT.MODID)
public class AIBOT {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "aibotmod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    private static Minecraft minecraft;
    private ConversationDatabase conversationDatabase;
    private AIBOT db;

    public AIBOT(){
        conversationDatabase = new ConversationDatabase();
    }

    private void init(FMLCommonSetupEvent event) throws SQLException {
        conversationDatabase = new ConversationDatabaseImpl();
        conversationDatabase.init();
        db = new AIBOT();
        db.connect();
    }

    public String getConversation(String id) throws SQLException {
        return db.getConversation(id);
    }

    public void saveConversation(String id, String message) throws SQLException {
        db.saveConversation(id, message);
    }

    public void connect() throws SQLException {
        String url = "jdbc:sqlite:conversations.db";
        Connection connection = DriverManager.getConnection(url);
    }

    public void onTick(){

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
    }
}
