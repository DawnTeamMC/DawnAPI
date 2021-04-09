package com.hugman.dawn.api.util;

import net.minecraft.util.Identifier;

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

	public static Identifier addToPath(String s, Identifier identifier) {
		return new Identifier(identifier.getNamespace(), s + identifier.getPath());
	}

	public static Identifier addToPath(Identifier identifier, String s) {
		return new Identifier(identifier.getNamespace(), identifier.getPath() + s);
	}

	public static Identifier addToPath(String s1, Identifier identifier, String s2) {
		return new Identifier(identifier.getNamespace(), s1 + identifier.getPath() + s2);
	}
}
