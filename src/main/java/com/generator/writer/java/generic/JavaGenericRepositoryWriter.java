package com.generator.writer.java.generic;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;

public class JavaGenericRepositoryWriter implements DefaultWriter {

	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getRepositoryPackagePath(true), upperCaseName + "GenericRepository.java", true)) {

			file.writeln(0, "package " + Utils.getImportRepositoryPackageName(true) + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.data.jpa.domain.Specification;");
			file.writeln(0, "import org.springframework.data.jpa.repository.JpaRepository;");
			file.writeln(0, "import org.springframework.stereotype.Repository;");
			file.writeln(0, "import org.springframework.data.domain.Pageable;");
			file.writeln(0, "import org.springframework.data.domain.Page;");
			file.writeln(0, "import " + Utils.getImportModelPackageName() + "." + upperCaseName + ";");
			file.writeln(0, "import java.util.List;");
			file.writeln(0, "");
			file.writeln(0, "@Repository");
			file.writeln(0, "public interface " + upperCaseName + "GenericRepository extends JpaRepository<" + upperCaseName + ", Long> {");
			file.writeln(0, "");
			file.writeln(1, "Page<" + upperCaseName + "> findAll(Specification<" + upperCaseName + "> spec, Pageable pageSort);");
			file.writeln(1, "List<" + upperCaseName + "> findAll(Specification<" + upperCaseName + "> spec);");
			file.writeln(0, "");
			file.writeln(0, "}");
		}

	}
}
