package com.generator.writer.java;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;
import com.generator.writer.Writer;

public class JavaRepositoryWriter implements Writer {

	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		if (Utils.fileExists(Utils.getRepositoryPackagePath(false), upperCaseName + "Repository.java")) {
			return;
		}
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getRepositoryPackagePath(false), upperCaseName + "Repository.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + Utils.getImportRepositoryPackageName(false) + ";");
			file.writeln(0, "");
			file.writeln(0, "@Repository");
			file.writeln(0, "public class " + upperCaseName + " implements " + upperCaseName + "GenericRepository {");
			file.writeln(0, "");

			file.writeln(0, "}");
		}

	}
}
