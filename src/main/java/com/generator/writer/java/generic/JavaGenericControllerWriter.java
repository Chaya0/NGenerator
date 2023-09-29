package com.generator.writer.java.generic;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;
import com.generator.writer.Writer;

public class JavaGenericControllerWriter implements Writer {

	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		if (Utils.fileExists(Utils.getControllerPackagePath(true), upperCaseName + "GenericController.java")) {
			return;
		}
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getControllerPackagePath(true), upperCaseName + "GenericController.java")) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + Utils.getImportControllerPackageName(true) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.data.jpa.domain.Specification;");
			file.writeln(0, "import org.springframework.data.jpa.repository.JpaRepository;");
			file.writeln(0, "import org.springframework.data.jpa.repository.Query;");
			file.writeln(0, "import org.springframework.stereotype.Repository;");
			file.writeln(0, "");
			file.writeln(0, "import java.util.Optional");
			file.writeln(0, "import java.util.List");
			file.writeln(0, "");
			file.writeln(0, "@Controller");
			file.writeln(0, "public interface " + upperCaseName + "GenericController extends JpaRepository<" + upperCaseName + ", Long> {");
			file.writeln(0, "");
			file.writeln(1, "Page<" + upperCaseName + "> findAll(Specification<" + upperCaseName + "> spec, Pageable pageSort);");
			file.writeln(0, "");
			file.writeln(0, "}");
		}

	}

}
