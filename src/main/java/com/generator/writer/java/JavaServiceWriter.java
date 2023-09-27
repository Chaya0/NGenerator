package com.generator.writer.java;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.util.Utils;
import com.generator.writer.BuilderOutputFile;
import com.generator.writer.Writer;

public class JavaServiceWriter implements Writer {

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

		String uperCaseName = StringUtils.uppercaseFirst(entity.getName());
		String outputPackage = "";
		try (BuilderOutputFile file = Utils.getOutputResource(Utils.getPackageName(properties.getControllerPackageName(), outputPackage), StringUtils.uppercaseFirst(entity.getName()) + "BusinessService.java",
				false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + ";");
			file.writeln(0, "");
			file.writeln(0, "import javax.persistence.*");
			file.writeln(0, "import org.springframework.data.domain.Page;");
			file.writeln(0, "import org.springframework.data.domain.Pageable;");
			file.writeln(0, "import org.springframework.beans.factory.annotation.Autowired;");
			file.writeln(0, "import org.springframework.data.jpa.domain.Specification;");
			file.writeln(0, "import java.util.*");
			file.writeln(0, "");
			file.writeln(0, "@Service");
			file.writeln(0, "public class " + uperCaseName + "Service extends " + uperCaseName + "GenericService {");
			file.writeln(1, "private final " + uperCaseName + "Repository repository;");
			file.writeln(0, "");
			file.writeln(1, "@Autowired");
			file.writeln(1, "public " + uperCaseName + "Service(" + uperCaseName + "Repository repository) {" );
			file.writeln(2, "this.repository = repository;");
			file.writeln(1, "}");
			/*
			 * Creates findAll methods with different parameters;
			 */
			file.writeln(1, "public List<" + uperCaseName + "> findAll() {");
			file.writeln(2, "return repository.findAll()");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "public Page<" + uperCaseName + "> findAll(Pageable pageSort) {");
			file.writeln(2, "return repository.findAll(pageSort)");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "public List<" + uperCaseName + "> findAll(Specification<" + uperCaseName + "> specification) {");
			file.writeln(2, "return repository.findAll(specification)");
			file.writeln(1, "}");
			file.writeln(0, "");
			/*
			 * Creates findById method;
			 */
			file.writeln(1, "public Optional<" + uperCaseName + "> findById(Long id) {");
			file.writeln(2, "return repository.findById(id)");
			file.writeln(1, "}");
			file.writeln(0, "");
			/*
			 * Creates save (update) method;
			 */
			file.writeln(1, "public " + uperCaseName + " save(" + uperCaseName + " " +  entity.getName() + ") {");
			file.writeln(2, "return repository.findById(id)");
			file.writeln(1, "}");
			file.writeln(0, "");
			/*
			 * Creates delete method;
			 */
			file.writeln(1, "public void deleteById(Long id) {");
			file.writeln(2, "return repository.deleteById(id)");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(0, "}");
		}

	}

}
