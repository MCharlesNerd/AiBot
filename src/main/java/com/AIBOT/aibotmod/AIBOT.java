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

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(AIBOT.MODID)
public class AIBOT {
    // Define mod id in a common place for everything to reference
    public static final String MODID = "aibotmod";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    private static Minecraft minecraft;
    private ConversationDatabase conversationDatabase;

    public AIBOT(){
        conversationDatabase = new ConversationDatabase();
    }

    private void init(FMLCommonSetupEvent event) {
        conversationDatabase.loadConversations();
    }

    public void onTick(){

    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
    }
}
