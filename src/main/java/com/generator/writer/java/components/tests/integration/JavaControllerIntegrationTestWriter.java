package com.generator.writer.java.components.tests.integration;

import java.io.IOException;

import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.EnumModel;
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
			file.writeln(0, "import com.fasterxml.jackson.databind.ObjectMapper;");
			file.writeln(0, "import org.junit.jupiter.api.BeforeEach;");
			file.writeln(0, "import org.junit.jupiter.api.Test;");
			file.writeln(0, "import org.springframework.beans.factory.annotation.Autowired;");
			file.writeln(0, "import org.springframework.boot.test.context.SpringBootTest;");
			file.writeln(0, "import org.springframework.boot.test.web.client.TestRestTemplate;");
			file.writeln(0, "import org.springframework.boot.test.web.server.LocalServerPort;");
			file.writeln(0, "import org.springframework.http.HttpEntity;");
			file.writeln(0, "import org.springframework.http.HttpMethod;");
			file.writeln(0, "import org.springframework.http.HttpStatus;");
			file.writeln(0, "import org.springframework.http.MediaType;");
			file.writeln(0, "import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;");
			file.writeln(0, "import org.springframework.test.web.servlet.MockMvc;");
			file.writeln(0, "import org.springframework.http.ResponseEntity;");
			file.writeln(0, "import org.springframework.test.annotation.DirtiesContext;");
			file.writeln(0, "import org.springframework.web.util.UriComponentsBuilder;");
			file.writeln(0, "");
			file.writeln(0, "import " + WriterUtils.getImportRepositoryPackageName(false, entity.getName()) + "." + upperCaseName + "Repository;");
			file.writeln(0, "import " + WriterUtils.getImportServicePackageName(false, entity.getName()) + "." + upperCaseName + "Service;");
			file.writeln(0, "import " + WriterUtils.getImportModelPackageName() + "." + upperCaseName + ";");
			file.writeln(0, "import " + WriterUtils.getSpecificationPackageImportPath() + ".*;");
			for (String enumToImort : entity.getEnumsForImport()) {
				file.writeln(0, "import " + WriterUtils.getImportModelEnumsPackageName() + "." + enumToImort + ";");
			}
			file.writeln(0, "");
			file.writeln(0, "import java.util.*;");
			file.writeln(0, "import java.time.*;");
			file.writeln(0, "");
			file.writeln(0, "import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;");
			file.writeln(0, "import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;");
			file.writeln(0, "");
			file.writeln(0, "@SpringBootTest");
			file.writeln(0, "@AutoConfigureMockMvc");
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
			file.writeln(1, "@BeforeEach");
			file.writeln(1, "void setUp() {");
			file.writeln(2, "repository.deleteAll();");
			writeMockedEntity(file, entity, "entity1", upperCaseName, "1");
			file.writeln(2, "repository.save(entity1);");
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
		Attribute attribute = entity.getAttributes().iterator().next();
		file.writeln(2, "mockMvc.perform(get(\"/api/" + entity.getName() + "/\").contentType(MediaType.APPLICATION_JSON)).andExpect(status().isOk()).andExpect(jsonPath(\"$[0]." + attribute.getName()
				+ "\").value(\"" + getGenericAttributeValue(attribute, "1", true) + "\"));");
		file.writeln(1, "}");
	}

	private void writeSearchPostTest(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		file.writeln(1, "@Test");
		file.writeln(1, "void testSearchPost() throws Exception {");
		file.breakLine();
		file.writeln(2, "SearchDTO searchDTO = new SearchDTO();");
		file.writeln(2, "searchDTO.setPageNumber(0);");
		file.writeln(2, "searchDTO.setPageSize(10);");
		file.writeln(2, "searchDTO.setFilterGroup(new FilterGroup());");
		file.breakLine();
		Attribute attribute = entity.getAttributes().iterator().next();
		file.writeln(2,
				"mockMvc.perform(post(\"/api/" + entity.getName()
						+ "/search\").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(searchDTO))).andExpect(status().isOk()).andExpect(jsonPath(\"$[0]."
						+ attribute.getName() + "\").value(\"" + getGenericAttributeValue(attribute, "1", true) + "\"));");
		file.writeln(1, "}");
	}

	private void writeSearchPageablePostTest(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		file.writeln(1, "@Test");
		file.writeln(1, "void testSearchPageablePost() throws Exception {");
		file.breakLine();
		file.writeln(2, "SearchDTO searchDTO = new SearchDTO();");
		file.writeln(2, "searchDTO.setPageNumber(0);");
		file.writeln(2, "searchDTO.setPageSize(10);");
		file.writeln(2, "searchDTO.setFilterGroup(new FilterGroup());");
		file.breakLine();
		Attribute attribute = entity.getAttributes().iterator().next();
		file.writeln(2,
				"mockMvc.perform(post(\"/api/" + entity.getName()
						+ "/searchPageable\").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(searchDTO))).andExpect(status().isOk()).andExpect(jsonPath(\"$[0]."
						+ attribute.getName() + "\").value(\"" + getGenericAttributeValue(attribute, "1", true) + "\"));");
		file.writeln(1, "}");
	}

	private void writeDeleteTest(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		file.writeln(1, "@Test");
		file.writeln(1, "void testDeleteUser() throws Exception {");
		file.writeln(2, upperCaseName + " entity = repository.findAll().get(0);");
		file.writeln(2, "mockMvc.perform(delete(\"/api/" + entity.getName() + "/delete/\" + entity.getId())).andExpect(status().isNoContent());");
		file.writeln(1, "}");
	}

	private void writeGetByIdTest(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		file.writeln(1, "@Test");
		file.writeln(1, "void testGetById() throws Exception {");
		file.writeln(2, upperCaseName + " entity = repository.findAll().get(0);");
		Attribute attribute = entity.getAttributes().iterator().next();
		file.writeln(2, "mockMvc.perform(get(\"/api/" + entity.getName() + "/\" + entity.getId()).contentType(MediaType.APPLICATION_JSON)).andExpect(jsonPath(\"$." + attribute.getName()
				+ "\").value(\"" + getGenericAttributeValue(attribute, "1", true) + "\"));");
		file.writeln(1, "}");
	}

	private void writeInsertTest(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		file.writeln(1, "@Test");
		file.writeln(1, "void testInsertUser() throws Exception {");
		writeMockedEntity(file, entity, "entity1", upperCaseName, "1");
		Attribute attribute = entity.getAttributes().iterator().next();
		file.writeln(2, "mockMvc.perform(post(\"/api/" + entity.getName() + "\").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(entity1))).andExpect(status().isOk())"
				+ ".andExpect(jsonPath(\"$." + attribute.getName() + "\").value(\"" + getGenericAttributeValue(attribute, "1", true) + "\"));");
		file.writeln(1, "}");
	}

	private void writeUpdateTest(GeneratorOutputFile file, Entity entity) throws IOException {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		file.writeln(1, "@Test");
		file.writeln(1, "void testUpdateUser() throws Exception {");
		file.writeln(2, upperCaseName + " entity1 = repository.findAll().get(0);");
		Attribute attribute = entity.getAttributes().iterator().next();
		file.writeln(2, "entity1.set" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + getGenericAttributeValue(attribute, "1") + ");");
		file.writeln(2,
				"mockMvc.perform(put(\"/api/" + entity.getName() + "/update\").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(entity1))).andExpect(status().isOk())"
						+ ".andExpect(jsonPath(\"$." + attribute.getName() + "\").value(\"" + getGenericAttributeValue(attribute, "1", true) + "\"));");
		file.writeln(1, "}");
	}

	protected void writeMockedEntity(GeneratorOutputFile file, Entity entity, String objectName, String upperCaseName, String objectNumber) throws IOException {
		file.writeln(2, upperCaseName + " " + objectName + " = new " + upperCaseName + "();");
		for (Attribute attribute : entity.getAttributes()) {
			file.writeln(2, objectName + ".set" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + getGenericAttributeValue(attribute, objectNumber) + ");");
		}
	}
	
	protected String getGenericAttributeValue(Attribute attribute, String objectNumber) {
		return getGenericAttributeValue(attribute, objectNumber, false);
	}
	
	protected String getGenericAttributeValue(Attribute attribute, String objectNumber, boolean cutForJson) {
		switch (attribute.getType()) {
		case STRING: {
			if(cutForJson) {
				return attribute.getName() + objectNumber;
			}else {
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
}
