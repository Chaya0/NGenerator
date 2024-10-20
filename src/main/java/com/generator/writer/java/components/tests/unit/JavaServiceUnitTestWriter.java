package com.generator.writer.java.components.tests.unit;

import java.io.IOException;

import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.EnumModel;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaServiceUnitTestWriter implements DefaultWriter {

	private AppModel appModel;

	@Override
	public void create(AppModel model) throws Exception {
		this.appModel = model;
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getUnitTestServicePackagePath(entity.getName()), upperCaseName + "ServiceUnitTest.java", true)) {

			file.writeln(0, "package " + WriterUtils.getUnitTestServicePackageImport(entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.junit.jupiter.api.BeforeEach;");
			file.writeln(0, "import org.junit.jupiter.api.Test;");
			file.writeln(0, "import org.mockito.InjectMocks;");
			file.writeln(0, "import org.mockito.Mock;");
			file.writeln(0, "import org.mockito.MockitoAnnotations;");
			file.writeln(0, "import org.springframework.data.domain.*;");
			file.writeln(0, "import org.springframework.data.jpa.domain.*;");
			file.writeln(0, "import jakarta.persistence.EntityNotFoundException;");
			file.writeln(0, "");
			file.writeln(0, "import java.util.*;");
			file.writeln(0, "import java.time.*;");
			file.writeln(0, WriterUtils.getApplicationExceptionImport());
			file.writeln(0, "import " + WriterUtils.getImportRepositoryPackageName(false, entity.getName()) + "." + upperCaseName + "Repository;");
			file.writeln(0, "import " + WriterUtils.getImportServicePackageName(false, entity.getName()) + "." + upperCaseName + "Service;");
			file.writeln(0, "import " + WriterUtils.getImportModelPackageName() + "." + upperCaseName + ";");
			for (String enumToImort : entity.getEnumsForImport()) {
				file.writeln(0, "import " + WriterUtils.getImportModelEnumsPackageName() + "." + enumToImort + ";");
			}
			file.writeln(0, "");
			file.writeln(0, "import static org.assertj.core.api.Assertions.assertThat;");
			file.writeln(0, "import static org.junit.jupiter.api.Assertions.assertThrows;");
			file.writeln(0, "import static org.mockito.ArgumentMatchers.any;");
			file.writeln(0, "import static org.mockito.Mockito.*;");
			file.writeln(0, "");
			file.writeln(0, "");
			file.writeln(0, "public class " + upperCaseName + "ServiceUnitTest {");
			file.writeln(0, "");
			file.writeln(1, "@Mock");
			file.writeln(1, "private " + upperCaseName + "Repository repository;");
			file.breakLine();
			file.writeln(1, "@InjectMocks");
			file.writeln(1, "private " + upperCaseName + "Service service;");
			file.breakLine();
			file.writeln(1, "private " + upperCaseName + " entity1;");
			file.writeln(1, "private " + upperCaseName + " entity2;");
			file.breakLine();
			file.writeln(1, "@BeforeEach");
			file.writeln(1, "void setUp() {");
			writeMockedEntity(file, entity, "entity1", upperCaseName, "1");
			file.breakLine();
			writeMockedEntity(file, entity, "entity2", upperCaseName, "2");
			file.breakLine();
			file.writeln(1, "}");
			writeBasicServiceTests(file, entity);
			file.breakLine();
			writeTestForGenericService(file, entity);
			file.writeln(0, "}");
		}

	}

	private void writeBasicServiceTests(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		for (Attribute attribute : entity.getAttributes()) {
			String upperCaseAttributeName = StringUtils.uppercaseFirst(attribute.getName());
			file.writeln(1, "@Test");
			file.writeln(1, "void testFindBy" + upperCaseAttributeName + "() {");
			if (attribute.isUnique()) {
				file.writeln(1, "when(repository.findBy" + upperCaseAttributeName + "(" + getGenericAttributeValue(attribute, "1") + ")).thenReturn(Optional.of(entity1));");
				file.writeln(2, "Optional<" + upperCaseName + "> foundEntity = Optional.of(service.findBy" + upperCaseAttributeName + "(" + getGenericAttributeValue(attribute, "1") + "));");
				file.writeln(2, "assertThat(foundEntity).isPresent();");
				file.writeln(2, "assertThat(foundEntity.get()).isEqualTo(entity1);");
			} else {
				file.writeln(1, "when(repository.findBy" + upperCaseAttributeName + "(" + getGenericAttributeValue(attribute, "1") + ")).thenReturn(List.of(entity1));");
				file.writeln(2, "List<" + upperCaseName + "> entities = service.findBy" + upperCaseAttributeName + "(" + getGenericAttributeValue(attribute, "1") + ");");
				file.writeln(2, "assertThat(entities).hasSize(1);");
				file.writeln(2, "assertThat(entities.get(0).get" + upperCaseAttributeName + "()).isEqualTo(" + getGenericAttributeValue(attribute, "1") + ");");
			}
			file.writeln(1, "verify(repository, times(1)).findBy" + upperCaseAttributeName + "(" + getGenericAttributeValue(attribute, "1") + ");");
			file.writeln(1, "}");
			file.breakLine();
		}
		writeSearchTests(file, entity);
	}

	private void writeTestForGenericService(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());

		file.writeln(1, "@Test");
		file.writeln(1, "void testFindById() {");
		file.writeln(2, "when(repository.findById(1L)).thenReturn(Optional.of(entity1));");
		file.writeln(2, "Optional<" + upperCaseName + "> entity = service.findById(1L);");
		file.writeln(2, "assertThat(entity).isPresent();");
		file.writeln(2, "assertThat(entity.get()).isEqualTo(entity1);");
		file.breakLine();
		file.writeln(2, "verify(repository, times(1)).findById(1L);");
		file.writeln(1, "}");
		file.breakLine();

		file.writeln(1, "@Test");
		file.writeln(1, "void testInsert() throws " + WriterUtils.getApplicationExceptionName() + " {");
		file.writeln(2, "when(repository.save(any(" + upperCaseName + ".class))).thenReturn(entity1);");
		writeMockedEntity(file, entity, "entity3", upperCaseName, "3", true);
		file.breakLine();
		file.writeln(2, upperCaseName + " savedEntity = service.insert(entity3);");
		file.breakLine();
		file.writeln(2, "assertThat(savedEntity).isEqualTo(entity1);");
//		for (Attribute attribute : entity.getAttributes()) {
//			String upperCaseAttributeName = StringUtils.uppercaseFirst(attribute.getName());
//			file.writeln(2, "assertThat(savedEntity.get" + upperCaseAttributeName + "()).isEqualTo(" + getGenericAttributeValue(attribute, "3") + ");");
//		}
		file.writeln(2, "verify(repository, times(1)).save(any(" + upperCaseName + ".class));");
		file.writeln(1, "}");
		file.breakLine();

		file.writeln(1, "@Test");
		file.writeln(1, "void testInsertWithIdThrowsException() {");
		writeMockedEntity(file, entity, "entity4", upperCaseName, "4", true);
		file.writeln(2, "entity4.setId(99L);");
		file.breakLine();
		file.writeln(2, "assertThrows(" + WriterUtils.getApplicationExceptionName() + ".class, () -> service.insert(entity4));");
		file.breakLine();
		file.writeln(2, "verify(repository, never()).save(any(" + upperCaseName + ".class));");
		file.writeln(1, "}");
		file.breakLine();

		file.writeln(1, "@Test");
		file.writeln(1, "void testUpdate() throws " + WriterUtils.getApplicationExceptionName() + " {");
		file.writeln(2, "when(repository.findById(entity1.getId())).thenReturn(Optional.of(entity1));");
		file.writeln(2, "when(repository.save(any(" + upperCaseName + ".class))).thenReturn(entity1);");
		file.breakLine();
		Attribute attribute = entity.getAttributes().iterator().next();
		file.writeln(2, "entity1.set" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + getGenericAttributeValue(attribute, "4") + ");");
		file.writeln(2, upperCaseName + " updatedEntity = service.update(entity1);");
		file.breakLine();
		file.writeln(2, "assertThat(updatedEntity.get" + StringUtils.uppercaseFirst(attribute.getName()) + "()).isEqualTo(" + getGenericAttributeValue(attribute, "4") + ");");
		file.writeln(2, "verify(repository, times(1)).findById(entity1.getId());");
		file.writeln(2, "verify(repository, times(1)).save(any(" + upperCaseName + ".class));");
		file.writeln(1, "}");
		file.breakLine();

		file.writeln(1, "@Test");
		file.writeln(1, "void testUpdateThrowsEntityNotFoundException() {");
		file.writeln(2, "when(repository.findById(anyLong())).thenReturn(Optional.empty());");
		file.breakLine();
		file.writeln(2, "assertThrows(EntityNotFoundException.class, () -> service.update(entity1));");
		file.breakLine();
		file.writeln(2, "verify(repository, never()).save(any(" + upperCaseName + ".class));");
		file.writeln(1, "}");
		file.breakLine();

		file.writeln(1, "@Test");
		file.writeln(1, "void testDeleteById() {");
		file.writeln(2, "when(repository.findById(entity1.getId())).thenReturn(Optional.of(entity1));");
		file.writeln(2, "doNothing().when(repository).deleteById(entity1.getId());");
		file.breakLine();
		file.writeln(2, "service.deleteById(entity1.getId());");
		file.writeln(2, "verify(repository, times(1)).deleteById(entity1.getId());");
		file.writeln(1, "}");
		file.breakLine();
	}

	protected void writeMockedEntity(GeneratorOutputFile file, Entity entity, String objectName, String upperCaseName, String objectNumber, boolean newEntity) throws IOException {
		if (newEntity) {
			file.writeln(2, upperCaseName + " " + objectName + " = new " + upperCaseName + "();");
		} else {
			file.writeln(2, objectName + " = new " + upperCaseName + "();");
		}
		for (Attribute attribute : entity.getAttributes()) {
			file.writeln(2, objectName + ".set" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + getGenericAttributeValue(attribute, objectNumber) + ");");
		}
	}

	protected void writeMockedEntity(GeneratorOutputFile file, Entity entity, String objectName, String upperCaseName, String objectNumber) throws IOException {
		writeMockedEntity(file, entity, objectName, upperCaseName, objectNumber, false);
	}

	private void writeSearchTests(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());

		file.writeln(1, "@Test");
		file.writeln(1, "void testFindAllWithoutArguments() {");
		file.writeln(2, "when(repository.findAll()).thenReturn(List.of(entity1, entity2));");
		file.writeln(2, "List<" + upperCaseName + "> entities = service.findAll();");
		file.breakLine();
		file.writeln(2, "assertThat(entities).hasSize(2);");
		file.writeln(2, "assertThat(entities).containsExactly(entity1, entity2);");
		file.breakLine();
		file.writeln(2, "verify(repository, times(1)).findAll();");
		file.writeln(1, "}");
		file.breakLine();

		file.writeln(1, "@Test");
		file.writeln(1, "void testFindAllWithPageable() {");
		file.writeln(2, "Pageable pageable = PageRequest.of(0, 2);");
		file.writeln(2, "Page<" + upperCaseName + "> page = new PageImpl<>(List.of(entity1, entity2), pageable, 2);");
		file.writeln(2, "when(repository.findAll(pageable)).thenReturn(page);");
		file.breakLine();
		file.writeln(2, "Page<" + upperCaseName + "> entities = service.findAll(pageable);");
		file.breakLine();
		file.writeln(2, "assertThat(entities.getContent()).hasSize(2);");
		file.writeln(2, "assertThat(entities.getContent()).containsExactly(entity1, entity2);");
		file.writeln(2, "assertThat(entities.getTotalElements()).isEqualTo(2);");
		file.breakLine();
		file.writeln(2, "verify(repository, times(1)).findAll(pageable);");
		file.writeln(1, "}");
		file.breakLine();

		file.writeln(1, "@Test");
		file.writeln(1, "void testFindAllWithSpecification() {");
		file.writeln(2, "Specification<" + upperCaseName + "> specification = mock(Specification.class);");
		file.writeln(2, "when(repository.findAll(specification)).thenReturn(List.of(entity1, entity2));");
		file.breakLine();
		file.writeln(2, "List<" + upperCaseName + "> entities = service.findAll(specification);");
		file.breakLine();
		file.writeln(2, "assertThat(entities).hasSize(2);");
		file.writeln(2, "assertThat(entities).containsExactly(entity1, entity2);");
		file.breakLine();
		file.writeln(2, "verify(repository, times(1)).findAll(specification);");
		file.writeln(1, "}");
		file.breakLine();

		file.writeln(1, "@Test");
		file.writeln(1, "void testFindAllWithSpecificationAndPageable() {");
		file.writeln(2, "Specification<" + upperCaseName + "> specification = mock(Specification.class);");
		file.writeln(2, "Pageable pageable = PageRequest.of(0, 2);");
		file.writeln(2, "Page<" + upperCaseName + "> page = new PageImpl<>(List.of(entity1, entity2), pageable, 2);");
		file.breakLine();
		file.writeln(2, "when(repository.findAll(specification, pageable)).thenReturn(page);");
		file.breakLine();
		file.writeln(2, "Page<" + upperCaseName + "> entities = service.findAll(specification, pageable);");
		file.breakLine();
		file.writeln(2, "assertThat(entities.getContent()).hasSize(2);");
		file.writeln(2, "assertThat(entities.getContent()).containsExactly(entity1, entity2);");
		file.writeln(2, "assertThat(entities.getTotalElements()).isEqualTo(2);");
		file.breakLine();
		file.writeln(2, "verify(repository, times(1)).findAll(specification, pageable);");
		file.writeln(1, "}");
		file.breakLine();
	}

	protected String getGenericAttributeValue(Attribute attribute, String objectNumber) {
		switch (attribute.getType()) {
		case STRING: {
			return "\"" + attribute.getName() + objectNumber + "\"";
		}
		case INTEGER: {
			return objectNumber;
		}
		case DOUBLE: {
			return objectNumber + ".00";
		}
		case LONG: {
			return objectNumber + "L";
		}
		case DATE: {
			if (objectNumber.equals("1")) {
				return "new Date(2020,1,1)";
			} else {
				return "new Date(2020,2,1)";
			}
		}
		case LOCAL_DATE: {
			if (objectNumber.equals("1")) {
				return "LocalDate.of(2020, 1, 1)";
			} else {
				return "LocalDate.of(2020, 2, 1)";
			}
		}
		case LOCAL_DATE_IME: {
			if (objectNumber.equals("1")) {
				return "LocalDateTime.of(2020, 1, 1, 0, 0)";
			} else {
				return "LocalDateTime.of(2020, 2, 1, 0, 0)";
			}
		}
		case BOOLEAN: {
			return "true";
		}
		case ENUM: {
			EnumModel enumModel = appModel.getEnumModelByName(attribute.getEnumName());
			if (!objectNumber.equals("1") && enumModel.getValues().size() > 1) {
				return StringUtils.uppercaseFirst(attribute.getEnumName()) + "." + enumModel.getValues().get(1);
			} else {
				return StringUtils.uppercaseFirst(attribute.getEnumName()) + "." + enumModel.getValues().get(0);
			}
		}
		default:
		}
		return "";
	}
}