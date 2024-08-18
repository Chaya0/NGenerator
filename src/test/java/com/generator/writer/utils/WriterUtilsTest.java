package com.generator.writer.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.File;
import java.nio.file.Files;
import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.Application;
import com.generator.model.properties.SpringProperties;

public class WriterUtilsTest {
	Properties props;

	@BeforeEach
	public void setUp() {
		Properties props = new Properties();
		props.setProperty("baseDir", "testBaseDir");
		props.setProperty("projectPath", "testProjectPath");
		props.setProperty("packageName", "com.test.package");
	}

	@Test
	public void testFileExists() throws Exception {
		File tempDir = new File("testFileExists");
		tempDir.mkdir();
		tempDir.deleteOnExit();

		File tempFile = new File(tempDir, "testFile.txt");
		Files.createFile(tempFile.toPath());

		assertTrue(WriterUtils.fileExists(tempDir.getAbsolutePath(), "testFile.txt"));
		assertFalse(WriterUtils.fileExists(tempDir.getAbsolutePath(), "nonexistentFile.txt"));
	}

	@Test
	public void testGetOutputResource() throws Exception {
		File tempDir = new File("testGetOutputResource");
		tempDir.mkdir();
		tempDir.deleteOnExit();

		GeneratorOutputFile file = WriterUtils.getOutputResource(tempDir.getAbsolutePath(), "testFile.txt", "UTF-8", true);
		assertNotNull(file);
		assertEquals(tempDir.getAbsolutePath() + "/testFile.txt", file);
	}

	@Test
	public void testGetCorrectPath() {
		assertEquals("com/example/project", WriterUtils.getCorrectPath("com.example.project"));
		assertEquals("my/app", WriterUtils.getCorrectPath("my.app"));
	}

	@Test
	public void testGetMainPackagePath() {
		// Mock the Application class and SpringProperties
		// For the purpose of this test, assume the getters return fixed strings
		Application.setSpringProperties(new SpringProperties(props));

		String expectedPath = "projectPath/baseDir/src/main/java/package/name";
		assertEquals(expectedPath, WriterUtils.getMainPackagePath());
	}

	@Test
	public void testGetControllerPackagePath() {
		// Mock the Application class and SpringProperties
		Application.setSpringProperties(new SpringProperties(props));

		String genericPath = "projectPath/baseDir/src/main/java/package/name/controller/generic";
		String specificPath = "projectPath/baseDir/src/main/java/package/name/controller";

		assertEquals(genericPath, WriterUtils.getControllerPackagePath(true));
		assertEquals(specificPath, WriterUtils.getControllerPackagePath(false));
	}

	@Test
	public void testGetImportControllerPackageName() {
		// Mock the Application class and SpringProperties
		Application.setSpringProperties(new SpringProperties(props));

		String genericImport = "packageName.controller.generic";
		String specificImport = "packageName.controller";

		assertEquals(genericImport, WriterUtils.getImportControllerPackageName(true));
		assertEquals(specificImport, WriterUtils.getImportControllerPackageName(false));
	}

	@Test
	public void testGetApplicationExceptionName() {
		// Mock the Application class and SpringProperties
		Application.setSpringProperties(new SpringProperties(props));

		assertEquals("ApplicationNameException", WriterUtils.getApplicationExceptionName());
	}

	@Test
	public void testGetFrontendRootPackagePath() {
		// Mock the Application class and SpringProperties
		Application.setSpringProperties(new SpringProperties(props));

		String expectedPath = "projectPath/baseDir-frontend/src/app/";
		assertEquals(expectedPath, WriterUtils.getFrontendRootPackagePath());
	}
}