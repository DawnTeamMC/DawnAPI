package com.hugman.dawn.mod.util.debug;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.hugman.dawn.Dawn;
import net.minecraft.util.Identifier;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.function.Supplier;

public class DataSerialization {
	public static final Gson RAW_GSON = new GsonBuilder().registerTypeAdapter(Identifier.class, new Identifier.Serializer()).excludeFieldsWithoutExposeAnnotation().create();
	public static final Gson PRETTY_GSON = new GsonBuilder().registerTypeAdapter(Identifier.class, new Identifier.Serializer()).setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();

	public static <T> T loadFromFile(File f, Class<? extends T> clazz, Supplier<T> baseCase) {
		return loadFromFile(PRETTY_GSON, f, clazz, baseCase);
	}

	public static <T> T loadFromFile(Gson gson, File f, Class<? extends T> clazz, Supplier<T> baseCase) {
		try {
			if(!f.exists()) {
				T t = baseCase.get();
				saveToFile(gson, f, clazz, t);
				return t;
			}
			FileInputStream in = new FileInputStream(f);
			return gson.fromJson(new InputStreamReader(in, StandardCharsets.UTF_8), clazz);
		}
		catch(IOException e) {
			Dawn.LOGGER.error("Failed to load file", e);
			return null;
		}
	}

	public static <T> void saveToFile(File f, Class<? extends T> clazz, T obj) {
		saveToFile(PRETTY_GSON, f, clazz, obj);
	}

	public static <T> void saveToFile(Gson gson, File f, Class<? extends T> clazz, T obj) {
		String json = gson.toJson(obj, clazz);
		try {
			f.createNewFile();
			try(BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
				writer.write(json);
			}
		}
		catch(IOException e) {
			Dawn.LOGGER.error("Failed to save file", e);
		}
	}
}
