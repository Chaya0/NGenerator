package com.generator.writer.java.generic;

import com.generator.model.AppModel;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;
import com.generator.writer.Writer;

public class JavaGenericServiceWriter implements Writer {

	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getServicePackagePath(true), upperCaseName + "GenericService.java")) {
			
			file.writeln(0, "package " + Utils.getImportServicePackageName(true) + ";");
			file.writeln(0, "");
			file.writeln(0, "import javax.persistence.*");
			file.writeln(0, "import org.springframework.data.domain.Page;");
			file.writeln(0, "import org.springframework.data.domain.Pageable;");
			file.writeln(0, "import org.springframework.beans.factory.annotation.Autowired;");
			file.writeln(0, "import org.springframework.data.jpa.domain.Specification;");
			file.writeln(0, "import " + Utils.getImportModelPackageName() + "." + upperCaseName + ";");
			file.writeln(0, "import " + Utils.getImportRepositoryPackageName(true) + "." + upperCaseName + "GenericRepository" + ";");
			file.writeln(0, "import java.util.*;");
			file.writeln(0, "");
			file.writeln(0, "@Service");
			file.writeln(0, "public class " + upperCaseName + "GenericService {");
			file.writeln(1, "private final " + upperCaseName + "GenericRepository repository;");
			file.writeln(0, "");
			file.writeln(1, "@Autowired");
			file.writeln(1, "public " + upperCaseName + "Service(" + upperCaseName + "GenericRepository repository) {");
			file.writeln(2, "this.repository = repository;");
			file.writeln(1, "}");
			/*
			 * Creates findAll methods with different parameters;
			 */
			file.writeln(1, "public List<" + upperCaseName + "> findAll() {");
			file.writeln(2, "return repository.findAll()");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "public Page<" + upperCaseName + "> findAll(Pageable pageable) {");
			file.writeln(2, "return repository.findAll(pageable)");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "public List<" + upperCaseName + "> findAll(Specification<" + upperCaseName + "> specification) {");
			file.writeln(2, "return repository.findAll(specification)");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "public Page<" + upperCaseName + "> findAll(Specification<" + upperCaseName + "> specification, Pageable pageable) {");
			file.writeln(2, "return repository.findAll(specification, pageable)");
			file.writeln(1, "}");
			file.writeln(0, "");
			/*
			 * Creates findById method;
			 */
			file.writeln(1, "public Optional<" + upperCaseName + "> findById(Long id) {");
			file.writeln(2, "return repository.findById(id)");
			file.writeln(1, "}");
			file.writeln(0, "");
			/*
			 * Creates save (update) method;
			 */
			file.writeln(1, "public " + upperCaseName + " save(" + upperCaseName + " object) {");
			file.writeln(2, "return repository.save(object)");
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
