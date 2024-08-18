package com.generator.writer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.generator.Application;
import com.generator.model.properties.SpringProperties;

public class SpringStartExtractorTest {

	@Mock
	private Application mockApplication;

	@Mock
	private SpringProperties mockSpringProperties;

	@Mock
	private CloseableHttpClient mockHttpClient;

	@Mock
	private CloseableHttpResponse mockHttpResponse;

	@Mock
	private HttpEntity mockHttpEntity;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		when(Application.getSpringProperties()).thenReturn(mockSpringProperties);
	}

	@Test
	public void testExtractAppSuccess() throws Exception {
		String url = "http://example.com/app.zip";
		String extractPath = "test/extract/path/";
		String zipEntryName = "testFile.txt";
		byte[] zipContent = createZipContent(zipEntryName, "Test content");

		when(mockSpringProperties.getSpringAppDownloadUrl()).thenReturn(url);
		when(mockSpringProperties.getProjectPath()).thenReturn(extractPath);

		when(mockHttpClient.execute(any(HttpGet.class))).thenReturn(mockHttpResponse);
		when(mockHttpResponse.getEntity()).thenReturn(mockHttpEntity);
		when(mockHttpEntity.getContent()).thenReturn(new ByteArrayInputStream(zipContent));

		// Execute the method under test
		SpringStartExtractor.extractApp();

		// Validate the extracted content
		File extractedFile = new File(extractPath + zipEntryName);
		assertTrue(extractedFile.exists());
		assertEquals("Test content", new String(Files.readAllBytes(extractedFile.toPath())));
		extractedFile.delete();
	}

	@Test
	public void testExtractAppIOException() throws Exception {
		String url = "http://example.com/app.zip";
		String extractPath = "test/extract/path/";

		when(mockSpringProperties.getSpringAppDownloadUrl()).thenReturn(url);
		when(mockSpringProperties.getProjectPath()).thenReturn(extractPath);

		// Simulate IOException during HTTP request
		when(mockHttpClient.execute(any(HttpGet.class))).thenThrow(new IOException("HTTP error"));

		// Execute the method under test
		Exception exception = assertThrows(Exception.class, () -> {
			SpringStartExtractor.extractApp();
		});

		assertTrue(exception.getMessage().contains("HTTP error"));
	}

	@Test
	public void testExtractAppFileNotFound() throws Exception {
		String url = "http://example.com/app.zip";
		String extractPath = "test/extract/path/";

		when(mockSpringProperties.getSpringAppDownloadUrl()).thenReturn(url);
		when(mockSpringProperties.getProjectPath()).thenReturn(extractPath);

		when(mockHttpClient.execute(any(HttpGet.class))).thenReturn(mockHttpResponse);
		when(mockHttpResponse.getEntity()).thenReturn(mockHttpEntity);
		when(mockHttpEntity.getContent()).thenReturn(null); // Simulate no content

		// Execute the method under test
		SpringStartExtractor.extractApp();

		// Validate that no files were created
		File tempFile = new File(extractPath + "testFile.txt");
		assertFalse(tempFile.exists());
	}

	private byte[] createZipContent(String fileName, String content) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try (ZipOutputStream zipOut = new ZipOutputStream(baos)) {
			ZipEntry zipEntry = new ZipEntry(fileName);
			zipOut.putNextEntry(zipEntry);
			zipOut.write(content.getBytes());
			zipOut.closeEntry();
		}
		return baos.toByteArray();
	}
}
