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

public class JavaGenericServiceWriterTest {

	@Mock
	private GeneratorOutputFile mockGeneratorOutputFile;

	@InjectMocks
	private JavaGenericServiceWriter genericServiceWriter;

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
        genericServiceWriter.create();

        // Then
        verify(mockGeneratorOutputFile).writeln(0, "package com.generator.service;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.data.domain.Page;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.data.domain.Pageable;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.data.jpa.domain.Specification;");
        verify(mockGeneratorOutputFile).writeln(0, "import com.generator.repository.GenericRepository;");
        verify(mockGeneratorOutputFile).writeln(0, "import com.generator.exception.ApplicationException;");
        verify(mockGeneratorOutputFile).writeln(0, "import java.util.*;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "public abstract class GenericService<T> {");
        verify(mockGeneratorOutputFile).writeln(1, "private final GenericRepository<T> repository;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(1, "public GenericService(GenericRepository<T> repository) {");
        verify(mockGeneratorOutputFile).writeln(2, "this.repository = repository;");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(1, "public List<T> findAll() {");
        verify(mockGeneratorOutputFile).writeln(2, "return repository.findAll();");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(1, "public Page<T> findAll(Pageable pageable) {");
        verify(mockGeneratorOutputFile).writeln(2, "return repository.findAll(pageable);");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(1, "public List<T> findAll(Specification<T> specification) {");
        verify(mockGeneratorOutputFile).writeln(2, "return repository.findAll(specification);");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(1, "public Page<T> findAll(Specification<T> specification, Pageable pageable) {");
        verify(mockGeneratorOutputFile).writeln(2, "return repository.findAll(specification, pageable);");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(1, "public void deleteById(Long id) {");
        verify(mockGeneratorOutputFile).writeln(2, "repository.deleteById(id);");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(1, "public abstract T insert(T object) throws ApplicationException;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(1, "public abstract T update(T object) throws ApplicationException;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(1, "public T save(T object) {");
        verify(mockGeneratorOutputFile).writeln(2, "return repository.save(object);");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(1, "public Optional<T> findById(Long id) {");
        verify(mockGeneratorOutputFile).writeln(2, "return repository.findById(id);");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(1, "public abstract Class<T> getEntityClass();");
        verify(mockGeneratorOutputFile).writeln(0, "}");
        verify(mockGeneratorOutputFile).close(); // Ensure the file is closed
    }
}
