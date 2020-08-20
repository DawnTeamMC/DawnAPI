package com.hugman.dawn;

import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.ModData;
import com.hugman.dawn.api.creator.pack.PackManager;
import com.hugman.dawn.api.util.debug.EntryDebugWriter;
import com.hugman.dawn.init.DawnBlockPack;
import com.hugman.dawn.init.DawnItemGroups;
import com.hugman.dawn.init.data.DawnTags;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Dawn implements ModInitializer {
	public static final ModData MOD_DATA = new ModData("dawn");
	public static final Logger LOGGER = LogManager.getLogger();
	public static final EntryDebugWriter DEBUG_WRITER = new EntryDebugWriter();

	@Override
	public void onInitialize() {
		ServerLifecycleEvents.SERVER_STARTED.register(minecraftServer -> onServerLoad(minecraftServer));
		new DawnBlockPack();
		new DawnItemGroups();
		FuelRegistry.INSTANCE.add(DawnTags.ITEM_WOODEN_VERTICAL_SLABS, 150);
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
