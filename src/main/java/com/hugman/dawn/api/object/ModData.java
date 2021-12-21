package com.hugman.dawn.api.object;

import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.creator.Creator;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	/**
	 * Tells the API to automatically replace all ID namespaces that matches an old mod name to the current one.
	 * <p>Example: <code>wild_explorer:XXX</code> -> <code>promenade:XXX</code></p>
	 * @param oldName the old name of the mod
	 */
	public void addOldName(String oldName) {
		oldModNames.add(oldName);
	}

	/**
	 * Tells the API to automatically replace all ID paths that matches an old name to a new one.
	 * <p>Example: <code>XXX:timeswap_table</code> -> <code>XXX:timewarp_table</code></p>
	 * @param oldName the old path of the ID
	 * @param newName the new path of the ID
	 */
	public void addOldId(String oldName, String newName) {
		oldObjectNames.put(oldName, newName);
	}

	/**
	 * Tells the API to automatically replace all IDs that matches an old ID to a new one.
	 * <p>This may be ONLY used when an item is moved from a mod to another. If you are updating your mod's name and some ID(s) at the same time, please use {@link #addOldId(String, String)} and {@link #addOldName(String)}</p>
	 * <p>Example: <code>culinaire:banana</code> -> <code>promenade:banana</code></p>
	 * @param oldName the old ID
	 * @param newName the new ID
	 */
	public void addOldId(Identifier oldName, String newName) {
		oldObjectIds.put(oldName, newName);
	}

	/**
	 * Please do not use this method.
	 */
	public List<String> getOldModNames() {
		return oldModNames;
	}

	/**
	 * Please do not use this method.
	 */
	public Map<String, String> getOldObjectNames() {
		return oldObjectNames;
	}

	/**
	 * Please do not use this method.
	 */
	public Map<Identifier, String> getOldObjectIds() {
		return oldObjectIds;
	}

	/**
	 * Please do not use this method.
	 */
	public void registerCreators() {
		creators.forEach(creator -> creator.register(this));
		creators.forEach(creator -> creator.postRegister(this));
		Dawn.MOD_DATA_LIST.add(this);
	}

	/**
	 * Please do not use this method.
	 */
	@Environment(EnvType.CLIENT)
	public void registerCreatorsClient() {
		this.creators.forEach(creator -> creator.clientRegister(this));
	}

	/**
	 * Please do not use this method.
	 */
	public void registerCreatorsServer(boolean isDedicated) {
		this.creators.forEach(creator -> creator.serverRegister(this, isDedicated));
	}

	/**
	 * Creates an ID using the mod's current name.
	 */
	public Identifier id(String s) {
		return new Identifier(this.modName, s);
	}
}
