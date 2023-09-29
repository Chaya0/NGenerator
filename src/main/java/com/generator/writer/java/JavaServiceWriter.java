package com.generator.writer.java;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;
import com.generator.writer.Writer;

public class JavaServiceWriter implements Writer {

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
			file.writeln(0, "@Service");
			file.writeln(0, "public class " + upperCaseName + " implements " + upperCaseName + "GenericService {");
			file.writeln(0, "");

			file.writeln(0, "}");
		}

	}

}
