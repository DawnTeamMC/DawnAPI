package com.hugman.dawn.api.util.debug;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.util.debug.data.BlockEntryData;
import com.hugman.dawn.api.util.debug.data.ItemEntryData;
import com.hugman.dawn.api.util.debug.data.SimpleEntryData;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

public class EntryDebugWriter {
	public final void load() {
		if(Dawn.CONFIG.debug.writeIds) {
			for(Registry<?> registry : Registry.REGISTRIES) {
				Map<String, LinkedHashSet<Identifier>> entryDataList = new HashMap<>();
				for(Identifier entryID : registry.getIds()) {
					LinkedHashSet<Identifier> set = entryDataList.get(entryID.getNamespace());
					if(set != null) {
						set.add(entryID);
					}
					else {
						LinkedHashSet<Identifier> newSet = new LinkedHashSet<>();
						newSet.add(entryID);
						entryDataList.put(entryID.getNamespace(), newSet);
					}
				}
				if(Dawn.CONFIG.debug.expandedInfo) {
					if(registry.getKey().getValue().equals(Registry.BLOCK.getKey().getValue())) {
						entryDataList.forEach((namespace, identifiers) -> new BlockEntryData(namespace, identifiers).save());
					}
					else if(registry.getKey().getValue().equals(Registry.ITEM.getKey().getValue())) {
						entryDataList.forEach((namespace, identifiers) -> new ItemEntryData(namespace, identifiers).save());
					}
					else {
						entryDataList.forEach((namespace, identifiers) -> new SimpleEntryData(namespace, registry, identifiers).save());
					}
				}
				else {
					entryDataList.forEach((namespace, identifiers) -> new SimpleEntryData(namespace, registry, identifiers).save());
				}
			}
			Dawn.LOGGER.info("Created debug entry files");
		}
	}
}
