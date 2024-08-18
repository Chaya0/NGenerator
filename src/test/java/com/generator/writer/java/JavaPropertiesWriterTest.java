package com.generator.writer.java;

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

public class JavaPropertiesWriterTest {

	@Mock
	private GeneratorOutputFile mockGeneratorOutputFile;

	@InjectMocks
	private JavaPropertiesWriter javaPropertiesWriter;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		// Setup SpringProperties
		Properties properties = new Properties();
		properties.setProperty("datasourceUrl", "jdbc:mysql://localhost:3306/mydb");
		properties.setProperty("datasourceUsername", "user");
		properties.setProperty("datasourcePassword", "pass");
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
		doNothing().when(mockGeneratorOutputFile).writeln(anyInt(), anyString());

		// When
		javaPropertiesWriter.create();

		// Then
		verify(mockGeneratorOutputFile).writeln(0, "spring.datasource.url=jdbc:mysql://localhost:3306/mydb");
		verify(mockGeneratorOutputFile).writeln(0, "spring.datasource.username=user");
		verify(mockGeneratorOutputFile).writeln(0, "spring.datasource.password=pass");
		verify(mockGeneratorOutputFile).writeln(0, "spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect");
		verify(mockGeneratorOutputFile).writeln(0, "spring.jpa.generate-ddl=true");
		verify(mockGeneratorOutputFile).writeln(0, "spring.datasource.driver-class-name=com.mysql.jdbc.Driver");
		verify(mockGeneratorOutputFile).writeln(0, "spring.jpa.hibernate.ddl-auto=create-drop");

		verify(mockGeneratorOutputFile).close(); // Ensure the file is closed
	}
}