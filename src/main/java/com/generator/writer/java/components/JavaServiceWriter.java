package com.generator.writer.java.components;

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
		if (Utils.fileExists(Utils.getServicePackagePath(false, entity.getName()), upperCaseName + "Service.java")) {
			return;
		}
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getServicePackagePath(false, entity.getName()), upperCaseName + "Service.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + Utils.getImportServicePackageName(false, entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.context.annotation.*;");
			file.writeln(0, "import org.springframework.stereotype.*;");
//			file.writeln(0, "import " + Utils.getImportServicePackageName(true, entity.getName()) + "." + upperCaseName + "GenericService;");
			file.writeln(0, "import " + Utils.getImportRepositoryPackageName(false, entity.getName()) + "." + upperCaseName + "Repository;");
			file.writeln(0, "");
			file.writeln(0, "@Service");
			file.writeln(0, "public class " + upperCaseName + "Service extends " + upperCaseName + "ServiceBasic {");
			file.writeln(0, "");
			file.writeln(1, "public " + upperCaseName + "Service(" + upperCaseName + "Repository repository) {");
			file.writeln(2, "super(repository);");
			file.writeln(1, "}");

			file.writeln(0, "}");
		}

	}
	
	public void create2(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		if (Utils.fileExists(Utils.getServicePackagePath(false), upperCaseName + "Service.java")) {
			return;
		}
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getServicePackagePath(false, entity.getName()), upperCaseName + "Service.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + Utils.getImportServicePackageName(false, entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.context.annotation.*;");
			file.writeln(0, "import org.springframework.stereotype.*;");
			file.writeln(0, "import " + Utils.getImportServicePackageName(true, entity.getName()) + ".GenericService;");
			file.writeln(0, "import " + Utils.getImportModelPackageName() + "." + upperCaseName + ";");
			file.writeln(0, "import " + Utils.getImportRepositoryPackageName(false, entity.getName()) + "." + upperCaseName + "Repository;");
			file.writeln(0, "");
			file.writeln(0, "@Service");
			file.writeln(0, "@Primary");
			file.writeln(0, "public class " + upperCaseName + "Service extends GenericService<" + upperCaseName + "> {");
			file.writeln(0, "");
			file.writeln(1, "public " + upperCaseName + "Service(" + upperCaseName + "Repository repository) {");
			file.writeln(2, "super(repository);");
			file.writeln(1, "}");

			file.writeln(0, "}");
		}
	}


}
