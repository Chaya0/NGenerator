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
	public static String camelToKebabCase(String input) {
        if (input == null || input.isEmpty()) {
            return input;
        }

        StringBuilder kebabCase = new StringBuilder();
        char[] charArray = input.toCharArray();

        for (int i = 0; i < charArray.length; i++) {
            char currentChar = charArray[i];
            if (Character.isUpperCase(currentChar)) {
                // Append a hyphen before uppercase letters, except at the start
                if (i != 0) {
                    kebabCase.append("-");
                }
                kebabCase.append(Character.toLowerCase(currentChar));
            } else {
                kebabCase.append(currentChar);
            }
        }

        return kebabCase.toString();
    }
}
