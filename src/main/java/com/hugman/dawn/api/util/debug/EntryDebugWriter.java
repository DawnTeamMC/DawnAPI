package com.hugman.dawn.api.util.debug;

import com.google.gson.annotations.Expose;
import com.hugman.dawn.Dawn;
import com.hugman.dawn.api.util.DataSerialization;
import com.hugman.dawn.api.util.FileUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntryDebugWriter {
	protected Map<Identifier, EntryData> map = new HashMap<>();
	protected final File entriesPath = new File("debug/entries");

	public final void load() {
		for(Registry<?> registry : Registry.REGISTRIES) {
			for(Identifier entryID : registry.getIds()) {
				EntryData data;
				Identifier registryID = new Identifier(entryID.getNamespace(), registry.getKey().getValue().getPath());
				if(map.containsKey(registryID)) {
					data = map.get(registryID);
				}
				else {
					data = new EntryData(registryID);
					map.put(registryID, data);
				}
				data.add(entryID);
			}
		}
		Dawn.LOGGER.info("Created debug entry files");
	}

	private static final File getFile(String filePath) {
		FileUtil.createDirectories(FileUtil.getParentPath(filePath));
		return new File(filePath);
	}

	public final class EntryData {
		private final File file;
		@Expose
		protected int count;
		@Expose
		protected List<String> values = new ArrayList<>();

		protected EntryData(Identifier registryID) {
			String path = entriesPath.getPath() + "/" + registryID.getNamespace() + "/" + registryID.getPath() + ".json";
			this.file = getFile(path);

		}

		public void add(Identifier identifier) {
			values.add(identifier.toString());
			count++;
			this.save();
		}

		public void save() {
			DataSerialization.saveToFile(file, EntryData.class, this);
		}

		public void delete() {
			file.delete();
		}
	}
}
