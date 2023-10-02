package com.generator.writer.java.generic;

import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.Relation;
import com.generator.model.enums.AttributeType;
import com.generator.util.StringUtils;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;
import com.generator.writer.Writer;

public class JavaEntityWriter implements Writer {
	private AppModel model;

	@Override
	public void create(AppModel model) throws Exception {
		this.model = model;
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		if (Utils.fileExists(Utils.getServicePackagePath(false), upperCaseName + ".java")) {
			return;
		}
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getModelPackagePath(), upperCaseName + ".java", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}

			file.writeln(0, "package " + Utils.getImportModelPackageName() + ";");
			file.writeln(0, "");
			file.writeln(0, "import lombok.Data");
			file.writeln(0, "import lombok.AllArgsConstructor");
			file.writeln(0, "import lombok.NoArgsConstructor");
			file.writeln(0, "import javax.persistence.*");
			file.writeln(0, "import java.util.*");
			file.writeln(0, "import com.fasterxml.jackson.annotation.JsonIgnore;");
			file.writeln(0, "@Data");
			file.writeln(0, "@AllArgsConstructor");
			file.writeln(0, "@NoArgsConstructor");
			file.writeln(0, "");
//			if(entity.getInhe)
			file.writeln(0, "@Entity(name = \"" + entity.getName() + "\")");
			file.writeln(0, "public class " + upperCaseName + " {");
			file.writeln(0, "");
			file.writeln(1, "@Id");
			file.writeln(1, "@GeneratedValue(strategy = GenerationType.IDENTITY)");
			file.writeln(1, "private Long id");

			writeAttributes(file, entity);

			writeRelations(file, entity);

			writeOtherSideRelations(file, model);
			file.writeln(0, "");
			file.writeln(0, "}");
		}

	}

	private void writeAttributes(GeneratorOutputFile file, Entity entity) throws Exception {
		for (Attribute attribute : entity.getAttributes()) {
			file.writeln(1, "@Column(nullable = " + attribute.isNullable() + ", unique = " + attribute.isUnique() + ")");

			if (attribute.getAttributeType().equals(AttributeType.ENUM)) {
				file.writeln(1, "@Enumerated(EnumType.STRING)");
			}
			file.writeln(1, "private " + attribute.getAttributeType().getCode() + " " + attribute.getName() + ";");
		}
	}

	private void writeRelations(GeneratorOutputFile file, Entity entity) throws Exception {
		for (Relation relation : entity.getRelations()) {
			switch (relation.getRelationType()) {
			case ONE_TO_MANY:
				file.writeln(1, "@OneToMany(mappedBy = \"" + StringUtils.lowercaseFirst(entity.getName()) + ")");
				file.writeln(1, "List<" + StringUtils.uppercaseFirst(relation.getEntityName()) + "> " + relation.getEntityName() + "List;");
				file.writeln(0, "");
				break;
			case MANY_TO_MANY:
				file.writeln(1, "@ManyToMany(mappedBy = \"" + entity.getName() + "List\")");
				file.writeln(1, "@JsonIgnore");
				file.writeln(1, "");
				file.writeln(0, "");
				break;
			case MANY_TO_ONE:
				file.writeln(1, "@ManyToOne");
				file.writeln(1, "@JoinColumn(name = \"" + relation.getEntityName() + "Id\")");
				file.writeln(1, "private " + StringUtils.uppercaseFirst(relation.getEntityName()) + " " + relation.getEntityName() + ";");
				file.writeln(0, "");
				break;
			case ONE_TO_ONE:
				file.writeln(0, "");
				file.writeln(0, "");
				file.writeln(0, "");
				file.writeln(0, "");
				break;
			}
		}
	}

	private void writeOtherSideRelations(GeneratorOutputFile file, AppModel model2) {
		// TODO Auto-generated method stub

	}

}
