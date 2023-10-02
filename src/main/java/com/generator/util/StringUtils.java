package com.generator.util;

public class StringUtils {
	public static String uppercaseFirst(String text) {
		if (text == null) return null;
		if (text.length() == 0) return text;

		String start = text.substring(0, 1);
		String end = text.substring(1);
		return start.toUpperCase() + end;
	}

	public static String lowercaseFirst(String text) {
		if (text == null) return null;
		if (text.length() == 0) return text;

		String start = text.substring(0, 1);
		String end = text.substring(1);
		return start.toLowerCase() + end;
	}

	public static String camelNotationToText(String text) {
		if (text == null) return null;

		String s = "";
		String uppercase = text.toUpperCase();
		for (int i = 0; i < text.length(); i++) {
			if (i > 0 && (text.charAt(i) == uppercase.charAt(i))) {
				s += " ";
			}
			s += text.charAt(i);
		}
		return s;
	}
}
