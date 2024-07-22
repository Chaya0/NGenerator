package com.generator.writer.java.dto;

import com.generator.Application;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaErrorResponseDTOWriter {
	public void create() throws Exception {
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getDTOPackagePath(), "ErrorResponse.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + Application.getSpringProperties().getPackageName() + ".dto;");
			file.writeln(0, "import lombok.Data;");
			file.writeln(0, "import lombok.NoArgsConstructor;");
			file.writeln(0, "import lombok.AllArgsConstructor;");
			file.writeln(0, "import java.util.List;");
			file.writeln(0, "");
			file.writeln(0, "@Data");
			file.writeln(0, "@NoArgsConstructor");
			file.writeln(0, "@AllArgsConstructor");
			file.writeln(0, "public class ErrorResponse {");
			file.writeln(0, "");
			file.writeln(1, "private String errorCode;");
			file.writeln(1, "private String message;");
			file.writeln(1, "private List<String> details;");
			file.writeln(0, "");
			file.writeln(1, "public ErrorResponse(String message, List<String> details) {");
			file.writeln(2, "super();");
			file.writeln(2, "this.message = message;");
			file.writeln(2, "this.details = details;");
			file.writeln(1, "}");
			file.writeln(0, "}");
		}
	}
}
