package com.generator.writer.utils;

import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Arrays;
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
        
        // Ensure subdirectories are deleted after the test
        subDir1.deleteOnExit();
        subDir2.deleteOnExit();

        GFolder gFolder = new GFolder(tempDir.getAbsolutePath());
        List<String> subfolders = gFolder.getSubfolders();

        assertTrue(subfolders.contains("subDir1"));
        assertTrue(subfolders.contains("subDir2"));
    }

	@Test
	public void testGetFiles() throws IOException {
	    File tempDir = new File("testGetFiles");
	    if (!tempDir.exists()) {
	        tempDir.mkdir();
	    }
	    tempDir.deleteOnExit();

	    File file1 = Files.createTempFile(tempDir.toPath(), "file1", ".txt").toFile();
	    File file2 = Files.createTempFile(tempDir.toPath(), "file2", ".java").toFile();
	    
	    file1.deleteOnExit();
	    file2.deleteOnExit();

	    GFolder gFolder = new GFolder(tempDir.getAbsolutePath());
	    
	    // Debug: List all files in the directory
	    for (File file : tempDir.listFiles()) {
	        System.out.println("File in directory: " + file.getName());
	    }
	    
	    String[] txtFiles = gFolder.getFiles("txt");
	    String[] javaFiles = gFolder.getFiles("java");

	    System.out.println("Found txt files: " + Arrays.toString(txtFiles));
	    System.out.println("Found java files: " + Arrays.toString(javaFiles));

	    assertEquals("file1.txt", txtFiles[0]);
	    assertEquals("file2.java", javaFiles[0]);
	}
}