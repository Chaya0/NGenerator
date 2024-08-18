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

public class JavaGenericRepositoryWriterTest {

	@Mock
	private GeneratorOutputFile mockGeneratorOutputFile;

	@InjectMocks
	private JavaGenericRepositoryWriter genericRepositoryWriter;

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
        genericRepositoryWriter.create();

        // Then
        verify(mockGeneratorOutputFile).writeln(0, "package com.generator.repository;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.data.jpa.domain.Specification;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.data.jpa.repository.JpaRepository;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.data.repository.NoRepositoryBean;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.data.domain.Pageable;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.data.domain.Page;");
        verify(mockGeneratorOutputFile).writeln(0, "import java.util.List;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "@NoRepositoryBean");
        verify(mockGeneratorOutputFile).writeln(0, "public interface GenericRepository<T> extends JpaRepository<T, Long> {");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(1, "Page<T> findAll(Specification<T> spec, Pageable pageSort);");
        verify(mockGeneratorOutputFile).writeln(1, "List<T> findAll(Specification<T> spec);");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "}");
        verify(mockGeneratorOutputFile).close(); // Ensure the file is closed
    }
}
