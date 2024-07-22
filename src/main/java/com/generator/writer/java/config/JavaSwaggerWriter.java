package com.generator.writer.java.config;

import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaSwaggerWriter {

	public void create() throws Exception {
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getConfigPackagePath(), "OpenApiConfiguration.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + WriterUtils.getImportDefaultPackage() + ".config;");
			file.writeln(0, "");
			file.writeln(0, "import io.swagger.v3.oas.annotations.OpenAPIDefinition;");
			file.writeln(0, "import io.swagger.v3.oas.annotations.info.Info;");
			file.writeln(0, "import org.springframework.context.annotation.Configuration;");
			file.writeln(0, "");
//			if(condition) {
//				file.writeln(0, "@SecurityScheme(name = \"bearerAuth\", type = SecuritySchemeType.HTTP, bearerFormat = \"JWT\", scheme = \"bearer\", in = SecuritySchemeIn.HEADER)\r");
//			}
			file.writeln(0, "@Configuration");
			file.writeln(0, "@OpenAPIDefinition(info = @Info(title = \"My API\", version = \"v1\"))");
			file.writeln(0, "public class OpenApiConfiguration {");
			file.writeln(0, "}");
		}
	}
}
