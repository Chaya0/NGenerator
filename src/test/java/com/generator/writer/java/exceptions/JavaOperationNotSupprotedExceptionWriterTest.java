package com.generator.writer.java.exceptions;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.generator.Application;
import com.generator.model.properties.SpringProperties;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaOperationNotSupprotedExceptionWriterTest {

	@Mock
	private GeneratorOutputFile mockGeneratorOutputFile;

	@InjectMocks
	private JavaOperationNotSupprotedExceptionWriter javaOperationNotSupportedExceptionWriter;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		// Setup SpringProperties
		Properties properties = new Properties();
		properties.setProperty("name", "MyApp");
		properties.setProperty("packageName", "com.myApp");
		SpringProperties springProperties = new SpringProperties(properties);
		Application.setSpringProperties(springProperties);

		// Mock static methods from WriterUtils
		try {
			when(WriterUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(mockGeneratorOutputFile);
		} catch (Exception e) {
			fail("Failed to mock WriterUtils.getOutputResource method");
		}
	}

	@Test
    public void testCreate() throws Exception {
        // Given
        when(mockGeneratorOutputFile.hasAlreadyExisted()).thenReturn(false);
        doNothing().when(mockGeneratorOutputFile).writeln(anyInt(), anyString());

        // When
        javaOperationNotSupportedExceptionWriter.create();

        // Then
        verify(mockGeneratorOutputFile).writeln(0, "package com.myApp.exceptions;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "public class OperationNotSupportedException extends MyAppException {");
        verify(mockGeneratorOutputFile).writeln(1, "private static final long serialVersionUID = 1L;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(1, "public OperationNotSupportedException(String message) {");
        verify(mockGeneratorOutputFile).writeln(2, "super(message);");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "}");

        verify(mockGeneratorOutputFile).close(); // Ensure the file is closed
    }
}