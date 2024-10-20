package com.generator.writer.java.components.tests.unit;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaRepositoryUnitTestWriter implements DefaultWriter {

	
	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getUnitTestRepositoryPackagePath(entity.getName()), upperCaseName + "RepositoryUnitTest.java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "package " + WriterUtils.getUnitTestRepositoryPackageImport(entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "");
			file.writeln(0, "/**");
			file.writeln(0, "* Since JPA repositories are typically tested via integration tests, there is often less need for unit tests");
			file.writeln(0, "* for the repository itself. However, if your repository contains custom queries, you may write unit tests");
			file.writeln(0, "* to verify logic around those queries. Still, most of the interaction with the database is tested using");
			file.writeln(0, "* integration tests, as JPA repositories delegate most of their functionality to Spring Data JPA.");
			file.writeln(0, "*/");
			file.writeln(0, "public class " + upperCaseName + "RepositoryUnitTest {");
			file.writeln(0, "}");
		}
	}
}
