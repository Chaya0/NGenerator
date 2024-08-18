package com.generator.writer.java.components;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.generator.model.Entity;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaRepositoryWriterTest {

	@Mock
	private GeneratorOutputFile mockGeneratorOutputFile;

	@InjectMocks
	private JavaRepositoryWriter repositoryWriter;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		// Mock static methods if needed
	}

	@Test
	public void testCreate() throws Exception {
		// Given
		Entity entity = new Entity(); // Mock or create an entity
		entity.setName("sampleEntity");
		when(WriterUtils.fileExists(anyString(), anyString())).thenReturn(false);
		when(WriterUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(mockGeneratorOutputFile);
		when(mockGeneratorOutputFile.hasAlreadyExisted()).thenReturn(false);
		doNothing().when(mockGeneratorOutputFile).writeln(anyInt(), anyString());

		// When
		repositoryWriter.create(entity);

		// Then
		verify(mockGeneratorOutputFile).writeln(0, "package com.myApp.repository;");
		verify(mockGeneratorOutputFile).writeln(0, "");
		verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.stereotype.Repository;");
		verify(mockGeneratorOutputFile).writeln(0, "");
		verify(mockGeneratorOutputFile).writeln(0, "@Repository");
		verify(mockGeneratorOutputFile).writeln(0, "public interface SampleEntityRepository extends SampleEntityRepositoryBasic {");
		verify(mockGeneratorOutputFile).writeln(0, "");
		verify(mockGeneratorOutputFile).writeln(0, "}");
		verify(mockGeneratorOutputFile).close(); // Ensure the file is closed
	}
}