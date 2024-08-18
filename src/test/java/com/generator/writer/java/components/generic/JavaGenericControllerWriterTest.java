package com.generator.writer.java.components.generic;

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

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaGenericControllerWriterTest {

	@Mock
	private GeneratorOutputFile mockGeneratorOutputFile;

	@InjectMocks
	private JavaGenericControllerWriter genericControllerWriter;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		// Mock static methods if needed
	}

	@Test
    public void testCreate() throws Exception {
        // Given
        when(WriterUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(mockGeneratorOutputFile);
        when(mockGeneratorOutputFile.hasAlreadyExisted()).thenReturn(false);
        doNothing().when(mockGeneratorOutputFile).writeln(anyInt(), anyString());

        // When
        genericControllerWriter.create();

        // Then
        verify(mockGeneratorOutputFile).writeln(0, "package com.generator.controller;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.web.bind.annotation.*;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.http.*;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.data.domain.*;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.data.jpa.domain.*;");
        verify(mockGeneratorOutputFile).writeln(0, "import java.util.*;");
        verify(mockGeneratorOutputFile).writeln(0, "import jakarta.validation.constraints.Max;");
        verify(mockGeneratorOutputFile).writeln(0, "import jakarta.validation.constraints.Min;");
        verify(mockGeneratorOutputFile).writeln(0, "import jakarta.validation.Valid;");
        verify(mockGeneratorOutputFile).writeln(0, "import jakarta.persistence.EntityNotFoundException;");
        verify(mockGeneratorOutputFile).writeln(0, "import com.generator.exception.ApplicationException;");
        verify(mockGeneratorOutputFile).writeln(0, "import com.generator.service.GenericService;");
        verify(mockGeneratorOutputFile).writeln(0, "import com.generator.utils.ApiUtil;");
        verify(mockGeneratorOutputFile).writeln(0, "import com.generator.specification.SpecificationBasic;");
        verify(mockGeneratorOutputFile).writeln(0, "import com.generator.specification.SearchDTO;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "public class GenericController<T> {");
        verify(mockGeneratorOutputFile).writeln(1, "private final GenericService<T> service;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(1, "public GenericController(GenericService<T> service) {");
        verify(mockGeneratorOutputFile).writeln(2, "this.service = service;");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");

        verify(mockGeneratorOutputFile).writeln(1, "@GetMapping(value = \"\", produces = MediaType.APPLICATION_JSON_VALUE)");
        verify(mockGeneratorOutputFile).writeln(1, "public ResponseEntity<?> findAll() {");
        verify(mockGeneratorOutputFile).writeln(2, "return ResponseEntity.ok(service.findAll());");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");

        verify(mockGeneratorOutputFile).writeln(1, "@PostMapping(value = \"/search\", produces = MediaType.APPLICATION_JSON_VALUE)");
        verify(mockGeneratorOutputFile).writeln(1, "public ResponseEntity<?> searchPost(@RequestBody SearchDTO request) throws ApplicationException {");
        verify(mockGeneratorOutputFile).writeln(2, "SpecificationBasic<T> specification = request.buildSpecification(service.getEntityClass());");
        verify(mockGeneratorOutputFile).writeln(2, "return ResponseEntity.ok(service.findAll(specification.getSpecification()));");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");

        verify(mockGeneratorOutputFile).writeln(1, "@PostMapping(value = \"/searchPageable\", produces = MediaType.APPLICATION_JSON_VALUE)");
        verify(mockGeneratorOutputFile).writeln(1, "public ResponseEntity<?> searchPageablePost(@RequestBody SearchDTO request) throws ApplicationException {");
        verify(mockGeneratorOutputFile).writeln(2, "SpecificationBasic<T> specification = request.buildSpecification(service.getEntityClass());");
        verify(mockGeneratorOutputFile).writeln(2, "return ResponseEntity.ok(service.findAll(specification.getSpecification(), request.createPageable()));");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");

        verify(mockGeneratorOutputFile).writeln(1, "@DeleteMapping(value = \"/delete/{id}\")");
        verify(mockGeneratorOutputFile).writeln(1, "public ResponseEntity<?> delete(@PathVariable(\"id\") Long id) {");
        verify(mockGeneratorOutputFile).writeln(2, "Optional<T> optional = service.findById(id);");
        verify(mockGeneratorOutputFile).writeln(2, "if (optional.isPresent()) {");
        verify(mockGeneratorOutputFile).writeln(3, "service.deleteById(id);");
        verify(mockGeneratorOutputFile).writeln(3, "return ResponseEntity.noContent().build();");
        verify(mockGeneratorOutputFile).writeln(2, "}");
        verify(mockGeneratorOutputFile).writeln(2, "throw new EntityNotFoundException(\"id:\" + id.toString());");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");

        verify(mockGeneratorOutputFile).writeln(1, "@GetMapping(value = \"/{id}\", produces = MediaType.APPLICATION_JSON_VALUE)");
        verify(mockGeneratorOutputFile).writeln(1, "public ResponseEntity<?> getById(@PathVariable(\"id\") Long id) {");
        verify(mockGeneratorOutputFile).writeln(2, "Optional<T> optional = service.findById(id);");
        verify(mockGeneratorOutputFile).writeln(2, "if (optional.isPresent()) {");
        verify(mockGeneratorOutputFile).writeln(3, "return ResponseEntity.ok(optional.get());");
        verify(mockGeneratorOutputFile).writeln(2, "}");
        verify(mockGeneratorOutputFile).writeln(2, "throw new EntityNotFoundException(\"id:\" + id.toString());");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");

        verify(mockGeneratorOutputFile).writeln(1, "@PutMapping(value = \"/update\", produces = MediaType.APPLICATION_JSON_VALUE)");
        verify(mockGeneratorOutputFile).writeln(1, "public ResponseEntity<?> update(@Valid @RequestBody T object) throws ApplicationException {");
        verify(mockGeneratorOutputFile).writeln(2, "return ResponseEntity.ok(service.update(object));");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");

        verify(mockGeneratorOutputFile).writeln(1, "@PostMapping(value = \"\", produces = MediaType.APPLICATION_JSON_VALUE)");
        verify(mockGeneratorOutputFile).writeln(1, "public ResponseEntity<?> insert(@Valid @RequestBody T object) throws ApplicationException {");
        verify(mockGeneratorOutputFile).writeln(2, "return ResponseEntity.ok(service.insert(object));");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");

        verify(mockGeneratorOutputFile).writeln(0, "}");
        verify(mockGeneratorOutputFile).close(); // Ensure the file is closed
    }
}
