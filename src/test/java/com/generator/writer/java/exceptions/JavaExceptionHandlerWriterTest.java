package com.generator.writer.java.exceptions;

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

public class JavaExceptionHandlerWriterTest {

	@Mock
	private GeneratorOutputFile mockGeneratorOutputFile;

	@InjectMocks
	private JavaExceptionHandlerWriter javaExceptionHandlerWriter;

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
        javaExceptionHandlerWriter.create();

        // Then
        verify(mockGeneratorOutputFile).writeln(0, "package com.myApp.exceptions.handler;");
        verify(mockGeneratorOutputFile).writeln(0, "import java.util.ArrayList;");
        verify(mockGeneratorOutputFile).writeln(0, "import java.util.List;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.dao.EmptyResultDataAccessException;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.data.mapping.PropertyReferenceException;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.http.HttpStatus;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.http.ResponseEntity;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.security.authentication.BadCredentialsException;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.web.bind.annotation.ControllerAdvice;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.web.bind.annotation.ExceptionHandler;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.web.context.request.WebRequest;");
        verify(mockGeneratorOutputFile).writeln(0, "import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "import com.myApp.dto.ErrorResponse;");
        verify(mockGeneratorOutputFile).writeln(0, "import com.myApp.exceptions.OperationNotSupportedException;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "import jakarta.persistence.EntityNotFoundException;");
        verify(mockGeneratorOutputFile).writeln(0, "import jakarta.validation.ConstraintViolationException;");
        verify(mockGeneratorOutputFile).writeln(0, "");
        verify(mockGeneratorOutputFile).writeln(0, "@ControllerAdvice");
        verify(mockGeneratorOutputFile).writeln(0, "public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {");
        verify(mockGeneratorOutputFile).writeln(0, "");

        // Verify exception handlers
        verify(mockGeneratorOutputFile).writeln(1, "@ExceptionHandler(Exception.class)");
        verify(mockGeneratorOutputFile).writeln(1, "public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {");
        verify(mockGeneratorOutputFile).writeln(2, "List<String> details = new ArrayList<>();");
        verify(mockGeneratorOutputFile).writeln(2, "details.add(ex.getLocalizedMessage());");
        verify(mockGeneratorOutputFile).writeln(2, "ErrorResponse error = new ErrorResponse(\"500\", \"Server Error\", details);");
        verify(mockGeneratorOutputFile).writeln(2, "return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);");
        verify(mockGeneratorOutputFile).writeln(1, "}");

        verify(mockGeneratorOutputFile).writeln(1, "@ExceptionHandler(BadCredentialsException.class)");
        verify(mockGeneratorOutputFile).writeln(1, "public final ResponseEntity<Object> handleBadCredentialsException(Exception ex, WebRequest request) {");
        verify(mockGeneratorOutputFile).writeln(2, "List<String> details = new ArrayList<>();");
        verify(mockGeneratorOutputFile).writeln(2, "details.add(ex.getLocalizedMessage());");
        verify(mockGeneratorOutputFile).writeln(2, "ErrorResponse error = new ErrorResponse(\"401\", \"Bad credentials!\", details);");
        verify(mockGeneratorOutputFile).writeln(2, "return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);");
        verify(mockGeneratorOutputFile).writeln(1, "}");

        verify(mockGeneratorOutputFile).writeln(1, "@ExceptionHandler(OperationNotSupportedException.class)");
        verify(mockGeneratorOutputFile).writeln(1, "public final ResponseEntity<Object> handleOperationNotSupportedException(Exception ex, WebRequest request) {");
        verify(mockGeneratorOutputFile).writeln(2, "List<String> details = new ArrayList<>();");
        verify(mockGeneratorOutputFile).writeln(2, "details.add(ex.getLocalizedMessage());");
        verify(mockGeneratorOutputFile).writeln(2, "ErrorResponse error = new ErrorResponse(\"418\", \"I am a teapot!\", details);");
        verify(mockGeneratorOutputFile).writeln(2, "return new ResponseEntity<>(error, HttpStatus.I_AM_A_TEAPOT);");
        verify(mockGeneratorOutputFile).writeln(1, "}");

        verify(mockGeneratorOutputFile).writeln(1, "@ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})");
        verify(mockGeneratorOutputFile).writeln(1, "public final ResponseEntity<Object> handleEntityNotFound(Exception ex, WebRequest request) {");
        verify(mockGeneratorOutputFile).writeln(2, "List<String> details = new ArrayList<>();");
        verify(mockGeneratorOutputFile).writeln(2, "details.add(ex.getLocalizedMessage());");
        verify(mockGeneratorOutputFile).writeln(2, "ErrorResponse error = new ErrorResponse(\"404\", \"Not found!\", details);");
        verify(mockGeneratorOutputFile).writeln(2, "return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);");
        verify(mockGeneratorOutputFile).writeln(1, "}");

        verify(mockGeneratorOutputFile).writeln(1, "@ExceptionHandler(PropertyReferenceException.class)");
        verify(mockGeneratorOutputFile).writeln(1, "public final ResponseEntity<Object> handlePropertyReference(Exception ex, WebRequest request) {");
        verify(mockGeneratorOutputFile).writeln(2, "List<String> details = new ArrayList<>();");
        verify(mockGeneratorOutputFile).writeln(2, "details.add(ex.getLocalizedMessage());");
        verify(mockGeneratorOutputFile).writeln(2, "ErrorResponse error = new ErrorResponse(\"400\", \"Bad request!\", details);");
        verify(mockGeneratorOutputFile).writeln(2, "return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);");
        verify(mockGeneratorOutputFile).writeln(1, "}");

        verify(mockGeneratorOutputFile).writeln(1, "@ExceptionHandler({ConstraintViolationException.class})");
        verify(mockGeneratorOutputFile).writeln(1, "public final ResponseEntity<Object> handleConstraintViolation(Exception ex, WebRequest request) {");
        verify(mockGeneratorOutputFile).writeln(2, "List<String> details = new ArrayList<>();");
        verify(mockGeneratorOutputFile).writeln(2, "details.add(ex.getLocalizedMessage());");
        verify(mockGeneratorOutputFile).writeln(2, "ErrorResponse error = new ErrorResponse(\"422\", \"Unprocessable entity! Validation failed.\", details);");
        verify(mockGeneratorOutputFile).writeln(2, "return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);");
        verify(mockGeneratorOutputFile).writeln(1, "}");

        verify(mockGeneratorOutputFile).writeln(0, "}");

        verify(mockGeneratorOutputFile).close(); // Ensure the file is closed
    }
}