package com.hugman.dawn.json;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.gson.annotations.Expose;
import com.hugman.dawn.Dawn;
import com.hugman.dawn.util.WordUtil;
import net.fabricmc.loader.api.metadata.ModMetadata;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DataWriter {
	public static final Map<Registry<?>, String> registryMap = new ImmutableMap.Builder<Registry<?>, String>()
			.put(Registry.BLOCK, "block")
			.put(Registry.ITEM, "item")
			.put(Registry.ENCHANTMENT, "enchantment")
			.put(Registry.ENTITY_TYPE, "entity_type")
			.put(Registry.PAINTING_MOTIVE, "painting_motive")
			.put(Registry.STATUS_EFFECT, "status_effect")
			.put(Registry.SOUND_EVENT, "sound_event")
			.put(Registry.FEATURE, "feature")
			.put(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, "configured_carver")
			.put(BuiltinRegistries.CONFIGURED_CARVER, "configured_carver")
			.put(BuiltinRegistries.CONFIGURED_FEATURE, "configured_feature")
			.put(BuiltinRegistries.BIOME, "biome")
			.put(Registry.SCREEN_HANDLER, "screen_handler")
			.put(Registry.STAT_TYPE, "stat_type")
			.build();

	public DataWriter(ModMetadata metadata) {
		String modName = metadata.getId();
		for(Registry<?> registry : Registry.REGISTRIES) {
			Set<Identifier> set = getRegistryEntries(modName, registry);
			if(set != null) {
				new EntriesData(modName, registry);
			}
		}
	}

	@Nullable
	private static Set<Identifier> getRegistryEntries(String modName, Registry<?> registry) {
		ImmutableSet.Builder builder = new ImmutableSet.Builder();
		for(Identifier identifier : registry.getIds()) {
			if(identifier.getNamespace() == modName) {
				builder.add(identifier);
			}
		}
		ImmutableSet set = builder.build();
		return set.isEmpty() ? null : set;
	}

	private static File createFile(String filePath) {
		String[] splitFilePath = WordUtil.cutAtLast(filePath, "/");
		Path path = Paths.get(splitFilePath[0]);
		if(!Files.exists(path)) {
			try {
				Dawn.LOGGER.info("Creating directory: " + splitFilePath[0]);
				Files.createDirectories(path);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		Dawn.LOGGER.info("Creating file: " + filePath);
		return new File(filePath);
	}

	public final class EntriesData {
		private final File file;
		@Expose
		protected int count;
		@Expose
		protected List<String> values = new ArrayList<>();

		protected EntriesData(String modName, Registry<?> registry) {
			String filePath = "debug/entries/" + modName + "/" + registry.getKey().getValue().getPath() + ".json";
			file = createFile(filePath);
			add(getRegistryEntries(modName, registry));
		}

		public void add(Set<Identifier> set) {
			set.forEach((identifier) -> add(identifier));
		}

		public void add(Identifier identifier) {
			values.add(identifier.toString());
			count++;
			DataSerialization.saveToFile(DataSerialization.PRETTY_GSON, file, EntriesData.class, this);
		}
	}
}
