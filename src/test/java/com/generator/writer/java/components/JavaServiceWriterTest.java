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

public class JavaServiceWriterTest {

	@Mock
	private GeneratorOutputFile mockGeneratorOutputFile;

	@InjectMocks
	private JavaServiceWriter serviceWriter;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		// Mock static methods if needed
	}

	@Test
	public void testCreate() throws Exception {
		// Given
		Entity entity = new Entity();
		entity.setName("sampleEntity");
		String upperCaseName = "SampleEntity";

		when(WriterUtils.fileExists(anyString(), anyString())).thenReturn(false);
		when(WriterUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(mockGeneratorOutputFile);
		when(mockGeneratorOutputFile.hasAlreadyExisted()).thenReturn(false);
		doNothing().when(mockGeneratorOutputFile).writeln(anyInt(), anyString());

		// When
		serviceWriter.create(entity);

		// Then
		verify(mockGeneratorOutputFile).writeln(0, "package com.myApp.service;");
		verify(mockGeneratorOutputFile).writeln(0, "");
		verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.context.annotation.*;");
		verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.stereotype.*;");
		verify(mockGeneratorOutputFile).writeln(0, "import com.myApp.repository." + upperCaseName + "Repository;");
		verify(mockGeneratorOutputFile).writeln(0, "");
		verify(mockGeneratorOutputFile).writeln(0, "@Service");
		verify(mockGeneratorOutputFile).writeln(0, "public class " + upperCaseName + "Service extends " + upperCaseName + "ServiceBasic {");
		verify(mockGeneratorOutputFile).writeln(0, "");
		verify(mockGeneratorOutputFile).writeln(1, "public " + upperCaseName + "Service(" + upperCaseName + "Repository repository) {");
		verify(mockGeneratorOutputFile).writeln(2, "super(repository);");
		verify(mockGeneratorOutputFile).writeln(1, "}");
		verify(mockGeneratorOutputFile).writeln(0, "}");
		verify(mockGeneratorOutputFile).close(); // Ensure the file is closed
	}

	@Test
	public void testCreate2() throws Exception {
		// Given
		Entity entity = new Entity();
		entity.setName("sampleEntity");
		String upperCaseName = "SampleEntity";

		when(WriterUtils.fileExists(anyString(), anyString())).thenReturn(false);
		when(WriterUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(mockGeneratorOutputFile);
		when(mockGeneratorOutputFile.hasAlreadyExisted()).thenReturn(false);
		doNothing().when(mockGeneratorOutputFile).writeln(anyInt(), anyString());

		// When
		serviceWriter.create2(entity);

		// Then
		verify(mockGeneratorOutputFile).writeln(0, "package com.myApp.service;");
		verify(mockGeneratorOutputFile).writeln(0, "");
		verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.context.annotation.*;");
		verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.stereotype.*;");
		verify(mockGeneratorOutputFile).writeln(0, "import com.myApp.service.GenericService;");
		verify(mockGeneratorOutputFile).writeln(0, "import com.myApp.model." + upperCaseName + ";");
		verify(mockGeneratorOutputFile).writeln(0, "import com.myApp.repository." + upperCaseName + "Repository;");
		verify(mockGeneratorOutputFile).writeln(0, "");
		verify(mockGeneratorOutputFile).writeln(0, "@Service");
		verify(mockGeneratorOutputFile).writeln(0, "@Primary");
		verify(mockGeneratorOutputFile).writeln(0, "public class " + upperCaseName + "Service extends GenericService<" + upperCaseName + "> {");
		verify(mockGeneratorOutputFile).writeln(0, "");
		verify(mockGeneratorOutputFile).writeln(1, "public " + upperCaseName + "Service(" + upperCaseName + "Repository repository) {");
		verify(mockGeneratorOutputFile).writeln(2, "super(repository);");
		verify(mockGeneratorOutputFile).writeln(1, "}");
		verify(mockGeneratorOutputFile).writeln(0, "}");
		verify(mockGeneratorOutputFile).close(); // Ensure the file is closed
	}
}
