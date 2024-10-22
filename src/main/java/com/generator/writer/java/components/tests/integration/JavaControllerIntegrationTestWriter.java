package com.generator.writer.java.components.tests.integration;

import java.io.IOException;
import java.util.UUID;

import com.generator.Application;
import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.EnumModel;
import com.generator.model.enums.AttributeType;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaControllerIntegrationTestWriter implements DefaultWriter {
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
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getIntegrationTestControllerPackagePath(entity.getName()), upperCaseName + "ControllerIntegrationTest.java", true)) {

			file.writeln(0, "package " + WriterUtils.getIntegrationTestControllerPackageImport(entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.junit.jupiter.api.BeforeEach;");
			file.writeln(0, "import org.junit.jupiter.api.Test;");
			file.writeln(0, "import org.mockito.Mockito;");
			file.writeln(0, "import org.mockito.invocation.InvocationOnMock;");
			file.writeln(0, "import org.mockito.stubbing.Answer;");
			file.writeln(0, "import org.springframework.beans.factory.annotation.Autowired;");
			file.writeln(0, "import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;");
			file.writeln(0, "import org.springframework.boot.test.context.SpringBootTest;");
			file.writeln(0, "import org.springframework.http.MediaType;");
			file.writeln(0, "import org.springframework.security.core.Authentication;");
			file.writeln(0, "import org.springframework.security.core.GrantedAuthority;");
			file.writeln(0, "import org.springframework.security.core.context.SecurityContext;");
			file.writeln(0, "import org.springframework.security.core.context.SecurityContextHolder;");
			file.writeln(0, "import org.springframework.test.context.ActiveProfiles;");
			file.writeln(0, "import org.springframework.test.web.servlet.MockMvc;");
			file.writeln(0, "import org.springframework.transaction.annotation.Transactional;");
			file.writeln(0, "import com.fasterxml.jackson.databind.ObjectMapper;");
			file.writeln(0, "");
			file.writeln(0, "import " + WriterUtils.getImportRepositoryPackageName(false, entity.getName()) + "." + upperCaseName + "Repository;");
			file.writeln(0, "import " + WriterUtils.getImportServicePackageName(false, entity.getName()) + "." + upperCaseName + "Service;");
			file.writeln(0, "import " + WriterUtils.getImportModelPackageName() + "." + upperCaseName + ";");
			file.writeln(0, "import " + WriterUtils.getSpecificationPackageImportPath() + ".*;");
			if (Application.getGeneratorProperties().isGeneratePermissionsAndRoles()) {
				file.writeln(0, "import " + WriterUtils.getImportModelPackageName() + ".Permission;");
			}
			for (String enumToImort : entity.getEnumsForImport()) {
				file.writeln(0, "import " + WriterUtils.getImportModelEnumsPackageName() + "." + enumToImort + ";");
			}
			file.writeln(0, "");
			file.writeln(0, "import java.util.*;");
			file.writeln(0, "import java.time.*;");
			file.writeln(0, "");
			file.writeln(0, "import static org.mockito.Mockito.mock;");
			file.writeln(0, "import static org.mockito.Mockito.when;");
			file.writeln(0, "import static org.hamcrest.Matchers.greaterThan;");
			file.writeln(0, "import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;");
			file.writeln(0, "import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;");
			file.writeln(0, "import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;");
			file.writeln(0, "import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;");
			file.writeln(0, "import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;");
			file.writeln(0, "import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;");
			file.writeln(0, "");
			file.writeln(0, "@SpringBootTest");
			file.writeln(0, "@AutoConfigureMockMvc");
			file.writeln(0, "@Transactional");
			file.writeln(0, "@ActiveProfiles(\"test\")");
			file.writeln(0, "class " + upperCaseName + "ControllerIntegrationTest {");
			file.writeln(0, "");
			file.writeln(1, "@Autowired");
			file.writeln(1, "private MockMvc mockMvc;");
			file.writeln(0, "");
			file.writeln(1, "@Autowired");
			file.writeln(1, "private " + upperCaseName + "Repository repository;");
			file.writeln(0, "");
			file.writeln(1, "@Autowired");
			file.writeln(1, "private ObjectMapper objectMapper;");
			file.writeln(0, "");
			file.writeln(1, "private " + upperCaseName + " entity1;");
			file.writeln(0, "");
			file.writeln(1, "@BeforeEach");
			file.writeln(1, "void setUp() {");
			file.writeln(2, "objectMapper.findAndRegisterModules();");
			if(entity.getName().equals("permission") || entity.getName().equals("role")) {
				file.writeln(2, "if (entity1 != null && entity1.getId() != null) repository.deleteById(entity1.getId());");
			}else {
				file.writeln(2, "repository.deleteAll();");
			}
			writeMockedEntity(file, entity, "entity1", upperCaseName, "1");
			file.writeln(2, "entity1 = repository.save(entity1);");
			file.breakLine();
			if (Application.getGeneratorProperties().isGenerateAuthorisationComponents()) {
				file.writeln(2, "Authentication authentication = mock(Authentication.class);");
				file.breakLine();
				file.writeln(2, "when(authentication.isAuthenticated()).thenReturn(true);");
				file.breakLine();
				file.writeln(2, "SecurityContext securityContext = mock(SecurityContext.class);");
				file.writeln(2, "when(securityContext.getAuthentication()).thenReturn(authentication);");
				file.writeln(2, "SecurityContextHolder.setContext(securityContext);");
				file.breakLine();
				if (Application.getGeneratorProperties().isGeneratePermissionsAndRoles()) {
					file.writeln(2, "Permission permission = new Permission();");
					file.writeln(2, "permission.setName(\"Admin\");");
					file.writeln(2, "List<GrantedAuthority> authorities = new ArrayList<>();");
					file.writeln(2, "authorities.add(permission);");
					file.breakLine();
					file.writeln(2, "Answer<List<GrantedAuthority>> answer = new Answer<List<GrantedAuthority>>() {");
					file.writeln(3, "public List<GrantedAuthority> answer(InvocationOnMock invocation) throws Throwable {");
					file.writeln(4, "return authorities;");
					file.writeln(3, "}");
					file.writeln(2, "};");
					file.breakLine();
					file.writeln(2, "Mockito.when(authentication.getAuthorities()).thenAnswer(answer);");
					file.breakLine();
				}
			}
			file.writeln(1, "}");
			file.breakLine();

			writeFindAllTest(file, entity);
			writeSearchPostTest(file, entity);
			writeSearchPageablePostTest(file, entity);
			writeDeleteTest(file, entity);
			writeGetByIdTest(file, entity);
			writeInsertTest(file, entity);
			writeUpdateTest(file, entity);

			file.writeln(0, "}");
		}

	}

	private void writeFindAllTest(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		file.writeln(1, "@Test");
		file.writeln(1, "void testFindAll() throws Exception {");
		file.breakLine();
		file.writeln(2, "mockMvc.perform(get(\"/api/" + entity.getName() + "\").contentType(MediaType.APPLICATION_JSON))");
		if (!(entity.getName().equals("permission") || entity.getName().equals("role"))) {
			file.writeln(3, ".andExpect(jsonPath(\"$[0].id\").value(entity1.getId()))");
			for (Attribute attribute : entity.getAttributes()) {
				// TODO Fix dates deserialization in order to enable their usage in test.
				if (isDate(attribute.getType())) continue;
				String upperCaseAttributeName = StringUtils.uppercaseFirst(attribute.getName());
				file.writeln(3, ".andExpect(jsonPath(\"$[0]." + attribute.getName() + "\").value(entity1.get" + upperCaseAttributeName + "()"
						+ (attribute.getType().equals(AttributeType.ENUM) ? ".toString()" : "") + "))");
			}
		}
		file.writeln(3, ".andExpect(jsonPath(\"$.size()\").value(greaterThan(0)))");
		file.writeln(3, ".andExpect(status().isOk());");
		file.writeln(1, "}");
	}

	private void writeSearchPostTest(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		file.writeln(1, "@Test");
		file.writeln(1, "void testSearchPost() throws Exception {");
		file.breakLine();
		writeSearchDTO(file);
		file.writeln(2, "mockMvc.perform(post(\"/api/" + entity.getName() + "/search\").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(searchDTO)))");
		if (!(entity.getName().equals("permission") || entity.getName().equals("role"))) {
			file.writeln(3, ".andExpect(jsonPath(\"$[0].id\").value(entity1.getId()))");
			for (Attribute attribute : entity.getAttributes()) {
				// TODO Fix dates deserialization in order to enable their usage in test.
				if (isDate(attribute.getType())) continue;
				String upperCaseAttributeName = StringUtils.uppercaseFirst(attribute.getName());
				file.writeln(3, ".andExpect(jsonPath(\"$[0]." + attribute.getName() + "\").value(entity1.get" + upperCaseAttributeName + "()"
						+ (attribute.getType().equals(AttributeType.ENUM) ? ".toString()" : "") + "))");
			}
		}
		file.writeln(3, ".andExpect(jsonPath(\"$.size()\").value(greaterThan(0)))");
		file.writeln(3, ".andExpect(status().isOk());");
		file.writeln(1, "}");
	}

	private void writeSearchPageablePostTest(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		file.writeln(1, "@Test");
		file.writeln(1, "void testSearchPageablePost() throws Exception {");
		file.breakLine();
		writeSearchDTO(file);
		file.writeln(2, "mockMvc.perform(post(\"/api/" + entity.getName() + "/searchPageable\").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(searchDTO)))");
		if (!(entity.getName().equals("permission") || entity.getName().equals("role"))) {
			file.writeln(3, ".andExpect(jsonPath(\"$.content[0].id\").value(entity1.getId()))");
			for (Attribute attribute : entity.getAttributes()) {
				// TODO Fix dates deserialization in order to enable their usage in test.
				if (isDate(attribute.getType())) continue;
				String upperCaseAttributeName = StringUtils.uppercaseFirst(attribute.getName());
				file.writeln(3, ".andExpect(jsonPath(\"$.content[0]." + attribute.getName() + "\").value(entity1.get" + upperCaseAttributeName + "()"
						+ (attribute.getType().equals(AttributeType.ENUM) ? ".toString()" : "") + "))");
			}
		}
		file.writeln(3, ".andExpect(jsonPath(\"$.size()\").value(greaterThan(0)))");
		file.writeln(3, ".andExpect(status().isOk());");
		file.writeln(1, "}");
	}

	private void writeSearchDTO(GeneratorOutputFile file) throws IOException {
		file.writeln(2, "SearchDTO searchDTO = new SearchDTO();");
		file.writeln(2, "searchDTO.setPageNumber(0);");
		file.writeln(2, "searchDTO.setPageSize(10);");
		file.writeln(2, "FilterGroup filterGroup = new FilterGroup();");
		file.writeln(2, "filterGroup.setFilters(new ArrayList<SearchFilter>());");
		file.writeln(2, "searchDTO.setFilterGroup(filterGroup);");
		file.breakLine();
	}

	private void writeDeleteTest(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		file.writeln(1, "@Test");
		file.writeln(1, "void testDelete() throws Exception {");
//		file.writeln(2, upperCaseName + " entity = repository.findAll().get(0);");
		file.writeln(2, "mockMvc.perform(delete(\"/api/" + entity.getName() + "/\" + entity1.getId()))");
		file.writeln(3, ".andExpect(status().isNoContent());");
		file.writeln(1, "}");
	}

	private void writeGetByIdTest(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		file.writeln(1, "@Test");
		file.writeln(1, "void testGetById() throws Exception {");
//		file.writeln(2, upperCaseName + " entity = repository.findAll().get(0);");
		file.writeln(2, "mockMvc.perform(get(\"/api/" + entity.getName() + "/\" + entity1.getId()).contentType(MediaType.APPLICATION_JSON))");
		file.writeln(3, ".andExpect(jsonPath(\"$.id\").value(entity1.getId()))");
		for (Attribute attribute : entity.getAttributes()) {
			// TODO Fix dates deserialization in order to enable their usage in test.
			if (isDate(attribute.getType())) continue;
			String upperCaseAttributeName = StringUtils.uppercaseFirst(attribute.getName());
			file.writeln(3, ".andExpect(jsonPath(\"$." + attribute.getName() + "\").value(entity1.get" + upperCaseAttributeName + "()"
					+ (attribute.getType().equals(AttributeType.ENUM) ? ".toString()" : "") + "))");
		}
		file.writeln(3, ".andExpect(status().isOk());");
		file.writeln(1, "}");
	}

	private void writeInsertTest(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		file.writeln(1, "@Test");
		file.writeln(1, "void testInsert() throws Exception {");
		writeMockedEntity(file, entity, "entity3", upperCaseName, "3", true);
		file.writeln(2, "mockMvc.perform(post(\"/api/" + entity.getName() + "\").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(entity3)))");
		for (Attribute attribute : entity.getAttributes()) {
			// TODO Fix dates deserialization in order to enable their usage in test.
			if (isDate(attribute.getType())) continue;
			String upperCaseAttributeName = StringUtils.uppercaseFirst(attribute.getName());
			file.writeln(3, ".andExpect(jsonPath(\"$." + attribute.getName() + "\").value(entity3.get" + upperCaseAttributeName + "()"
					+ (attribute.getType().equals(AttributeType.ENUM) ? ".toString()" : "") + "))");
		}
		file.writeln(3, ".andExpect(status().isOk());");
		file.writeln(1, "}");
	}

	private void writeUpdateTest(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		Attribute attribute = entity.getAttributes().stream().filter(attr -> !isDate(attr.getType())).findFirst().get();
		if(attribute == null) return;
		file.writeln(1, "@Test");
		file.writeln(1, "void testUpdate() throws Exception {");
//		file.writeln(2, upperCaseName + " entity1 = repository.findAll().get(0);");
		String upperCaseAttributeName = StringUtils.uppercaseFirst(attribute.getName());
		file.writeln(2, "entity1.set" + upperCaseAttributeName + "(" + getGenericAttributeValue(attribute, "1") + ");");
		file.writeln(2, "mockMvc.perform(put(\"/api/" + entity.getName() + "\").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(entity1)))");
		file.writeln(3, ".andExpect(jsonPath(\"$." + attribute.getName() + "\").value(entity1.get" + upperCaseAttributeName + "()"
				+ (attribute.getType().equals(AttributeType.ENUM) ? ".toString()" : "") + "))");
		file.writeln(3, ".andExpect(status().isOk());");
		file.writeln(1, "}");
	}

	protected void writeMockedEntity(GeneratorOutputFile file, Entity entity, String objectName, String upperCaseName, String objectNumber, boolean newEntity) throws IOException {
		if (newEntity) {
			file.writeln(2, upperCaseName + " " + objectName + " = new " + upperCaseName + "();");
		} else {
			file.writeln(2, objectName + " = new " + upperCaseName + "();");
		}
//		file.writeln(2, objectName + ".setId(" + objectNumber + "L);");
		for (Attribute attribute : entity.getAttributes()) {
//			if (isDate(attribute.getType())) continue;
			file.writeln(2, objectName + ".set" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + getGenericAttributeValue(attribute, objectNumber) + ");");
		}
	}

	protected void writeMockedEntity(GeneratorOutputFile file, Entity entity, String objectName, String upperCaseName, String objectNumber) throws IOException {
		writeMockedEntity(file, entity, objectName, upperCaseName, objectNumber, false);
	}

	protected String getGenericAttributeValue(Attribute attribute, String objectNumber) {
		return getGenericAttributeValue(attribute, objectNumber, false);
	}

	protected String getGenericAttributeValue(Attribute attribute, String objectNumber, boolean cutForJson) {
		switch (attribute.getType()) {
		case STRING: {
			if (cutForJson) {
				if (attribute.isUnique()) {
					return attribute.getName() + objectNumber + UUID.randomUUID().toString();
				}
				return attribute.getName() + objectNumber;
			} else {
				if (attribute.isUnique()) {
					return "\"" + attribute.getName() + objectNumber + "\" + UUID.randomUUID().toString()";
				}
				return "\"" + attribute.getName() + objectNumber + "\"";
			}
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

	private boolean isDate(AttributeType attributeType) {
		return attributeType == AttributeType.DATE || attributeType == AttributeType.LOCAL_DATE || attributeType == AttributeType.LOCAL_DATE_IME;
	}
}
