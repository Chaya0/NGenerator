package com.generator;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ErrorLogHandlerTest {

	private static final String LOG_FILE_PATH = "./ErrorLog.txt";

	@BeforeEach
	public void setUp() {
		File file = new File(LOG_FILE_PATH);
		if (file.exists()) {
			file.delete();
		}
	}

	@Test
	public void testWriteErrorLog() throws Exception {
		// Given
		Exception testException = new Exception("Test exception message");

		// When
		ErrorLogHandler.writeErrorLog(testException);

		// Then
		File file = new File(LOG_FILE_PATH);
		assertTrue(file.exists(), "Log file should be created.");

		StringBuilder content = new StringBuilder();
		try (Scanner scanner = new Scanner(file, StandardCharsets.UTF_8)) {
			while (scanner.hasNextLine()) {
				content.append(scanner.nextLine()).append(System.lineSeparator());
			}
		}

		String expectedContent = "Error: Test exception message" + System.lineSeparator() + "Stack Trace:" + System.lineSeparator() + "\t" + testException.getStackTrace()[0].toString()
				+ System.lineSeparator();

		assertTrue(content.toString().contains(expectedContent), "Log file content does not match.");
	}
}
