package com.generator.writer.java.exceptions;

import com.generator.Application;
import com.generator.util.StringUtils;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaApplicationExceptionWriter {
	public void create() throws Exception {
		String appName = StringUtils.uppercaseFirst(Application.getSpringProperties().getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getExceptionsPackagePath(), appName + "Exception.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + Application.getSpringProperties().getPackageName() + ".exceptions;");
			file.writeln(0, "");
			file.writeln(0, "public class MervException extends Exception {");
			file.writeln(1, "private static final long serialVersionUID = 1L;");
			file.writeln(0, "");
			file.writeln(1, "public " + appName + "Exception(String message) {");
			file.writeln(2, "super(message);");
			file.writeln(1, "}");
			file.writeln(0, "}");
		}
	}
}
