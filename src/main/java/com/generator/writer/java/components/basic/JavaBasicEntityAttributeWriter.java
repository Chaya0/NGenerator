package com.generator.writer.java.components.basic;

import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.Relation;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaBasicEntityAttributeWriter implements DefaultWriter {
	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());

		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getServicePackagePath(false, entity.getName()), upperCaseName + "Attribute.java", true)) {

			file.writeln(0, "package " + WriterUtils.getImportServicePackageName(false, entity.getName()) + ";");
			file.writeln(0, "");
			file.writeln(0, "import " + WriterUtils.getImportServicePackageName(false) + ".EntityAttribute;");
			file.writeln(0, "");
			file.writeln(0, "public enum " + upperCaseName + "Attribute implements EntityAttribute {");
			for (Attribute attribute : entity.getAttributes()) {
				file.writeln(1, StringUtils.camelToSnakeCase(attribute.getName()).toUpperCase() + "(\"" + attribute.getName() + "\"),");
			}
			for (Relation relation : entity.getRelations()) {
				if (relation.getRelationName() != null && (!relation.getRelationName().isEmpty() || relation.getRelationName().length() > 0)) {
					file.writeln(1, StringUtils.camelToSnakeCase(relation.getRelationName()).toUpperCase() + "(\"" + relation.getRelationName() + "\"),");
				} else {
					file.writeln(1, StringUtils.camelToSnakeCase(relation.getEntityName()).toUpperCase() + "(\"" + relation.getEntityName() + "\"),");
				}
			}
			file.writeln(1, ";");
			file.writeln(0, "");
			file.writeln(1, "private final String name;");
			file.writeln(0, "");
			file.writeln(1, upperCaseName + "Attribute(String name) {");
			file.writeln(2, "this.name = name;");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "@Override");
			file.writeln(1, "public String getName() {");
			file.writeln(2, "return name;");
			file.writeln(1, "}");
			file.writeln(0, "}");
		}
	}
}
