package com.hugman.dawn.mod.util.debug.data;

import com.hugman.dawn.api.util.FileUtil;
import com.hugman.dawn.mod.util.debug.DataSerialization;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.io.File;

public abstract class EntryData {
	protected File file;

	protected EntryData(String namespace, Registry<?> registry) {
		Identifier registryID = registry.getKey().getValue();
		this.file = createFile(namespace, registryID.getNamespace(), registryID.getPath());
	}

	private static File createFile(String namespace, String registryNamespace, String registryPath) {
		String filePath = "debug/registry_entries/" + namespace + "/" + registryNamespace + "/" + registryPath + ".json";
		FileUtil.createDirectories(FileUtil.getParentPath(filePath));
		return new File(filePath);
	}

	public File getFile() {
		return file;
	}

	public void save() {
		DataSerialization.saveToFile(file, this.getClass(), this);
	}
}
