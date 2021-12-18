package com.hugman.dawn.api.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public final class FileUtil {
	public static Path getParentPath(String s) {
		String[] splitFilePath = StringUtil.cutAtLast(s, "/");
		return Paths.get(splitFilePath[0]);
	}

	public static void createDirectories(Path path) {
		if(!Files.exists(path)) {
			try {
				Files.createDirectories(path);
			}
			catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}
