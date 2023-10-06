package com.generator.writer.java;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;

public class JavaControllerWriter implements DefaultWriter {

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
			/*
			 * import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;
			 */
			file.writeln(0, "package " + Utils.getImportControllerPackageName(false) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.web.bind.annotation.*;");
			file.writeln(0, "import org.springframework.context.annotation.*;");
			file.writeln(0, "import org.springframework.stereotype.*;");
			file.writeln(0, "import " + Utils.getImportControllerPackageName(true) + "." + upperCaseName + "GenericController;");
			file.writeln(0, "import " + Utils.getImportServicePackageName(false) + "." + upperCaseName + "Service;");
			file.writeln(0, "");
			file.writeln(0, "@Primary");
			file.writeln(0, "@CrossOrigin");
			file.writeln(0, "@RestController");
			file.writeln(0, "@RequestMapping(\"/api/" + entity.getName() + "\")");
			file.writeln(0, "public class " + upperCaseName + "Controller extends " + upperCaseName + "GenericController {");
			file.writeln(0, "");
			file.writeln(0, "public " + upperCaseName + "Controller(" + upperCaseName +"Service service) {");
			file.writeln(0, "super(service);");
			file.writeln(0, "}");
			file.writeln(0, "");
			file.writeln(0, "}");
		}

	}

}
