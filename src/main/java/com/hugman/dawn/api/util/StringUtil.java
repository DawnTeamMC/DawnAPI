package com.hugman.dawn.api.util;

import com.hugman.dawn.api.creator.BlockGetter;

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

	public static String fixShapePrefix(String s, BlockGetter getter) {
		if(getter != BlockGetter.CUBE && s.endsWith("bricks")) {
			return s.substring(0, s.length() - 1);
		}
		else {
			return s;
		}
	}
}
