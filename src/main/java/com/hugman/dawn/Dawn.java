package com.hugman.dawn;

import com.hugman.dawn.api.object.ModData;
import com.hugman.dawn.config.DawnConfig;
import com.hugman.dawn.mod.init.DawnCommands;
import com.hugman.dawn.mod.init.DawnEntities;
import com.hugman.dawn.mod.init.DawnItemGroups;
import com.hugman.dawn.mod.util.debug.EntryDebugWriter;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.server.MinecraftServer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Dawn implements ModInitializer {
	public static final ModData MOD_DATA = new ModData("dawn");
	public static final Logger LOGGER = LogManager.getLogger();
	public static final DawnConfig CONFIG = AutoConfig.register(DawnConfig.class, PartitioningSerializer.wrap(GsonConfigSerializer::new)).getConfig();
	public static final EntryDebugWriter DEBUG_WRITER = new EntryDebugWriter();
	public static final List<ModData> MOD_DATA_LIST = new ArrayList<>();

	@Override
	public void onInitialize() {
		DawnItemGroups.init();
		DawnCommands.init();
		DawnEntities.init();
		MOD_DATA.registerCreators();
		ServerLifecycleEvents.SERVER_STARTED.register(this::onServerLoad);
	}

	public void onServerLoad(MinecraftServer minecraftServer) {
		MOD_DATA_LIST.forEach(modData -> modData.registerCreatorsServer(minecraftServer.isDedicated()));
		if(minecraftServer.isDedicated()) DEBUG_WRITER.load();
	}
}
