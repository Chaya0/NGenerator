package com.generator.writer.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;

import org.junit.jupiter.api.Test;

public class GFolderTest {

	@Test
	public void testCreateFolder() {
		File tempDir = new File("testCreateFolder");
		tempDir.deleteOnExit();

		GFolder gFolder = new GFolder(tempDir.getAbsolutePath());
		assertTrue(gFolder.create());
		assertTrue(tempDir.exists());
	}

	@Test
	public void testCreateExistingFolder() {
		File tempDir = new File("testCreateExistingFolder");
		tempDir.mkdir();
		tempDir.deleteOnExit();

		GFolder gFolder = new GFolder(tempDir.getAbsolutePath());
		assertTrue(gFolder.create());
		assertTrue(tempDir.exists());
	}

	@Test
	public void testGetSubfolders() throws IOException {
		File tempDir = new File("testGetSubfolders");
		tempDir.mkdir();
		tempDir.deleteOnExit();

		File subDir1 = new File(tempDir, "subDir1");
		File subDir2 = new File(tempDir, "subDir2");
		subDir1.mkdir();
		subDir2.mkdir();

		GFolder gFolder = new GFolder(tempDir.getAbsolutePath());
		List<String> subfolders = gFolder.getSubfolders();

		assertTrue(subfolders.contains("subDir1"));
		assertTrue(subfolders.contains("subDir2"));
	}

	@Test
	public void testGetFiles() throws IOException {
		File tempDir = new File("testGetFiles");
		tempDir.mkdir();
		tempDir.deleteOnExit();

		File file1 = new File(tempDir, "file1.txt");
		File file2 = new File(tempDir, "file2.java");
		Files.createFile(file1.toPath());
		Files.createFile(file2.toPath());

		GFolder gFolder = new GFolder(tempDir.getAbsolutePath());
		String[] txtFiles = gFolder.getFiles("txt");
		String[] javaFiles = gFolder.getFiles("java");

		assertEquals(1, txtFiles.length);
		assertEquals("file1.txt", txtFiles[0]);
		assertEquals(1, javaFiles.length);
		assertEquals("file2.java", javaFiles[0]);
	}
}