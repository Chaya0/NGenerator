package com.generator.writer.java.components.basic;

import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;

public class JavaBasicServiceWriter implements DefaultWriter {
	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());

		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getServicePackagePath(false, entity.getName()), upperCaseName + "ServiceBasic.java", true)) {

			file.writeln(0, "package " + Utils.getImportServicePackageName(false, entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import " + Utils.getImportModelPackageName() + "." + upperCaseName + ";");
			file.writeln(0, "import " + Utils.getImportServicePackageName(false) + ".GenericService;");
			file.writeln(0, Utils.getApplicationExceptionImport());
			file.writeln(0, "import " + Utils.getImportRepositoryPackageName(false, entity.getName()) + "." + upperCaseName + "Repository" + ";");
			file.writeln(0, "import java.util.*;");
			file.writeln(0, "import java.time.*;");
			file.writeln(0, "import jakarta.persistence.EntityNotFoundException;");
			for(Attribute attribute : entity.getAttributes()) {
				if(attribute.getEnumName() != null && !attribute.getEnumName().isBlank()) {
					file.writeln(0, "import " + Utils.getImportModelEnumsPackageName() + "." + StringUtils.uppercaseFirst(attribute.getEnumName()) + ";");
				}
			}
			file.writeln(0, "");
			file.writeln(0, "public class " + upperCaseName + "ServiceBasic extends GenericService<" + upperCaseName + "> {");
			file.writeln(1, "protected final " + upperCaseName + "Repository repository;");
			file.writeln(0, "");
			file.writeln(1, "public " + upperCaseName + "ServiceBasic(" + upperCaseName + "Repository repository) {");
			file.writeln(2, "super(repository);");
			file.writeln(2, "this.repository = repository;");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "@Override");
			file.writeln(1, "public " + upperCaseName + " insert(" + upperCaseName + " object) throws " + Utils.getApplicationExceptionName() + " {");
			file.writeln(2, "if(object.getId() != null) throw new " + Utils.getApplicationExceptionName() + "(\"Entity already exists: \" + object.getId());");
			file.writeln(2, "return repository.save(object);");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "@Override");
			file.writeln(1, "public " + upperCaseName + " update(" + upperCaseName + " object) throws " + Utils.getApplicationExceptionName() + " {");
			file.writeln(2, "if(object.getId() == null || repository.findById(object.getId()).isEmpty()) throw new EntityNotFoundException(String.valueOf(object.getId()));");
			file.writeln(2, "return repository.save(object);");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "@Override");
			file.writeln(1, "public Class<" + upperCaseName + "> getEntityClass() {");
			file.writeln(2, "return " + upperCaseName + ".class;");
			file.writeln(1, "}");
			file.writeln(0, "");
			for(Attribute attribute : entity.getAttributes()) {
				if(attribute.getEnumName() != null && !attribute.getEnumName().isBlank()) {
					file.writeln(1, "public " + upperCaseName + " findBy" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + StringUtils.uppercaseFirst(attribute.getEnumName()) + " " + attribute.getName() + ") {");
				}else{
					file.writeln(1, "public " + upperCaseName + " findBy" + StringUtils.uppercaseFirst(attribute.getName()) + "(" + attribute.getType().getGeneratorCode() + " " + attribute.getName() + ") {");
				}
				file.writeln(2, "return repository.findBy" + StringUtils.uppercaseFirst(attribute.getName())+ "(" + attribute.getName() + ").orElseThrow();");
				file.writeln(1, "}");
			}
			file.writeln(0, "}");
		}
	}

}
