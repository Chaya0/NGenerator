package com.generator.writer.java.components;

import com.generator.annotations.CustomComponent;
import com.generator.annotations.WriterVersion;
import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.model.enums.ComponentType;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;


@CustomComponent(ComponentType.CONTROLLER)
@WriterVersion("1.0")
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
		if (WriterUtils.fileExists(WriterUtils.getControllerPackagePath(false, entity.getName()), upperCaseName + "Controller.java")) {
			return;
		}
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getControllerPackagePath(false, entity.getName()), upperCaseName + "Controller.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "package " + WriterUtils.getImportControllerPackageName(false, entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.web.bind.annotation.*;");
			file.writeln(0, "import org.springframework.context.annotation.*;");
			// TODO dodati logiku za generisanje anotacije za autorizaciju
			file.writeln(0, "import io.swagger.v3.oas.annotations.security.SecurityRequirement;");
//			file.writeln(0, "import " + Utils.getImportControllerPackageName(false) + upperCaseName + "ControllerBasic;");
			file.writeln(0, "import " + WriterUtils.getImportServicePackageName(false) + "." + entity.getName().toLowerCase() + "." + upperCaseName + "Service;");
			file.writeln(0, "import " + WriterUtils.getImportModelPackageName() + "." + upperCaseName + ";");

			file.writeln(0, "");
			file.writeln(0, "@Primary");
			file.writeln(0, "@CrossOrigin");
			file.writeln(0, "@RestController");
			file.writeln(0, "@RequestMapping(\"/api/" + entity.getName() + "\")");
			// TODO dodati logiku za generisanje anotacije za autorizaciju
			file.writeln(0, "@SecurityRequirement(name = \"bearerAuth\")");
			file.writeln(0, "public class " + upperCaseName + "Controller extends " + upperCaseName + "ControllerBasic {");
			file.writeln(0, "");
			file.writeln(1, "public " + upperCaseName + "Controller(" + upperCaseName + "Service service) {");
			file.writeln(2, "super(service);");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(0, "}");
		}

	}

}
