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

class JavaBasicServiceWriterTest {

	@InjectMocks
	private JavaBasicServiceWriter javaBasicServiceWriter;

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
		javaBasicServiceWriter.create(appModel);

		// Assert
		verify(writerUtils, times(1)).getOutputResource(eq(WriterUtils.getServicePackagePath(false, "TestEntity")), eq("TestEntityServiceBasic.java"), eq(true));
		verify(generatorOutputFile, times(1)).writeln(0, "package " + WriterUtils.getImportServicePackageName(false, "TestEntity") + ";");
		verify(generatorOutputFile, times(1)).writeln(0, "public class TestEntityServiceBasic extends GenericService<TestEntity> {");
		verify(generatorOutputFile, times(1)).writeln(1, "protected final TestEntityRepository repository;");
		verify(generatorOutputFile, times(1)).writeln(1, "public TestEntityServiceBasic(TestEntityRepository repository) {");
		verify(generatorOutputFile, times(1)).writeln(2, "super(repository);");
		verify(generatorOutputFile, times(1)).writeln(2, "this.repository = repository;");
		verify(generatorOutputFile, times(1)).writeln(1, "}");
		verify(generatorOutputFile, times(1)).writeln(1, "@Override");
		verify(generatorOutputFile, times(1)).writeln(1, "public TestEntity insert(TestEntity object) throws ApplicationException {");
		verify(generatorOutputFile, times(1)).writeln(2, "if(object.getId() != null) throw new ApplicationException(\"Entity already exists: \" + object.getId());");
		verify(generatorOutputFile, times(1)).writeln(2, "return repository.save(object);");
		verify(generatorOutputFile, times(1)).writeln(1, "}");
		verify(generatorOutputFile, times(1)).writeln(1, "@Override");
		verify(generatorOutputFile, times(1)).writeln(1, "public TestEntity update(TestEntity object) throws ApplicationException {");
		verify(generatorOutputFile, times(1)).writeln(2,
				"if(object.getId() == null || repository.findById(object.getId()).isEmpty()) throw new EntityNotFoundException(String.valueOf(object.getId()));");
		verify(generatorOutputFile, times(1)).writeln(2, "return repository.save(object);");
		verify(generatorOutputFile, times(1)).writeln(1, "}");
		verify(generatorOutputFile, times(1)).writeln(1, "@Override");
		verify(generatorOutputFile, times(1)).writeln(1, "public Class<TestEntity> getEntityClass() {");
		verify(generatorOutputFile, times(1)).writeln(2, "return TestEntity.class;");
		verify(generatorOutputFile, times(1)).writeln(1, "}");
		verify(generatorOutputFile, times(1)).writeln(1, "public TestEntity findByUniqueAttr(String uniqueAttr) {");
		verify(generatorOutputFile, times(1)).writeln(2, "return repository.findByUniqueAttr(uniqueAttr).orElseThrow();");
		verify(generatorOutputFile, times(1)).writeln(1, "}");
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
		javaBasicServiceWriter.create(entity);

		// Assert
		verify(writerUtils, times(1)).getOutputResource(eq(WriterUtils.getServicePackagePath(false, "Order")), eq("OrderServiceBasic.java"), eq(true));
		verify(generatorOutputFile, times(1)).writeln(0, "package " + WriterUtils.getImportServicePackageName(false, "Order") + ";");
		verify(generatorOutputFile, times(1)).writeln(0, "public class OrderServiceBasic extends GenericService<Order> {");
		verify(generatorOutputFile, times(1)).writeln(1, "protected final OrderRepository repository;");
		verify(generatorOutputFile, times(1)).writeln(1, "public OrderServiceBasic(OrderRepository repository) {");
		verify(generatorOutputFile, times(1)).writeln(2, "super(repository);");
		verify(generatorOutputFile, times(1)).writeln(2, "this.repository = repository;");
		verify(generatorOutputFile, times(1)).writeln(1, "}");
		verify(generatorOutputFile, times(1)).writeln(1, "@Override");
		verify(generatorOutputFile, times(1)).writeln(1, "public Order insert(Order object) throws ApplicationException {");
		verify(generatorOutputFile, times(1)).writeln(2, "if(object.getId() != null) throw new ApplicationException(\"Entity already exists: \" + object.getId());");
		verify(generatorOutputFile, times(1)).writeln(2, "return repository.save(object);");
		verify(generatorOutputFile, times(1)).writeln(1, "}");
		verify(generatorOutputFile, times(1)).writeln(1, "@Override");
		verify(generatorOutputFile, times(1)).writeln(1, "public Order update(Order object) throws ApplicationException {");
		verify(generatorOutputFile, times(1)).writeln(2,
				"if(object.getId() == null || repository.findById(object.getId()).isEmpty()) throw new EntityNotFoundException(String.valueOf(object.getId()));");
		verify(generatorOutputFile, times(1)).writeln(2, "return repository.save(object);");
		verify(generatorOutputFile, times(1)).writeln(1, "}");
		verify(generatorOutputFile, times(1)).writeln(1, "@Override");
		verify(generatorOutputFile, times(1)).writeln(1, "public Class<Order> getEntityClass() {");
		verify(generatorOutputFile, times(1)).writeln(2, "return Order.class;");
		verify(generatorOutputFile, times(1)).writeln(1, "}");
		verify(generatorOutputFile, times(1)).writeln(1, "public Order findByStatus(StatusEnum status) {");
		verify(generatorOutputFile, times(1)).writeln(2, "return repository.findByStatus(status).orElseThrow();");
		verify(generatorOutputFile, times(1)).writeln(1, "}");
	}
}
