package com.generator.writer.java.components.tests.unit;

import java.io.IOException;

import com.generator.model.AppModel;
import com.generator.model.Entity;
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
	            file.writeln(0, "import com.fasterxml.jackson.databind.ObjectMapper;");
	            file.writeln(0, "import org.junit.jupiter.api.BeforeEach;");
	            file.writeln(0, "import org.junit.jupiter.api.Test;");
	            file.writeln(0, "import org.mockito.InjectMocks;");
	            file.writeln(0, "import org.mockito.Mock;");
	            file.writeln(0, "import org.mockito.junit.jupiter.MockitoExtension;");
	            file.writeln(0, "import org.springframework.http.MediaType;");
	            file.writeln(0, "import org.springframework.test.web.servlet.MockMvc;");
	            file.writeln(0, "import org.springframework.test.web.servlet.setup.MockMvcBuilders;");
	            file.writeln(0, "import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;");
	            file.writeln(0, "import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;");
	            file.writeln(0, "import static org.mockito.Mockito.*;");
	            file.writeln(0, "import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;");
	            file.writeln(0, "import org.springframework.boot.test.mock.mockito.MockBean;");
	            file.writeln(0, "import org.springframework.test.context.junit.jupiter.SpringExtension;");
	            file.breakLine();
	            file.writeln(0, "import " + WriterUtils.getImportRepositoryPackageName(false, entity.getName()) + "." + upperCaseName + "Repository;");
				file.writeln(0, "import " + WriterUtils.getImportServicePackageName(false, entity.getName()) + "." + upperCaseName + "Service;");
				file.writeln(0, "import " + WriterUtils.getImportModelPackageName() + "." + upperCaseName + ";");
				file.writeln(0, "import " + WriterUtils.getImportControllerPackageName(false, entity.getName()) + "." + upperCaseName + "Controller;");
				file.writeln(0, "import " + WriterUtils.getSpecificationPackageImportPath() + ".*;");
				for (String enumToImort : entity.getEnumsForImport()) {
					file.writeln(0, "import " + WriterUtils.getImportModelEnumsPackageName() + "." + enumToImort + ";");
				}
	            file.writeln(0, "import java.util.Optional;");
	            file.writeln(0, "import java.util.List;");
	            file.breakLine();
	            
	            // Annotations and Class Declaration
	            file.writeln(0, "@WebMvcTest");
	            file.writeln(0, "class " + upperCaseName + "ControllerUnitTest {");
	            file.breakLine();

	            // MockMvc and Service Mocks
	            file.writeln(1, "private MockMvc mockMvc;");
	            file.breakLine();

	            // Inject the controller we're testing
	            file.writeln(1, "@InjectMocks");
	            file.writeln(1, "private " + upperCaseName + "Controller controller;");
	            file.breakLine();

	            // Mocking the service layer
	            file.writeln(1, "@Mock");
	            file.writeln(1, "private " + upperCaseName + "Service service;");
	            file.breakLine();

	            // ObjectMapper for JSON handling
	            file.writeln(1, "private ObjectMapper objectMapper = new ObjectMapper();");
	            file.breakLine();

	            // Setup method to initialize MockMvc
	            file.writeln(1, "@BeforeEach");
	            file.writeln(1, "void setUp() {");
	            file.writeln(2, "mockMvc = MockMvcBuilders.standaloneSetup(controller).build();");
	            file.writeln(1, "}");
	            file.breakLine();

	            // Write tests for each entity (FindAll, Insert, Update, Delete, GetById)
	            writeFindAllTest(file, entity);
	            writeGetByIdTest(file, entity);
	            writeInsertTest(file, entity);
	            writeUpdateTest(file, entity);
	            writeDeleteTest(file, entity);

	            // Closing class bracket
	            file.writeln(0, "}");
	        }
	    }

	    // Writes a test for the findAll() endpoint
	    private void writeFindAllTest(GeneratorOutputFile file, Entity entity) throws IOException {
	        String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
	        file.writeln(1, "@Test");
	        file.writeln(1, "void testFindAll() throws Exception {");
	        file.breakLine();
	        
	        file.writeln(2, "List<" + upperCaseName + "> mockList = List.of(new " + upperCaseName + "());");
	        file.writeln(2, "when(service.findAll()).thenReturn(mockList);");
	        file.breakLine();

	        file.writeln(2, "mockMvc.perform(get(\"/api/" + entity.getName() + "/\")");
	        file.writeln(3, ".contentType(MediaType.APPLICATION_JSON))");
	        file.writeln(3, ".andExpect(status().isOk());");
	        file.breakLine();

	        file.writeln(2, "verify(service, times(1)).findAll();");
	        file.writeln(1, "}");
	    }

	    // Writes a test for the getById() endpoint
	    private void writeGetByIdTest(GeneratorOutputFile file, Entity entity) throws IOException {
	        String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
	        file.writeln(1, "@Test");
	        file.writeln(1, "void testGetById() throws Exception {");
	        file.breakLine();
	        
	        file.writeln(2, upperCaseName + " mock" + upperCaseName + " = new " + upperCaseName + "();");
	        file.writeln(2, "when(service.findById(1L)).thenReturn(Optional.of(mock" + upperCaseName + "));");
	        file.breakLine();

	        file.writeln(2, "mockMvc.perform(get(\"/api/" + entity.getName() + "/1\")");
	        file.writeln(3, ".contentType(MediaType.APPLICATION_JSON))");
	        file.writeln(3, ".andExpect(status().isOk());");
	        file.breakLine();

	        file.writeln(2, "verify(service, times(1)).findById(1L);");
	        file.writeln(1, "}");
	    }

	    // Writes a test for the insert (POST) endpoint
	    private void writeInsertTest(GeneratorOutputFile file, Entity entity) throws IOException {
	        String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
	        file.writeln(1, "@Test");
	        file.writeln(1, "void testInsert() throws Exception {");
	        file.breakLine();
	        
	        file.writeln(2, upperCaseName + " new" + upperCaseName + " = new " + upperCaseName + "();");
	        file.writeln(2, "when(service.save(any(" + upperCaseName + ".class))).thenReturn(new" + upperCaseName + ");");
	        file.breakLine();

	        file.writeln(2, "mockMvc.perform(post(\"/api/" + entity.getName() + "\")");
	        file.writeln(3, ".contentType(MediaType.APPLICATION_JSON)");
	        file.writeln(3, ".content(objectMapper.writeValueAsString(new" + upperCaseName + ")))");
	        file.writeln(3, ".andExpect(status().isOk());");
	        file.breakLine();

	        file.writeln(2, "verify(service, times(1)).save(any(" + upperCaseName + ".class));");
	        file.writeln(1, "}");
	    }

	    // Writes a test for the update (PUT) endpoint
	    private void writeUpdateTest(GeneratorOutputFile file, Entity entity) throws IOException {
	        String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
	        file.writeln(1, "@Test");
	        file.writeln(1, "void testUpdate() throws Exception {");
	        file.breakLine();
	        
	        file.writeln(2, upperCaseName + " updated" + upperCaseName + " = new " + upperCaseName + "();");
	        file.writeln(2, "when(service.update(any(" + upperCaseName + ".class))).thenReturn(updated" + upperCaseName + ");");
	        file.breakLine();

	        file.writeln(2, "mockMvc.perform(put(\"/api/" + entity.getName() + "/1\")");
	        file.writeln(3, ".contentType(MediaType.APPLICATION_JSON)");
	        file.writeln(3, ".content(objectMapper.writeValueAsString(updated" + upperCaseName + ")))");
	        file.writeln(3, ".andExpect(status().isOk());");
	        file.breakLine();

	        file.writeln(2, "verify(service, times(1)).update(any(" + upperCaseName + ".class));");
	        file.writeln(1, "}");
	    }

	    // Writes a test for the delete (DELETE) endpoint
	    private void writeDeleteTest(GeneratorOutputFile file, Entity entity) throws IOException {
	        String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
	        file.writeln(1, "@Test");
	        file.writeln(1, "void testDelete() throws Exception {");
	        file.breakLine();
	        
	        file.writeln(2, "doNothing().when(service).deleteById(1L);");
	        file.breakLine();

	        file.writeln(2, "mockMvc.perform(delete(\"/api/" + entity.getName() + "/1\")");
	        file.writeln(3, ".contentType(MediaType.APPLICATION_JSON))");
	        file.writeln(3, ".andExpect(status().isNoContent());");
	        file.breakLine();

	        file.writeln(2, "verify(service, times(1)).deleteById(1L);");
	        file.writeln(1, "}");
	    }
	}