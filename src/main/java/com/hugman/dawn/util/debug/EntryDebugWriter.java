package com.hugman.dawn.util.debug;

import com.google.gson.annotations.Expose;
import com.hugman.dawn.Dawn;
import com.hugman.dawn.util.DataSerialization;
import com.hugman.dawn.util.StringUtil;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntryDebugWriter {
	public static final void init() {
		Map<Identifier, EntryData> map = new HashMap<>();
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
		String[] splitFilePath = StringUtil.cutAtLast(filePath, "/");
		Path path = Paths.get(splitFilePath[0]);
		if(!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
		return new File(filePath);
	}

	public static final class EntryData {
		private final File file;
		private final Identifier registryID;
		@Expose
		protected int count;
		@Expose
		protected List<String> values = new ArrayList<>();

		protected EntryData(Identifier registryID) {
			String path = "debug/entries/" + registryID.getNamespace() + "/" + registryID.getPath() + ".json";
			this.file = getFile(path);
			this.registryID = registryID;

		}

		public void add(Identifier identifier) {
			values.add(identifier.toString());
			count++;
			this.save();
		}

		public void save() {
			DataSerialization.saveToFile(file, EntryData.class, this);
		}
	}
}
