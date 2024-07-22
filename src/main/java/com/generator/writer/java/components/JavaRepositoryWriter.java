package com.generator.writer.java.components;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaRepositoryWriter implements DefaultWriter {

	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		if (WriterUtils.fileExists(WriterUtils.getRepositoryPackagePath(false, entity.getName()), upperCaseName + "Repository.java")) {
			return;
		}
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getRepositoryPackagePath(false, entity.getName()), upperCaseName + "Repository.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + WriterUtils.getImportRepositoryPackageName(false, entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.stereotype.Repository;");
//			file.writeln(0, "import " + Utils.getImportModelPackageName() + "." + upperCaseName + ";");
//  			file.writeln(0, "import " + Utils.getImportRepositoryPackageName(true, entity.getName()) + "." + upperCaseName + "GenericRepository;");
			file.writeln(0, "");
			file.writeln(0, "@Repository");
			file.writeln(0, "public interface " + upperCaseName + "Repository extends " + upperCaseName + "RepositoryBasic {");
			file.writeln(0, "");

			file.writeln(0, "}");
		}

	}
//	public void create(Entity entity) throws Exception {
//		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
//		if (Utils.fileExists(Utils.getRepositoryPackagePath(false, entity.getName()), upperCaseName + "Repository.java")) {
//			return;
//		}
//		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getRepositoryPackagePath(false, entity.getName()), upperCaseName + "Repository.java", false)) {
//			if (file.hasAlreadyExisted()) {
//				return;
//			}
//
//			file.writeln(0, "package " + Utils.getImportRepositoryPackageName(false, entity.getName()) + ";");
//			file.writeln(0, "");
//			file.writeln(0, "import org.springframework.context.annotation.*;");
//			file.writeln(0, "import org.springframework.stereotype.*;");
//			file.writeln(0, "import " + Utils.getImportRepositoryPackageName(true, entity.getName()) + ".GenericRepository;");
//			file.writeln(0, "import " + Utils.getImportModelPackageName() + "." + upperCaseName + ";");
//			file.writeln(0, "");
//			file.writeln(0, "@Repository");
//			file.writeln(0, "public interface " + upperCaseName + "Repository extends GenericRepository<" + upperCaseName + "> {");
//			file.writeln(0, "");
//
//			file.writeln(0, "}");
//		}
//
//	}
}
