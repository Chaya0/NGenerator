package com.generator.writer.java.components.basic;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaBasicControllerWriter implements DefaultWriter{

	@Override
	public void create(AppModel model) throws Exception {
		for(Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());

		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getControllerPackagePath(false, entity.getName()),
				upperCaseName + "ControllerBasic.java", true)) {

			file.writeln(0, "package " + WriterUtils.getImportControllerPackageName(false, entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import " + WriterUtils.getImportServicePackageName(false, entity.getName()) + "." + upperCaseName + "Service;");
			file.writeln(0, "import " + WriterUtils.getImportControllerPackageName(false) + ".GenericController;");
			file.writeln(0, "import " + WriterUtils.getImportModelPackageName() + "." + upperCaseName + ";");
			file.writeln(0, "");
			file.writeln(0, "public class " + upperCaseName + "ControllerBasic extends GenericController<"
					+ upperCaseName + "> {");
			file.writeln(1, "protected " + upperCaseName + "Service " + entity.getName() + "Service;");
			file.writeln(0, "");
			file.writeln(1, "public " + upperCaseName + "ControllerBasic(" + upperCaseName + "Service service) {");
			file.writeln(2, "super(service);");
			file.writeln(2, "this." + entity.getName() + "Service = service;");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(0, "}");

		}
	}
}
