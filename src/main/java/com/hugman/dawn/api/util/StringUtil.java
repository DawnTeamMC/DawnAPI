package com.hugman.dawn.api.util;

public class StringUtil {
	public static String[] cutAtLast(String s, String separatorChar) {
		int i = s.lastIndexOf(separatorChar);
		return new String[]{s.substring(0, i), s.substring(i)};
	}

	public static String numerate(int i, String s) {
		if(i == 0) {
			return "no " + s;
		}
		else if(i == 1) {
			return i + " " + s;
		}
		else {
			if(s.endsWith("y")) {
				return i + " " + s.substring(0, s.length() - 1) + "ies";
			}
			else {
				return i + " " + s + "s";
			}
		}
	}

	public static String getShapedName(String s, BlockGetter getter) {
		String suffix = getter.getSuffix();
		if(!suffix.isEmpty()) {
			suffix = "_" + suffix;
		}
		if(s.endsWith("_")) {
			s = s.substring(0, s.length() - 1);
		}
		if(!getter.getSuffix().isEmpty() && s.endsWith("bricks") && s.endsWith("tiles")) {
			return s.substring(0, s.length() - 1) + suffix;
		}
		else {
			return s + suffix;
		}
	}
}
