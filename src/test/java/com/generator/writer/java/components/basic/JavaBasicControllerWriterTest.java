package com.generator.writer.java.components.basic;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

class JavaBasicControllerWriterTest {

	@InjectMocks
	private JavaBasicControllerWriter javaBasicControllerWriter;

	@Mock
	private WriterUtils writerUtils;

	@Mock
	private GeneratorOutputFile generatorOutputFile;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateAppModel() throws Exception {
		// Arrange
		Entity entity = new Entity();
		entity.setName("TestEntity");
		AppModel appModel = new AppModel();
		appModel.setEntities(Arrays.asList(entity));

		// Mock WriterUtils
		when(writerUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(generatorOutputFile);

		// Act
		javaBasicControllerWriter.create(appModel);

		// Assert
		verify(writerUtils, times(1)).getOutputResource(eq(WriterUtils.getControllerPackagePath(false, "TestEntity")), eq("TestEntityControllerBasic.java"), eq(true));
		verify(generatorOutputFile, times(1)).writeln(0, "package " + WriterUtils.getImportControllerPackageName(false, "TestEntity") + ";");
		verify(generatorOutputFile, times(1)).writeln(0, "public class TestEntityControllerBasic extends GenericController<TestEntity> {");
		verify(generatorOutputFile, times(1)).writeln(1, "protected TestEntityService testEntityService;");
		verify(generatorOutputFile, times(1)).writeln(1, "public TestEntityControllerBasic(TestEntityService service) {");
		verify(generatorOutputFile, times(1)).writeln(2, "super(service);");
		verify(generatorOutputFile, times(1)).writeln(2, "this.testEntityService = service;");
		verify(generatorOutputFile, times(1)).writeln(1, "}");
		verify(generatorOutputFile, times(1)).writeln(1, "@Override");
		verify(generatorOutputFile, times(1)).writeln(1, "@PreAuthorize(\"hasAuthority('can_view_test_entity')\")");
		verify(generatorOutputFile, times(1)).writeln(1, "public ResponseEntity<?> findAll() {");
	}

	@Test
	void testCreateEntity() throws Exception {
		// Arrange
		Entity entity = new Entity();
		entity.setName("TestEntity");

		// Mock WriterUtils
		when(writerUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(generatorOutputFile);

		// Act
		javaBasicControllerWriter.create(entity);

		// Assert
		verify(writerUtils, times(1)).getOutputResource(eq(WriterUtils.getControllerPackagePath(false, "TestEntity")), eq("TestEntityControllerBasic.java"), eq(true));
		verify(generatorOutputFile, times(1)).writeln(0, "package " + WriterUtils.getImportControllerPackageName(false, "TestEntity") + ";");
		verify(generatorOutputFile, times(1)).writeln(0, "public class TestEntityControllerBasic extends GenericController<TestEntity> {");
		verify(generatorOutputFile, times(1)).writeln(1, "protected TestEntityService testEntityService;");
		verify(generatorOutputFile, times(1)).writeln(1, "public TestEntityControllerBasic(TestEntityService service) {");
		verify(generatorOutputFile, times(1)).writeln(2, "super(service);");
		verify(generatorOutputFile, times(1)).writeln(2, "this.testEntityService = service;");
		verify(generatorOutputFile, times(1)).writeln(1, "}");
		verify(generatorOutputFile, times(1)).writeln(1, "@Override");
		verify(generatorOutputFile, times(1)).writeln(1, "@PreAuthorize(\"hasAuthority('can_view_test_entity')\")");
		verify(generatorOutputFile, times(1)).writeln(1, "public ResponseEntity<?> findAll() {");
	}
}
