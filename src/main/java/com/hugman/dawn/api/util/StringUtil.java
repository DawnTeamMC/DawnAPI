package com.hugman.dawn.api.util;

public final class StringUtil {
	public static String[] cutAtLast(String s, String separatorChar) {
		int i = s.lastIndexOf(separatorChar);
		return new String[]{s.substring(0, i), s.substring(i)};
	}

	public static String parseSuffix(String suffix, String name) {
		if(!suffix.isEmpty()) {
			suffix = "_" + suffix;
		}
		if(name.endsWith("_")) {
			name = name.substring(0, name.length() - 1);
		}
		boolean b = name.endsWith("bricks") || name.endsWith("tiles");
		if(!suffix.isEmpty() && b) {
			return name.substring(0, name.length() - 1) + suffix;
		}
		else {
			return name + suffix;
		}
	}
}
