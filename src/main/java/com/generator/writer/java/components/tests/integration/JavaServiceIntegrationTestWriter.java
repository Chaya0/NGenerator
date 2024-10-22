package com.generator.writer.java.components.tests.integration;

import java.io.IOException;

import com.generator.Application;
import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.EnumModel;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaServiceIntegrationTestWriter implements DefaultWriter {
	private AppModel appModel;

	@Override
	public void create(AppModel model) throws Exception {
		appModel = model;
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getIntegrationTestServicePackagePath(entity.getName()), upperCaseName + "ServiceIntegrationTest.java", true)) {

			file.writeln(0, "package " + WriterUtils.getIntegrationTestServicePackageImport(entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.junit.jupiter.api.BeforeEach;");
			file.writeln(0, "import org.junit.jupiter.api.Test;");
			file.writeln(0, "import org.junit.jupiter.api.TestInstance;");
			file.writeln(0, "import org.springframework.beans.factory.annotation.Autowired;");
			file.writeln(0, "import org.springframework.boot.test.context.SpringBootTest;");
			file.writeln(0, "import org.springframework.test.context.ActiveProfiles;");
			file.writeln(0, "import org.springframework.test.annotation.DirtiesContext;");
			file.writeln(0, "import org.springframework.transaction.annotation.Transactional;");
			file.writeln(0, "import static org.assertj.core.api.Assertions.assertThat;");
			file.writeln(0, "");
			file.writeln(0, "import " + WriterUtils.getImportRepositoryPackageName(false, entity.getName()) + "." + upperCaseName + "Repository;");
			if (Application.getGeneratorProperties().isGeneratePermissionsAndRoles() && (entity.getName().equals("role") || entity.getName().equals("permission"))) {
				file.writeln(0, "import " + WriterUtils.getImportRepositoryPackageName(false, "user") + ".UserRepository;");
				if (entity.getName().equals("permission")) {
					file.writeln(0, "import " + WriterUtils.getImportRepositoryPackageName(false, "role") + ".RoleRepository;");
				}
			}
			file.writeln(0, "import " + WriterUtils.getImportServicePackageName(false, entity.getName()) + "." + upperCaseName + "Service;");
			file.writeln(0, "import " + WriterUtils.getImportModelPackageName() + "." + upperCaseName + ";");
			for (String enumToImort : entity.getEnumsForImport()) {
				file.writeln(0, "import " + WriterUtils.getImportModelEnumsPackageName() + "." + enumToImort + ";");
			}
			file.writeln(0, "");
			file.writeln(0, "import java.util.*;");
			file.writeln(0, "import java.time.*;");
			file.writeln(0, "");
			file.writeln(0, "@ActiveProfiles(\"test\")");
			file.writeln(0, "@SpringBootTest  // This starts the full Spring Boot context for service integration tests");
			file.writeln(0, "@Transactional   // Ensures that each test is rolled back automatically");
			file.writeln(0, "@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)");
			file.writeln(0, "@TestInstance(TestInstance.Lifecycle.PER_CLASS)");
			file.writeln(0, "class " + upperCaseName + "ServiceIntegrationTest {");
			file.writeln(0, "");
			file.writeln(1, "@Autowired");
			file.writeln(1, "private " + upperCaseName + "Repository repository;");
			file.breakLine();
			if (Application.getGeneratorProperties().isGeneratePermissionsAndRoles() && (entity.getName().equals("role") || entity.getName().equals("permission"))) {
				file.writeln(1, "@Autowired");
				file.writeln(1, "private UserRepository userRepository;");
				file.breakLine();
				if (entity.getName().equals("permission")) {
					file.writeln(1, "@Autowired");
					file.writeln(1, "private RoleRepository roleRepository;");
					file.breakLine();
				}
			}
			file.writeln(1, "@Autowired");
			file.writeln(1, "private " + upperCaseName + "Service service;");
			file.breakLine();
			file.writeln(1, "@BeforeEach");
			file.writeln(1, "void setUp() {");
			if (Application.getGeneratorProperties().isGeneratePermissionsAndRoles() && (entity.getName().equals("role") || entity.getName().equals("permission"))) {
				file.writeln(1, "userRepository.deleteAll();");
				if (entity.getName().equals("permission")) {
					file.writeln(1, "roleRepository.deleteAll();");
				}
			}
			file.writeln(1, "repository.deleteAll();");
			writeMockedEntity(file, entity, "entity1", upperCaseName, "1");
			file.writeln(2, "entity1.setId(1L);");
			file.breakLine();
			writeMockedEntity(file, entity, "entity2", upperCaseName, "2");
			file.writeln(2, "entity2.setId(2L);");
			file.breakLine();
			file.writeln(2, "repository.save(entity1);");
			file.writeln(2, "repository.save(entity2);");
			file.writeln(1, "}");
			file.breakLine();

			writeTestForGenericService(file, entity);

			writeBasicServiceTests(file, entity);

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
				file.writeln(2, "Optional<" + upperCaseName + "> entity1 = Optional.of(service.findBy" + upperCaseAttributeName + "(" + getGenericAttributeValue(attribute, "1") + "));");
				file.writeln(2, "assertThat(entity1).isPresent();");
				file.writeln(2, "assertThat(entity1.get().get" + upperCaseAttributeName + "()).isEqualTo(" + getGenericAttributeValue(attribute, "1") + ");");
			} else {
				file.writeln(2, "List<" + upperCaseName + "> entities = service.findBy" + upperCaseAttributeName + "(" + getGenericAttributeValue(attribute, "1") + ");");
				file.writeln(2, "assertThat(entities).hasSize(1);");
				file.writeln(2, "assertThat(entities.get(0).get" + upperCaseAttributeName + "()).isEqualTo(" + getGenericAttributeValue(attribute, "1") + ");");
			}
			file.writeln(1, "}");
			file.breakLine();
		}
	}

	private void writeTestForGenericService(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());

		file.writeln(1, "@Test");
		file.writeln(1, "void testFindById() {");
		file.writeln(2, "Optional<" + upperCaseName + "> entity = service.findById(repository.findAll().get(0).getId());");
		file.writeln(2, "assertThat(entity).isPresent();");
		file.writeln(1, "}");
		file.breakLine();

		file.writeln(1, "@Test");
		file.writeln(1, "void testSave() {");
		writeMockedEntity(file, entity, "entity3", upperCaseName, "3");
		file.breakLine();
		file.writeln(2, upperCaseName + " savedEntity = service.save(entity3);");
		file.breakLine();
		file.writeln(2, "assertThat(savedEntity.getId()).isNotNull();");
		for (Attribute attribute : entity.getAttributes()) {
			String upperCaseAttributeName = StringUtils.uppercaseFirst(attribute.getName());
			file.writeln(2, "assertThat(savedEntity.get" + upperCaseAttributeName + "()).isEqualTo(" + getGenericAttributeValue(attribute, "3") + ");");
		}
		file.writeln(1, "}");
		file.breakLine();

		file.writeln(1, "@Test");
		file.writeln(1, "void testDeleteById() {");
		file.writeln(2, "service.deleteById(1L);");
		file.breakLine();
		file.writeln(2, "Optional<" + upperCaseName + "> deletedEntity = service.findById(1L);");
		file.writeln(2, "assertThat(deletedEntity).isNotPresent();");
		file.writeln(1, "}");
		file.breakLine();
	}

	protected void writeMockedEntity(GeneratorOutputFile file, Entity entity, String objectName, String upperCaseName, String objectNumber) throws IOException {
		file.writeln(2, upperCaseName + " " + objectName + " = new " + upperCaseName + "();");
		for (Attribute attribute : entity.getAttributes()) {
			file.writeln(2, objectName + ".set" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + getGenericAttributeValue(attribute, objectNumber) + ");");
		}
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
			if (objectNumber.equals("1")) {
				return "true";
			} else {
				return "false";
			}
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
