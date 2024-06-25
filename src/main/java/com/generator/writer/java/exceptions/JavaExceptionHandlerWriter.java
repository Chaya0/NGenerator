package com.generator.writer.java.exceptions;

import java.io.IOException;

import com.generator.Application;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;

public class JavaExceptionHandlerWriter {

	public void create() throws Exception {
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getExceptionsPackagePath() + "/handler", "DefaultExceptionHandler.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + Application.getSpringProperties().getPackageName() + ".exceptions.handler;");
			file.writeln(0, "import java.util.ArrayList;");
			file.writeln(0, "import java.util.List;");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.dao.EmptyResultDataAccessException;");
			file.writeln(0, "import org.springframework.data.mapping.PropertyReferenceException;");
			file.writeln(0, "import org.springframework.http.HttpStatus;");
			file.writeln(0, "import org.springframework.http.ResponseEntity;");
			file.writeln(0, "import org.springframework.security.authentication.BadCredentialsException;");
			file.writeln(0, "import org.springframework.web.bind.annotation.ControllerAdvice;");
			file.writeln(0, "import org.springframework.web.bind.annotation.ExceptionHandler;");
			file.writeln(0, "import org.springframework.web.context.request.WebRequest;");
			file.writeln(0, "import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;");
			file.writeln(0, "");
			file.writeln(0, "import " + Utils.getImportDefaultPackage() + ".dto.ErrorResponse;");
			file.writeln(0, "import " + Utils.getImportDefaultPackage() + ".exceptions.OperationNotSupportedException;");
			file.writeln(0, "");
			file.writeln(0, "import jakarta.persistence.EntityNotFoundException;");
			file.writeln(0, "import jakarta.validation.ConstraintViolationException;");
			file.writeln(0, "");
			file.writeln(0, "@ControllerAdvice");
			file.writeln(0, "public class DefaultExceptionHandler extends ResponseEntityExceptionHandler {");
			file.writeln(0, "");
			
			writeInternalServerErrorHandler(file);
			
			writeUnprocessableEntityHandler(file);
			
			writeBadRequestHandler(file);
			
			writeEntityNotFoundHandler(file);
			
			writeOperationNotSupportedHandler(file);
			
			writeUnauthorisedHandler(file);
			
			file.writeln(0, "}");
		}
	}

	private void writeInternalServerErrorHandler(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "@ExceptionHandler(Exception.class)");
		file.writeln(1, "public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {");
		file.writeln(2, "List<String> details = new ArrayList<>();");
		file.writeln(2, "details.add(ex.getLocalizedMessage());");
		file.writeln(2, "ErrorResponse error = new ErrorResponse(\"500\", \"Server Error\", details);");
		file.writeln(2, "return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeUnauthorisedHandler(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "@ExceptionHandler(BadCredentialsException.class)");
		file.writeln(1, "public final ResponseEntity<Object> handleBadCredentialsException(Exception ex, WebRequest request) {");
		file.writeln(2, "List<String> details = new ArrayList<>();");
		file.writeln(2, "details.add(ex.getLocalizedMessage());");
		file.writeln(2, "ErrorResponse error = new ErrorResponse(\"401\", \"Bad credentials!\", details);");
		file.writeln(2, "return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeOperationNotSupportedHandler(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "@ExceptionHandler(OperationNotSupportedException.class)");
		file.writeln(1, "public final ResponseEntity<Object> handleOperationNotSupportedException(Exception ex, WebRequest request) {");
		file.writeln(2, "List<String> details = new ArrayList<>();");
		file.writeln(2, "details.add(ex.getLocalizedMessage());");
		file.writeln(2, "ErrorResponse error = new ErrorResponse(\"418\", \"I am a teapot!\", details);");
		file.writeln(2, "return new ResponseEntity<>(error, HttpStatus.I_AM_A_TEAPOT);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeEntityNotFoundHandler(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "@ExceptionHandler({EntityNotFoundException.class, EmptyResultDataAccessException.class})");
		file.writeln(1, "public final ResponseEntity<Object> handleEntityNotFound(Exception ex, WebRequest request) {");
		file.writeln(2, "List<String> details = new ArrayList<>();");
		file.writeln(2, "details.add(ex.getLocalizedMessage());");
		file.writeln(2, "ErrorResponse error = new ErrorResponse(\"404\", \"Not found!\", details);");
		file.writeln(2, "return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeBadRequestHandler(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "@ExceptionHandler(PropertyReferenceException.class)");
		file.writeln(1, "public final ResponseEntity<Object> handlePropertyReference(Exception ex, WebRequest request) {");
		file.writeln(2, "List<String> details = new ArrayList<>();");
		file.writeln(2, "details.add(ex.getLocalizedMessage());");
		file.writeln(2, "ErrorResponse error = new ErrorResponse(\"400\", \"Bad request!\", details);");
		file.writeln(2, "return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeUnprocessableEntityHandler(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "@ExceptionHandler({ConstraintViolationException.class})");
		file.writeln(1, "public final ResponseEntity<Object> handleConstraintViolation(Exception ex, WebRequest request) {");
		file.writeln(2, "List<String> details = new ArrayList<>();");
		file.writeln(2, "details.add(ex.getLocalizedMessage());");
		file.writeln(2, "ErrorResponse error = new ErrorResponse(\"422\", \"Unprocessable entity! Validation failed.\", details);");
		file.writeln(2, "return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);");
		file.writeln(1, "}");
		file.writeln(0, "");
	}
}
