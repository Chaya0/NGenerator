package com.generator.writer.java.exceptions;

import com.generator.Application;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;

public class JavaOperationNotSupprotedExceptionWriter {
	public void create() throws Exception {
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getExceptionsPackagePath(), "OperationNotSupportedException.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + Application.getSpringProperties().getPackageName() + ".exceptions;");
			file.writeln(0, "");
			file.writeln(0, "public class OperationNotSupportedException extends RuntimeException{");
			file.writeln(1, "private static final long serialVersionUID = 1L;");
			file.writeln(0, "");
			file.writeln(1, "public OperationNotSupportedException(String message) {");
			file.writeln(2, "super(message);");
			file.writeln(1, "}");
			file.writeln(0, "}");
		}
	}
}
