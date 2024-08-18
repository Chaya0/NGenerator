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
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.enums.AttributeType;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

class JavaBasicRepositoryWriterTest {

	@InjectMocks
	private JavaBasicRepositoryWriter javaBasicRepositoryWriter;

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
		Attribute uniqueAttribute = new Attribute();
		uniqueAttribute.setName("uniqueAttr");
		uniqueAttribute.setUnique(true);
		uniqueAttribute.setType(AttributeType.STRING);

		Entity entity = new Entity();
		entity.setName("TestEntity");
		entity.setAttributes(Arrays.asList(uniqueAttribute));

		AppModel appModel = new AppModel();
		appModel.setEntities(Arrays.asList(entity));

		// Mock WriterUtils
		when(writerUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(generatorOutputFile);

		// Act
		javaBasicRepositoryWriter.create(appModel);

		// Assert
		verify(writerUtils, times(1)).getOutputResource(eq(WriterUtils.getRepositoryPackagePath(false, "TestEntity")), eq("TestEntityRepositoryBasic.java"), eq(true));
		verify(generatorOutputFile, times(1)).writeln(0, "package " + WriterUtils.getImportRepositoryPackageName(false, "TestEntity") + ";");
		verify(generatorOutputFile, times(1)).writeln(0, "public interface TestEntityRepositoryBasic extends GenericRepository<TestEntity> {");
		verify(generatorOutputFile, times(1)).writeln(1, "Optional<TestEntity> findByUniqueAttr(String uniqueAttr);");
	}

	@Test
	void testCreateEntityWithEnumAttributes() throws Exception {
		// Arrange
		Attribute uniqueEnumAttribute = new Attribute();
		uniqueEnumAttribute.setName("status");
		uniqueEnumAttribute.setUnique(true);
		uniqueEnumAttribute.setEnumName("StatusEnum");

		Entity entity = new Entity();
		entity.setName("Order");
		entity.setAttributes(Arrays.asList(uniqueEnumAttribute));

		AppModel appModel = new AppModel();
		appModel.setEntities(Arrays.asList(entity));

		// Mock WriterUtils
		when(writerUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(generatorOutputFile);

		// Act
		javaBasicRepositoryWriter.create(entity);

		// Assert
		verify(writerUtils, times(1)).getOutputResource(eq(WriterUtils.getRepositoryPackagePath(false, "Order")), eq("OrderRepositoryBasic.java"), eq(true));
		verify(generatorOutputFile, times(1)).writeln(0, "package " + WriterUtils.getImportRepositoryPackageName(false, "Order") + ";");
		verify(generatorOutputFile, times(1)).writeln(0, "public interface OrderRepositoryBasic extends GenericRepository<Order> {");
		verify(generatorOutputFile, times(1)).writeln(1, "Optional<Order> findByStatus(StatusEnum status);");
	}
}
