package com.generator.writer.java.config;

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

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaSwaggerWriterTest {

	@Mock
	private GeneratorOutputFile mockGeneratorOutputFile;

	@InjectMocks
	private JavaSwaggerWriter javaSwaggerWriter;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);

		// Setup default package
		Properties properties = new Properties();
		properties.setProperty("importDefaultPackage", "com.myApp");

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
        javaSwaggerWriter.create();

        // Then
        verify(mockGeneratorOutputFile).writeln(0, "package com.myApp.config;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "import io.swagger.v3.oas.annotations.OpenAPIDefinition;");
        verify(mockGeneratorOutputFile).writeln(0, "import io.swagger.v3.oas.annotations.info.Info;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.context.annotation.Configuration;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "@Configuration");
        verify(mockGeneratorOutputFile).writeln(0, "@OpenAPIDefinition(info = @Info(title = \"My API\", version = \"v1\"))");
        verify(mockGeneratorOutputFile).writeln(0, "public class OpenApiConfiguration {");
        verify(mockGeneratorOutputFile).writeln(0, "}");
        verify(mockGeneratorOutputFile).close(); // Ensure the file is closed
    }
}