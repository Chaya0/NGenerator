package com.generator.writer.java.dto;

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

public class JavaGenericResponseWriterTest {

	@Mock
	private GeneratorOutputFile mockGeneratorOutputFile;

	@InjectMocks
	private JavaGenericResponseWriter javaGenericResponseWriter;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		// Setup SpringProperties
		Properties properties = new Properties();
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
        javaGenericResponseWriter.create();

        // Then
        verify(mockGeneratorOutputFile).writeln(0, "package com.myApp.dto;");
        verify(mockGeneratorOutputFile).writeln(0, "import lombok.Data;");
        verify(mockGeneratorOutputFile).writeln(0, "import lombok.NoArgsConstructor;");
        verify(mockGeneratorOutputFile).writeln(0, "import lombok.AllArgsConstructor;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "@Data");
        verify(mockGeneratorOutputFile).writeln(0, "@NoArgsConstructor");
        verify(mockGeneratorOutputFile).writeln(0, "@AllArgsConstructor");
        verify(mockGeneratorOutputFile).writeln(0, "public class GenericResponse {");
        verify(mockGeneratorOutputFile).writeln(1, "private String code;");
        verify(mockGeneratorOutputFile).writeln(1, "private String message;");
        verify(mockGeneratorOutputFile).writeln(1, "private Object data;");
        verify(mockGeneratorOutputFile).writeln(0, "");

        // Verify static methods
        verify(mockGeneratorOutputFile).writeln(1, "public static GenericResponse getSuccessfulResponse(Object data) {");
        verify(mockGeneratorOutputFile).writeln(2, "GenericResponse response = new GenericResponse();");
        verify(mockGeneratorOutputFile).writeln(2, "response.code = \"200\";");
        verify(mockGeneratorOutputFile).writeln(2, "response.message = \"Success\";");
        verify(mockGeneratorOutputFile).writeln(2, "response.data = data;");
        verify(mockGeneratorOutputFile).writeln(2, "return response;");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");

        verify(mockGeneratorOutputFile).writeln(1, "public static GenericResponse getErrorResponse(String errorMessage) {");
        verify(mockGeneratorOutputFile).writeln(2, "GenericResponse response = new GenericResponse();");
        verify(mockGeneratorOutputFile).writeln(2, "response.code = \"500\";");
        verify(mockGeneratorOutputFile).writeln(2, "response.message = errorMessage;");
        verify(mockGeneratorOutputFile).writeln(2, "response.data = \"\";");
        verify(mockGeneratorOutputFile).writeln(2, "return response;");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");

        verify(mockGeneratorOutputFile).writeln(1, "public static GenericResponse getErrorResponse(String code, String errorMessage) {");
        verify(mockGeneratorOutputFile).writeln(2, "GenericResponse response = new GenericResponse();");
        verify(mockGeneratorOutputFile).writeln(2, "response.code = code;");
        verify(mockGeneratorOutputFile).writeln(2, "response.message = errorMessage;");
        verify(mockGeneratorOutputFile).writeln(2, "response.data = \"\";");
        verify(mockGeneratorOutputFile).writeln(2, "return response;");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");

        verify(mockGeneratorOutputFile).writeln(0, "}");
        verify(mockGeneratorOutputFile).close(); // Ensure the file is closed
    }
}