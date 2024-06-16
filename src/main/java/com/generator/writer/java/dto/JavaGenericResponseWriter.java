package com.generator.writer.java.dto;

import java.io.IOException;

import com.generator.Application;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;

public class JavaGenericResponseWriter {
	public void create() throws Exception {
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getDTOPackagePath(), "GenericResponse.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + Application.getSpringProperties().getPackageName() + ".dto;");
			file.writeln(0, "import lombok.Data;");
			file.writeln(0, "import lombok.NoArgsConstructor;");
			file.writeln(0, "import lombok.AllArgsConstructor;");
			file.writeln(0, "");
			file.writeln(0, "@Data");
			file.writeln(0, "@NoArgsConstructor");
			file.writeln(0, "@AllArgsConstructor");
			file.writeln(0, "public class GenericResponseEntity {");
			file.writeln(1, "private String code;");
			file.writeln(1, "private String message;");
			file.writeln(1, "private Object data;");
			file.writeln(0, "");

			writeGetSuccessfulResponse(file);

			writeGetErrorResponse(file);

			writeGetErrorResponseWithCodeInput(file);

			file.writeln(0, "}");
		}
	}

	private void writeGetErrorResponseWithCodeInput(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "public static GenericResponseEntity getErrorResponse(String code, String errorMessage) {");
		file.writeln(2, "GenericResponseEntity response = new GenericResponseEntity();");
		file.writeln(2, "response.code = code;");
		file.writeln(2, "response.message = errorMessage;");
		file.writeln(2, "response.data = \"\";");
		file.writeln(2, "return response;");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeGetErrorResponse(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "public static GenericResponseEntity getErrorResponse(String errorMessage) {");
		file.writeln(2, "GenericResponseEntity response = new GenericResponseEntity();");
		file.writeln(2, "response.code = \"500\";");
		file.writeln(2, "response.message = errorMessage;");
		file.writeln(2, "response.data = \"\";");
		file.writeln(2, "return response;");
		file.writeln(1, "}");
		file.writeln(0, "");
	}

	private void writeGetSuccessfulResponse(GeneratorOutputFile file) throws IOException {
		file.writeln(1, "public static GenericResponseEntity getSuccessfulResponse(Object data) {");
		file.writeln(2, "GenericResponseEntity response = new GenericResponseEntity();");
		file.writeln(2, "response.code = \"200\";");
		file.writeln(2, "response.message = \"Success\";");
		file.writeln(2, "response.data = data;");
		file.writeln(2, "return response;");
		file.writeln(1, "}");
		file.writeln(0, "");
	}
}
