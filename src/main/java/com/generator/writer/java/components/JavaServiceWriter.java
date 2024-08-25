package com.generator.writer.java.components;

import com.generator.annotations.CustomComponent;
import com.generator.annotations.WriterVersion;
import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

@CustomComponent
@WriterVersion("1.0")
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
		if (WriterUtils.fileExists(WriterUtils.getServicePackagePath(false, entity.getName()), upperCaseName + "Service.java")) {
			return;
		}
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getServicePackagePath(false, entity.getName()), upperCaseName + "Service.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + WriterUtils.getImportServicePackageName(false, entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.context.annotation.*;");
			file.writeln(0, "import org.springframework.stereotype.*;");
//			file.writeln(0, "import " + Utils.getImportServicePackageName(true, entity.getName()) + "." + upperCaseName + "GenericService;");
			file.writeln(0, "import " + WriterUtils.getImportRepositoryPackageName(false, entity.getName()) + "." + upperCaseName + "Repository;");
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
		if (WriterUtils.fileExists(WriterUtils.getServicePackagePath(false), upperCaseName + "Service.java")) {
			return;
		}
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getServicePackagePath(false, entity.getName()), upperCaseName + "Service.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + WriterUtils.getImportServicePackageName(false, entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.context.annotation.*;");
			file.writeln(0, "import org.springframework.stereotype.*;");
			file.writeln(0, "import " + WriterUtils.getImportServicePackageName(true, entity.getName()) + ".GenericService;");
			file.writeln(0, "import " + WriterUtils.getImportModelPackageName() + "." + upperCaseName + ";");
			file.writeln(0, "import " + WriterUtils.getImportRepositoryPackageName(false, entity.getName()) + "." + upperCaseName + "Repository;");
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
