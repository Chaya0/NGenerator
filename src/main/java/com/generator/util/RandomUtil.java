package com.generator.util;

import java.security.SecureRandom;

public class RandomUtil {
	// Define the characters to be used for generating the random string
	private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*()_+{}[]:;',./<>?";

	private static final SecureRandom RANDOM = new SecureRandom();

	/**
	 * Generates a random string with the given length, containing alphanumeric
	 * characters and symbols.
	 * 
	 * @param length The number of characters in the generated string.
	 * @return A random string of the specified length.
	 */
	public static String generateRandomString(int length) {
		StringBuilder sb = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int index = RANDOM.nextInt(CHARACTERS.length());
			sb.append(CHARACTERS.charAt(index));
		}

		return sb.toString();
	}
}
