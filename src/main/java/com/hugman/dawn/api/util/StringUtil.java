package com.hugman.dawn.api.util;

public final class StringUtil {
	public static String[] cutAtLast(String s, String separatorChar) {
		int i = s.lastIndexOf(separatorChar);
		return new String[]{s.substring(0, i), s.substring(i)};
	}

	public static String parsePluralBlockName(String name) {
		if(name.endsWith("bricks") || name.endsWith("tiles")) return name.substring(0, name.length() - 1);
		else return name;
	}

}
