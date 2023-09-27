package com.generator.writer.java.generic;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.util.Utils;
import com.generator.writer.BuilderOutputFile;
import com.generator.writer.Writer;

import raf.si.racunovodstvo.knjizenje.model.Konto;

public class JavaGenericRepositoryWriter implements Writer {

	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		if (Utils.classExsists()) {
			return;
		}

		String upercaseName = StringUtils.uppercaseFirst(entity.getName());
		String outputPackage = "";
		try (BuilderOutputFile file = Utils.getOutputResource(Utils.getPackageName(properties.getControllerPackageName(), outputPackage), StringUtils.uppercaseFirst(entity.getName()) + "BusinessService.java",
				false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + ";");
			file.writeln(0, "");
			file.writeln(0, "import org.springframework.data.jpa.domain.Specification;");
			file.writeln(0, "import org.springframework.data.jpa.repository.JpaRepository;");
			file.writeln(0, "import org.springframework.data.jpa.repository.Query;");
			file.writeln(0, "import org.springframework.stereotype.Repository;");
			file.writeln(0, "");
			file.writeln(0, "import java.util.Optional");
			file.writeln(0, "import java.util.List");
			file.writeln(0, "");
			file.writeln(0, "@Repository");
			file.writeln(0, "public interface " + uperCaseName + "GenericRepository extends JpaRepository<" + uperCaseName + ", Long> {");
			file.writeln(0, "");
			file.writeln(1, "Page<" + uperCaseName + "> findAll(Specification<" + uperCaseName + "> spec, Pageable pageSort);");
			file.writeln(0, "");
			file.writeln(0, "}");
		}

	}
}
