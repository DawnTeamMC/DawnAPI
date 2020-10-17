package com.hugman.dawn;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.ModData;
import com.hugman.dawn.api.creator.pack.PackManager;
import com.hugman.dawn.api.util.debug.EntryDebugWriter;
import com.hugman.dawn.mod.init.DawnCommands;
import com.hugman.dawn.mod.init.DawnEffectPack;
import com.hugman.dawn.mod.init.DawnEnchantmentPack;
import com.hugman.dawn.mod.init.DawnItemGroups;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dawn implements ModInitializer {
	public static final ModData MOD_DATA = new ModData("dawn");
	public static final Logger LOGGER = LogManager.getLogger();
	public static final EntryDebugWriter DEBUG_WRITER = new EntryDebugWriter();

	@Override
	public void onInitialize() {
		new DawnEffectPack();
		new DawnEnchantmentPack();
		new DawnItemGroups();
		DawnCommands.init();
		ServerLifecycleEvents.SERVER_STARTED.register(this::onServerLoad);
	}

	public void onServerLoad(MinecraftServer minecraftServer) {
		for(Creator<?> creator : PackManager.CREATORS) {
			creator.serverRegister(minecraftServer.isDedicated());
		}
		if(minecraftServer.isDedicated()) {
			DEBUG_WRITER.load();
		}
	}
}
