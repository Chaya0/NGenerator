package com.generator.writer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.Application;
import com.generator.model.properties.SpringProperties;
import com.generator.writer.utils.GeneratorOutputFile;

public class UtilsTest {

	private static final String TEST_FOLDER = "test_folder";
	private static final String TEST_FILE = "test_file.txt";

	@BeforeEach
	public void setUp() throws IOException {
		Files.createDirectories(Path.of(TEST_FOLDER));
	}

	@Test
	public void testFileExistsWhenFileExists() {
		File file = new File(TEST_FOLDER, TEST_FILE);
		try {
			assertTrue(file.createNewFile());
			assertTrue(Utils.fileExists(TEST_FOLDER, TEST_FILE));
		} catch (IOException e) {
			fail("Exception thrown while creating file");
		}
	}

	@Test
	public void testFileExistsWhenFileDoesNotExist() {
		assertFalse(Utils.fileExists(TEST_FOLDER, "non_existent_file.txt"));
	}

	@Test
	public void testGetOutputResourceWithCharset() throws Exception {
		GeneratorOutputFile outputFile = Utils.getOutputResource(TEST_FOLDER, TEST_FILE, "UTF-8", true);
		assertNotNull(outputFile);
		assertEquals(new File(TEST_FOLDER, TEST_FILE).getAbsolutePath(), outputFile);
	}

	@Test
	public void testGetOutputResourceWithoutCharset() throws Exception {
		GeneratorOutputFile outputFile = Utils.getOutputResource(TEST_FOLDER, TEST_FILE, true);
		assertNotNull(outputFile);
		assertEquals(new File(TEST_FOLDER, TEST_FILE).getAbsolutePath(), outputFile);
	}

	@Test
	public void testGetOutputResourceDefault() throws Exception {
		GeneratorOutputFile outputFile = Utils.getOutputResource(TEST_FOLDER, TEST_FILE);
		assertNotNull(outputFile);
		assertEquals(new File(TEST_FOLDER, TEST_FILE).getAbsolutePath(), outputFile);
	}

	@Test
	public void testGetCorrectPath() {
		assertEquals("com/example/project", Utils.getCorrectPath("com.example.project"));
	}

	@Test
	public void testGetMainPackagePath() {
		// Mock Application and SpringProperties
		Application app = mock(Application.class);
		SpringProperties props = mock(SpringProperties.class);
		when(props.getProjectPath()).thenReturn("/project/path");
		when(props.getBaseDir()).thenReturn("baseDir");
		when(props.getPackageName()).thenReturn("com.example");
		Application.setSpringProperties(props);

		String expected = "/project/path/baseDir/src/main/java/com/example";
		assertEquals(expected, Utils.getMainPackagePath());
	}

	@Test
	public void testGetControllerPackagePath() {
		String expected = Utils.getMainPackagePath() + "/controller";
		assertEquals(expected, Utils.getControllerPackagePath(false));

		expected = Utils.getMainPackagePath() + "/controller/generic";
		assertEquals(expected, Utils.getControllerPackagePath(true));
	}

	@Test
	public void testGetRepositoryPackagePath() {
		String expected = Utils.getMainPackagePath() + "/repository";
		assertEquals(expected, Utils.getRepositoryPackagePath(false));

		expected = Utils.getMainPackagePath() + "/repository/generic";
		assertEquals(expected, Utils.getRepositoryPackagePath(true));
	}

	@Test
	public void testGetServicePackagePath() {
		String expected = Utils.getMainPackagePath() + "/service";
		assertEquals(expected, Utils.getServicePackagePath(false));

		expected = Utils.getMainPackagePath() + "/service/generic";
		assertEquals(expected, Utils.getServicePackagePath(true));
	}

	@Test
	public void testGetModelPackagePath() {
		String expected = Utils.getMainPackagePath() + "/model";
		assertEquals(expected, Utils.getModelPackagePath());
	}

	@Test
	public void testGetResourcesPath() {
		// Mock Application and SpringProperties
		Application app = mock(Application.class);
		SpringProperties props = mock(SpringProperties.class);
		when(props.getProjectPath()).thenReturn("/project/path");
		when(props.getBaseDir()).thenReturn("baseDir");
		Application.setSpringProperties(props);

		String expected = "/project/path/baseDir/src/main/resources/";
		assertEquals(expected, Utils.getResourcesPath());
	}

	@Test
	public void testGetApplicationExceptionImport() {
		// Mock Application and SpringProperties
		Application app = mock(Application.class);
		SpringProperties props = mock(SpringProperties.class);
		when(props.getName()).thenReturn("app");
		Application.setSpringProperties(props);

		String expected = "import com.example.exceptions.AppException;";
		assertEquals(expected, Utils.getApplicationExceptionImport());
	}

	@Test
	public void testGetFrontendSourceDirectoryPath() {
		// Mock Application and SpringProperties
		Application app = mock(Application.class);
		SpringProperties props = mock(SpringProperties.class);
		when(props.getProjectPath()).thenReturn("/project/path");
		when(props.getBaseDir()).thenReturn("baseDir");
		Application.setSpringProperties(props);

		String expected = "/project/path/baseDir-frontend/src/";
		assertEquals(expected, Utils.getFrontendSourceDirectoryPath());
	}

	@Test
	public void testGetFrontendRootPackagePath() {
		// Mock Application and SpringProperties
		Application app = mock(Application.class);
		SpringProperties props = mock(SpringProperties.class);
		when(props.getProjectPath()).thenReturn("/project/path");
		when(props.getBaseDir()).thenReturn("baseDir");
		Application.setSpringProperties(props);

		String expected = "/project/path/baseDir-frontend/src/app/";
		assertEquals(expected, Utils.getFrontendRootPackagePath());
	}

	@Test
	public void testGetFrontendFeaturesEntitiesPath() {
		String expected = Utils.getFrontendRootPackagePath() + "features/entities/";
		assertEquals(expected, Utils.getFrontendFeaturesEntitiesPath());
	}

	@Test
	public void testGetFrontendPagesPath() {
		String expected = Utils.getFrontendRootPackagePath() + "pages/";
		assertEquals(expected, Utils.getFrontendPagesPath());
	}

	@Test
	public void testGetFrontendLocalizationPath() {
		String expected = Utils.getFrontendSourceDirectoryPath() + "assets/i18n/";
		assertEquals(expected, Utils.getFrontendLocalizationPath());
	}

	@AfterEach
	public void tearDown() throws IOException {
		Files.deleteIfExists(Path.of(TEST_FOLDER, TEST_FILE));
		Files.deleteIfExists(Path.of(TEST_FOLDER));
	}
}