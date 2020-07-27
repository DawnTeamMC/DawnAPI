package com.hugman.dawn.util;

public class StringUtil {
	public static String[] cutAtLast(String s, String character) {
		int i = s.lastIndexOf(character);
		return new String[]{s.substring(0, i), s.substring(i)};
	}
}
