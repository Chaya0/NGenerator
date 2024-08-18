package com.generator.writer.java.utils;

import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Properties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.generator.Application;
import com.generator.model.properties.SpringProperties;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaApiUtilWriterTest {

	@Mock
	private GeneratorOutputFile mockGeneratorOutputFile;

	@InjectMocks
	private JavaApiUtilWriter javaApiUtilWriter;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.openMocks(this);
		// Setup SpringProperties
		Properties properties = new Properties();
		properties.setProperty("packageName", "com.myApp");
		SpringProperties springProperties = new SpringProperties(properties);
		Application.setSpringProperties(springProperties);

		// Mock static methods from WriterUtils
		try {
			when(WriterUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(mockGeneratorOutputFile);
		} catch (Exception e) {
			fail("Failed to mock WriterUtils.getOutputResource method");
		}
	}

	@Test
    public void testCreate() throws Exception {
        // Given
        when(mockGeneratorOutputFile.hasAlreadyExisted()).thenReturn(false);
        doNothing().when(mockGeneratorOutputFile).writeln(anyInt(), anyString());

        // When
        javaApiUtilWriter.create();

        // Then
        verify(mockGeneratorOutputFile).writeln(0, "package com.myApp.utils;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.data.domain.PageRequest;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.data.domain.Pageable;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.data.domain.Sort;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.data.domain.Sort.Direction;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.data.domain.Sort.Order;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "import lombok.NoArgsConstructor;");
        verify(mockGeneratorOutputFile).writeln(0, "import java.util.ArrayList;");
        verify(mockGeneratorOutputFile).writeln(0, "import java.util.List;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "@NoArgsConstructor");
        verify(mockGeneratorOutputFile).writeln(0, "public class ApiUtil {");
        verify(mockGeneratorOutputFile).writeln(1, "public static final String DEFAULT_PAGE = \"0\";");
        verify(mockGeneratorOutputFile).writeln(1, "public static final String DEFAULT_SIZE = \"50\";");
        verify(mockGeneratorOutputFile).writeln(1, "public static final int MIN_PAGE = 0;");
        verify(mockGeneratorOutputFile).writeln(1, "public static final int MIN_SIZE = 1;");
        verify(mockGeneratorOutputFile).writeln(1, "public static final int MAX_SIZE = 100;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(1, "public static Pageable resolveSortingAndPagination(Integer page, Integer size, String[] sort) {");
        verify(mockGeneratorOutputFile).writeln(2, "List<Order> orders = new ArrayList<>();");
        verify(mockGeneratorOutputFile).writeln(2, "for (String sortParam : sort) {");
        verify(mockGeneratorOutputFile).writeln(3, "Direction sortDir = sortParam.startsWith(\"-\") ? Direction.DESC : Direction.ASC;");
        verify(mockGeneratorOutputFile).writeln(3, "sortParam = sortParam.startsWith(\"-\") || sortParam.startsWith(\"+\") ? sortParam.substring(1) : sortParam;");
        verify(mockGeneratorOutputFile).writeln(3, "orders.add(new Order(sortDir, sortParam));");
        verify(mockGeneratorOutputFile).writeln(2, "}");
        verify(mockGeneratorOutputFile).writeln(2, "return PageRequest.of(page, size, Sort.by(orders));");
        verify(mockGeneratorOutputFile).writeln(1, "}");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "}");

        verify(mockGeneratorOutputFile).close(); // Ensure the file is closed
    }
}