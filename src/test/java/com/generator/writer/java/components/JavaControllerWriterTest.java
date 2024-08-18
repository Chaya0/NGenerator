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

public class JavaControllerWriterTest {

	@Mock
	private GeneratorOutputFile mockGeneratorOutputFile;

	@InjectMocks
	private JavaControllerWriter controllerWriter;

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
		controllerWriter.create(entity);

		// Then
		verify(mockGeneratorOutputFile).writeln(0, "package com.myApp.controller;");
		verify(mockGeneratorOutputFile).writeln(0, "");
		verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.web.bind.annotation.*;");
		verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.context.annotation.*;");
		verify(mockGeneratorOutputFile).writeln(0, "import io.swagger.v3.oas.annotations.security.SecurityRequirement;");
		verify(mockGeneratorOutputFile).writeln(0, "import com.myApp.service.sampleentity.SampleEntityService;");
		verify(mockGeneratorOutputFile).writeln(0, "import com.myApp.model.SampleEntity;");
		verify(mockGeneratorOutputFile).writeln(0, "");
		verify(mockGeneratorOutputFile).writeln(0, "@Primary");
		verify(mockGeneratorOutputFile).writeln(0, "@CrossOrigin");
		verify(mockGeneratorOutputFile).writeln(0, "@RestController");
		verify(mockGeneratorOutputFile).writeln(0, "@RequestMapping(\"/api/sampleEntity\")");
		verify(mockGeneratorOutputFile).writeln(0, "@SecurityRequirement(name = \"bearerAuth\")");
		verify(mockGeneratorOutputFile).writeln(0, "public class SampleEntityController extends SampleEntityControllerBasic {");
		verify(mockGeneratorOutputFile).writeln(0, "");
		verify(mockGeneratorOutputFile).writeln(1, "public SampleEntityController(SampleEntityService service) {");
		verify(mockGeneratorOutputFile).writeln(2, "super(service);");
		verify(mockGeneratorOutputFile).writeln(1, "}");
		verify(mockGeneratorOutputFile).writeln(0, "}");
		verify(mockGeneratorOutputFile).close(); // Ensure the file is closed
	}
}
