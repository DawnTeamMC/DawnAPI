package com.hugman.dawn.api.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtil {
	public static final Path getParentPath(String s) {
		String[] splitFilePath = StringUtil.cutAtLast(s, "/");
		return Paths.get(splitFilePath[0]);
	}

	public static final void createDirectories(Path path) {
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
