package com.generator.writer.java.components.tests.unit;

import java.io.IOException;

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

public class JavaControllerUnitTestWriter implements DefaultWriter {
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
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getUnitTestControllerPackagePath(entity.getName()), upperCaseName + "ControllerUnitTest.java", true)) {

			file.writeln(0, "package " + WriterUtils.getUnitTestControllerPackageImport(entity.getName()) + ";");
			file.breakLine();
			file.writeln(0, "import static org.junit.jupiter.api.Assertions.assertThrows;");
			file.writeln(0, "import static org.mockito.Mockito.mock;");
			file.writeln(0, "import static org.mockito.Mockito.times;");
			file.writeln(0, "import static org.mockito.Mockito.verify;");
			file.writeln(0, "import static org.mockito.Mockito.when;");
			file.writeln(0, "import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;");
			file.writeln(0, "import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;");
			file.writeln(0, "import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;");
			file.writeln(0, "import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;");
			file.writeln(0, "import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;");
			file.writeln(0, "import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;");
			file.breakLine();
			file.writeln(0, "import org.junit.jupiter.api.BeforeEach;");
			file.writeln(0, "import org.junit.jupiter.api.Test;");
			file.writeln(0, "import org.mockito.Mockito;");
			file.writeln(0, "import org.mockito.MockitoAnnotations;");
			file.writeln(0, "import org.mockito.invocation.InvocationOnMock;");
			file.writeln(0, "import org.mockito.stubbing.Answer;");
			file.writeln(0, "import org.springframework.beans.factory.annotation.Autowired;");
			file.writeln(0, "import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;");
			file.writeln(0, "import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;");
			file.writeln(0, "import org.springframework.boot.test.mock.mockito.MockBean;");
			file.writeln(0, "import org.springframework.http.MediaType;");
			file.writeln(0, "import org.springframework.security.core.Authentication;");
			file.writeln(0, "import org.springframework.security.core.GrantedAuthority;");
			file.writeln(0, "import org.springframework.security.core.context.SecurityContext;");
			file.writeln(0, "import org.springframework.security.core.context.SecurityContextHolder;");
			file.writeln(0, "import org.springframework.test.web.servlet.MockMvc;");
			file.writeln(0, "import org.springframework.test.web.servlet.setup.MockMvcBuilders;");
			file.writeln(0, "import com.fasterxml.jackson.databind.ObjectMapper;");
			file.breakLine();
			file.writeln(0, "import " + WriterUtils.getImportServicePackageName(false, entity.getName()) + "." + upperCaseName + "Service;");
			file.writeln(0, "import " + WriterUtils.getImportModelPackageName() + "." + upperCaseName + ";");
			file.writeln(0, "import " + WriterUtils.getImportControllerPackageName(false, entity.getName()) + "." + upperCaseName + "Controller;");
			file.writeln(0, "import " + WriterUtils.getSpecificationPackageImportPath() + ".*;");
			file.writeln(0, "import " + WriterUtils.getSecurityPackageImportPath() + ".*;");
			if (Application.getGeneratorProperties().isGeneratePermissionsAndRoles()) {
				file.writeln(0, "import " + WriterUtils.getImportModelPackageName() + ".Permission;");
			}
			for (String enumToImort : entity.getEnumsForImport()) {
				file.writeln(0, "import " + WriterUtils.getImportModelEnumsPackageName() + "." + enumToImort + ";");
			}
			file.writeln(0, "import java.util.*;");
			file.writeln(0, "import java.time.*;");
			file.breakLine();
			file.writeln(0, "@WebMvcTest(" + upperCaseName + "Controller.class)");
			file.writeln(0, "@AutoConfigureMockMvc");
			file.writeln(0, "class " + upperCaseName + "ControllerUnitTest {");
			file.breakLine();
			file.writeln(1, "private " + upperCaseName + "Controller controller;");

			file.writeln(1, "@Autowired");
			file.writeln(1, "private MockMvc mockMvc;");
			file.breakLine();

			file.writeln(1, "@MockBean");
			file.writeln(1, "private " + upperCaseName + "Service service;");
			file.breakLine();
			if (Application.getGeneratorProperties().isGenerateAuthorisationComponents()) {
				file.writeln(1, "@MockBean");
				file.writeln(1, "private JwtFilter jwtFilter;");
				file.breakLine();
				file.writeln(1, "@MockBean");
				file.writeln(1, "private JwtUtil jwtUtil;");
				file.breakLine();
//				file.writeln(1, "@MockBean");
//				file.writeln(1, "private SecurityUtils securityUtils;");
//				file.breakLine();
			}
			file.writeln(1, "private " + upperCaseName + " entity1;");
			file.breakLine();
			/*
			 * file.writeln(1, "@MockBean");
			 * file.writeln(1, "private ObjectMapper objectMapper;");
			 * file.breakLine();
			 */

			// Setup method to initialize MockMvc
			file.writeln(1, "@BeforeEach");
			file.writeln(1, "void setUp() {");
			file.writeln(2, "MockitoAnnotations.openMocks(this);");
			file.writeln(2, "controller = new " + upperCaseName + "Controller(service);");
			file.writeln(2, "mockMvc = MockMvcBuilders.standaloneSetup(controller).build();");
			file.breakLine();
			writeMockedEntity(file, entity, "entity1", upperCaseName, "1");
			file.writeln(2, "List<" + upperCaseName + "> entities = List.of(entity1);");
			file.writeln(2, "when(service.findAll()).thenReturn(entities);");
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
					file.writeln(2, "");
					file.breakLine();
					file.writeln(2, "Mockito.when(authentication.getAuthorities()).thenAnswer(answer);");
					file.breakLine();
				}
			}
			file.writeln(1, "}");
			file.breakLine();

			writeFindAllTest(file, entity);
			writeGetByIdTest(file, entity);
			writeInsertTest(file, entity);
			writeUpdateTest(file, entity);
			writeDeleteTest(file, entity);

			file.writeln(0, "}");
		}
	}

	private void writeFindAllTest(GeneratorOutputFile file, Entity entity) throws IOException {
		file.writeln(1, "@Test");
		file.writeln(1, "void testFindAll() throws Exception {");
		file.breakLine();
		file.writeln(2, "mockMvc.perform(get(\"/api/" + entity.getName() + "\").contentType(MediaType.APPLICATION_JSON))");
		file.writeln(3, ".andExpect(jsonPath(\"$[0].id\").value(entity1.getId()))");
		for (Attribute attribute : entity.getAttributes()) {
			// TODO Fix dates deserialization in order to enable their usage in test.
			if (isDate(attribute.getType())) continue;
			String upperCaseAttributeName = StringUtils.uppercaseFirst(attribute.getName());
			file.writeln(3, ".andExpect(jsonPath(\"$[0]." + attribute.getName() + "\").value(entity1.get" + upperCaseAttributeName + "()"
					+ (attribute.getType().equals(AttributeType.ENUM) ? ".toString()" : "") + "))");
		}
		file.writeln(3, ".andExpect(status().isOk());");
		file.breakLine();
		file.writeln(2, "verify(service, times(1)).findAll();");
		file.writeln(1, "}");
	}

	private void writeGetByIdTest(GeneratorOutputFile file, Entity entity) throws IOException {
		file.writeln(1, "@Test");
		file.writeln(1, "void testGetByIdSuccess() throws Exception {");
		file.breakLine();
		file.writeln(2, "when(service.findById(1L)).thenReturn(Optional.of(entity1));");
		file.breakLine();
		file.writeln(2, "mockMvc.perform(get(\"/api/" + entity.getName() + "/1\").contentType(MediaType.APPLICATION_JSON))");
		file.writeln(3, ".andExpect(jsonPath(\"$.id\").value(entity1.getId()))");
		for (Attribute attribute : entity.getAttributes()) {
			// TODO Fix dates deserialization in order to enable their usage in test.
			if (isDate(attribute.getType())) continue;
			String upperCaseAttributeName = StringUtils.uppercaseFirst(attribute.getName());
			file.writeln(3, ".andExpect(jsonPath(\"$." + attribute.getName() + "\").value(entity1.get" + upperCaseAttributeName + "()"
					+ (attribute.getType().equals(AttributeType.ENUM) ? ".toString()" : "") + "))");
		}
		file.writeln(3, ".andExpect(status().isOk());");
		file.breakLine();
		file.writeln(2, "verify(service, times(1)).findById(1L);");
		file.writeln(1, "}");

		file.writeln(1, "@Test");
		file.writeln(1, "void testGetByIdNotFound() throws Exception {");
		file.breakLine();
		file.writeln(2, "when(service.findById(1L)).thenReturn(Optional.empty());");
		file.breakLine();
		file.writeln(2, "assertThrows(Exception.class, () -> {");
		file.writeln(3, "mockMvc.perform(get(\"/api/" + entity.getName() + "/1\"));");
		file.writeln(2, "});");
		file.breakLine();
		file.writeln(2, "verify(service, times(1)).findById(1L);");
		file.writeln(1, "}");
	}

	private void writeInsertTest(GeneratorOutputFile file, Entity entity) throws IOException {
		file.writeln(1, "@Test");
		file.writeln(1, "void testInsert() throws Exception {");
		file.breakLine();
		file.writeln(2, "when(service.insert(entity1)).thenReturn(entity1);");
		file.breakLine();
		file.writeln(2,
				"mockMvc.perform(post(\"/api/" + entity.getName() + "\").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().findAndRegisterModules().writeValueAsString(entity1)))");
		file.writeln(3, ".andExpect(jsonPath(\"$.id\").value(entity1.getId()))");
		for (Attribute attribute : entity.getAttributes()) {
			// TODO Fix dates deserialization in order to enable their usage in test.
			if (isDate(attribute.getType())) continue;
			String upperCaseAttributeName = StringUtils.uppercaseFirst(attribute.getName());
			file.writeln(3, ".andExpect(jsonPath(\"$." + attribute.getName() + "\").value(entity1.get" + upperCaseAttributeName + "()"
					+ (attribute.getType().equals(AttributeType.ENUM) ? ".toString()" : "") + "))");
		}
		file.writeln(3, ".andExpect(status().isOk());");
		file.breakLine();
		file.writeln(2, "verify(service, times(1)).insert(entity1);");
		file.writeln(1, "}");
	}

	private void writeUpdateTest(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		file.writeln(1, "@Test");
		file.writeln(1, "void testUpdateSuccess() throws Exception {");
		file.breakLine();
		writeMockedEntity(file, entity, "updated" + upperCaseName, upperCaseName, "1", true);
		file.writeln(2, "when(service.update(entity1)).thenReturn(updated" + upperCaseName + ");");
		file.breakLine();

		file.writeln(2,
				"mockMvc.perform(put(\"/api/" + entity.getName() + "\").contentType(MediaType.APPLICATION_JSON).content(new ObjectMapper().findAndRegisterModules().writeValueAsString(entity1)))");
		file.writeln(3, ".andExpect(jsonPath(\"$.id\").value(updated" + upperCaseName + ".getId()))");
		for (Attribute attribute : entity.getAttributes()) {
			// TODO Fix dates deserialization in order to enable their usage in test.
			if (isDate(attribute.getType())) continue;
			String upperCaseAttributeName = StringUtils.uppercaseFirst(attribute.getName());
			file.writeln(3, ".andExpect(jsonPath(\"$." + attribute.getName() + "\").value(updated" + upperCaseName + ".get" + upperCaseAttributeName + "()"
					+ (attribute.getType().equals(AttributeType.ENUM) ? ".toString()" : "") + "))");
		}
		file.writeln(3, ".andExpect(status().isOk());");
		file.breakLine();

		file.writeln(2, "verify(service, times(1)).update(entity1);");
		file.writeln(1, "}");
	}

	private void writeDeleteTest(GeneratorOutputFile file, Entity entity) throws IOException {
		file.writeln(1, "@Test");
		file.writeln(1, "void testDeleteSuccess() throws Exception {");
		file.breakLine();
		file.writeln(2, "when(service.findById(1L)).thenReturn(Optional.of(entity1));");
		file.breakLine();
		file.writeln(2, "mockMvc.perform(delete(\"/api/" + entity.getName() + "/1\")");
		file.writeln(3, ".contentType(MediaType.APPLICATION_JSON))");
		file.writeln(3, ".andExpect(status().isNoContent());");
		file.breakLine();
		file.writeln(2, "verify(service, times(1)).deleteById(1L);");
		file.writeln(1, "}");
		file.breakLine();
		file.writeln(1, "@Test");
		file.writeln(1, "void testDeleteNotFound() throws Exception {");
		file.breakLine();
		file.writeln(2, "when(service.findById(1L)).thenReturn(Optional.empty());");
		file.breakLine();
		file.writeln(2, "assertThrows(Exception.class, () -> {");
		file.writeln(3, "mockMvc.perform(delete(\"/api/" + entity.getName() + "/1\"));");
		file.writeln(2, "});");
		file.breakLine();
		file.writeln(2, "verify(service, times(0)).deleteById(1L);");
		file.writeln(1, "}");
	}

	protected void writeMockedEntity(GeneratorOutputFile file, Entity entity, String objectName, String upperCaseName, String objectNumber, boolean newEntity) throws IOException {
		if (newEntity) {
			file.writeln(2, upperCaseName + " " + objectName + " = new " + upperCaseName + "();");
		} else {
			file.writeln(2, objectName + " = new " + upperCaseName + "();");
		}
		file.writeln(2, objectName + ".setId(" + objectNumber + "L);");
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
				return attribute.getName() + objectNumber;
			} else {
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