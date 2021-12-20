package com.hugman.dawn.api.object;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.creator.Creator;
import com.hugman.dawn.api.creator.bundle.Bundle;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public class ModData {
	private final String modName;
	private final List<String> oldModNames = new ArrayList<>();
	private final Map<String, String> oldObjectNames = new HashMap<>();
	private final Map<Identifier, String> oldObjectIds = new HashMap<>();
	private final List<Creator> creators = new ArrayList<>();

	public ModData(String modName) {
		this.modName = modName;
	}

	public String getModName() {
		return modName;
	}

	public void addCreator(Creator creator) {
		creators.add(creator);
	}

	public void addOldName(String string) {
		oldModNames.add(string);
	}

	public void addOldId(String oldName, String newName) {
		oldObjectNames.put(oldName, newName);
	}

	public void addOldId(Identifier oldName, String newName) {
		oldObjectIds.put(oldName, newName);
	}

	public List<String> getOldModNames() {
		return oldModNames;
	}

	public Map<String, String> getOldObjectNames() {
		return oldObjectNames;
	}

	public Map<Identifier, String> getOldObjectIds() {
		return oldObjectIds;
	}

	public void registerCreators() {
		creators.forEach(creator -> creator.register(this));
		creators.forEach(creator -> creator.postRegister(this));
		Dawn.MOD_DATA_LIST.add(this);
	}

	@Environment(EnvType.CLIENT)
	public void registerCreatorsClient() {
		this.creators.forEach(creator -> creator.clientRegister(this));
	}

	public void registerCreatorsServer(boolean isDedicated) {
		this.creators.forEach(creator -> creator.serverRegister(this, isDedicated));
	}

	public Identifier id(String s) {
		return new Identifier(this.modName, s);
	}
}
