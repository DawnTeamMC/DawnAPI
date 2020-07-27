package com.hugman.dawn.util;

import com.hugman.dawn.util.creator.block.BlockGetter;

public class StringUtil {
	public static String[] cutAtLast(String s, String character) {
		int i = s.lastIndexOf(character);
		return new String[]{s.substring(0, i), s.substring(i)};
	}

	public static String numerate(int number, String text) {
		if(number == 0) {
			return "no " + text;
		}
		else if(number == 1) {
			return number + " " + text;
		}
		else {
			if(text.endsWith("y")) {
				return number + " " + text.substring(0, text.length() - 1) + "ies";
			}
			else {
				return number + " " + text + "s";
			}
		}
	}

	public static String fixShapePrefix(String name, BlockGetter getter) {
		if(getter != BlockGetter.CUBE && name.endsWith("bricks")) {
			return name.substring(0, name.length() - 1);
		}
		else {
			return name;
		}
	}
}
