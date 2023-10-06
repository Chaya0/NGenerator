package com.generator.writer.java;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;

public class JavaServiceWriter implements DefaultWriter {

	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		if (Utils.fileExists(Utils.getServicePackagePath(false), upperCaseName + "Service.java")) {
			return;
		}
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getServicePackagePath(false), upperCaseName + "Service.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + Utils.getImportServicePackageName(false) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.context.annotation.*;");
			file.writeln(0, "import org.springframework.stereotype.*;");
			file.writeln(0, "import " + Utils.getImportServicePackageName(true) + "." + upperCaseName + "GenericService;");
			file.writeln(0, "import " + Utils.getImportRepositoryPackageName(false) + "." + upperCaseName + "Repository;");
			file.writeln(0, "");
			file.writeln(0, "@Service");
			file.writeln(0, "@Primary");
			file.writeln(0, "public class " + upperCaseName + "Service extends " + upperCaseName + "GenericService {");
			file.writeln(0, "");
			file.writeln(1, "public " + upperCaseName + "Service(" + upperCaseName + "Repository repository) {");
			file.writeln(2, "super(repository);");
			file.writeln(1, "}");

			file.writeln(0, "}");
		}

	}

}
