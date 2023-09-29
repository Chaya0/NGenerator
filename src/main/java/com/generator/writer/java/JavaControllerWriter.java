package com.generator.writer.java;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;
import com.generator.writer.Writer;

public class JavaControllerWriter implements Writer {

	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		if (Utils.fileExists(Utils.getControllerPackagePath(false), upperCaseName + "Controller.java")) {
			return;
		}
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getControllerPackagePath(false), upperCaseName + "Controller.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + Utils.getImportControllerPackageName(false) + ";");
			file.writeln(0, "");
			file.writeln(0, "@Controller");
			file.writeln(0, "public class " + upperCaseName + " extends " + upperCaseName + "GenericController {");
			file.writeln(0, "");

			file.writeln(0, "}");
		}

	}

}
