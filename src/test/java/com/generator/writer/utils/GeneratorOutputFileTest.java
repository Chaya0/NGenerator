package com.generator.writer.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class GeneratorOutputFileTest {

	@Test
	public void testFileCreationAndWriting() throws IOException {
		File tempFile = File.createTempFile("testFile", ".txt");
		tempFile.deleteOnExit();

		try (GeneratorOutputFile outputFile = new GeneratorOutputFile(tempFile.getAbsolutePath(), "UTF-8", true)) {
			assertTrue(outputFile.hasAlreadyExisted());
			outputFile.writeln(1, "Hello World");
			assertTrue(outputFile.writePermitted());
		}

		try (GeneratorOutputFile outputFile = new GeneratorOutputFile(tempFile.getAbsolutePath(), "UTF-8", false)) {
			assertTrue(outputFile.hasAlreadyExisted());
		}
	}

	@Test
	public void testFileNotOverwritten() throws IOException {
		File tempFile = File.createTempFile("testFile", ".txt");
		tempFile.deleteOnExit();

		try (GeneratorOutputFile outputFile = new GeneratorOutputFile(tempFile.getAbsolutePath(), "UTF-8", false)) {
			assertTrue(outputFile.hasAlreadyExisted());
		}

		try (GeneratorOutputFile outputFile = new GeneratorOutputFile(tempFile.getAbsolutePath(), "UTF-8", true)) {
			assertTrue(outputFile.hasAlreadyExisted());
		}
	}

	@Test
	public void testWriteMethods() throws IOException {
		File tempFile = File.createTempFile("testFile", ".txt");
		tempFile.deleteOnExit();

		try (GeneratorOutputFile outputFile = new GeneratorOutputFile(tempFile.getAbsolutePath(), "UTF-8")) {
			outputFile.writeln(1, "Hello");
			outputFile.write(2, "World");
		}

		// Read the content of the file to verify
		String content = new String(java.nio.file.Files.readAllBytes(tempFile.toPath()));
		assertTrue(content.contains("Hello"));
		assertTrue(content.contains("World"));
	}

	@Test
	public void testClose() throws IOException {
		File tempFile = File.createTempFile("testFile", ".txt");
		tempFile.deleteOnExit();

		try (GeneratorOutputFile outputFile = new GeneratorOutputFile(tempFile.getAbsolutePath(), "UTF-8")) {
			outputFile.writeln(1, "Hello");
			outputFile.close();
			assertTrue(tempFile.exists());
		}
	}

	@Test
	public void testToString() throws IOException {
		File tempFile = File.createTempFile("testFile", ".txt");
		tempFile.deleteOnExit();

		try (GeneratorOutputFile outputFile = new GeneratorOutputFile(tempFile.getAbsolutePath(), "UTF-8")) {
			assertEquals(tempFile.getAbsolutePath(), outputFile.toString());
		}
	}
}