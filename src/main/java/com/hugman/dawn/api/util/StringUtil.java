package com.hugman.dawn.api.util;

public class StringUtil {
	public static String[] cutAtLast(String s, String separatorChar) {
		int i = s.lastIndexOf(separatorChar);
		return new String[]{s.substring(0, i), s.substring(i)};
	}

	public static String getShapedName(String s, BlockTemplate template) {
		String suffix = template.getSuffix();
		if(!suffix.isEmpty()) {
			suffix = "_" + suffix;
		}
		if(s.endsWith("_")) {
			s = s.substring(0, s.length() - 1);
		}
		boolean b = s.endsWith("bricks") || s.endsWith("tiles");
		if(!template.getSuffix().isEmpty() && b) {
			return s.substring(0, s.length() - 1) + suffix;
		}
		else {
			return s + suffix;
		}
	}
}
