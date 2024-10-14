package com.generator.writer.java.dto;

import org.jsoup.internal.StringUtil;

import com.generator.Application;
import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.Relation;
import com.generator.model.enums.AttributeType;
import com.generator.util.StringUtils;
import com.generator.writer.DefaultWriter;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class JavaEntityDTOWriter implements DefaultWriter {

	@Override
	public void create(AppModel model) throws Exception {
		for (Entity entity : model.getEntities()) {
			create(entity);
		}
	}

	@Override
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());

		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getDTOPackagePath(), upperCaseName + ".java", true)) {

			file.writeln(0, "package " + Application.getSpringProperties().getPackageName() + ".dto;");
			file.writeln(0, "");
			file.writeln(0, "import jakarta.persistence.*;");
			file.writeln(0, "import jakarta.validation.constraints.NotEmpty;");
			file.writeln(0, "import jakarta.validation.constraints.NotNull;");
			file.writeln(0, "import lombok.Data;");
			file.writeln(0, "import lombok.AllArgsConstructor;");
			file.writeln(0, "import lombok.NoArgsConstructor;");
			file.writeln(0, "import com.fasterxml.jackson.annotation.JsonIgnore;");
			file.writeln(0, "import java.util.*;");
			file.writeln(0, "import java.time.*;");
			if (entity.getName().equalsIgnoreCase("permission")) {
				file.writeln(0, "import org.springframework.security.core.GrantedAuthority;");
			}
			for (String enumToImort : entity.getEnumsForImport()) {
				file.writeln(0, "import " + WriterUtils.getImportModelEnumsPackageName() + "." + enumToImort + ";");
			}
			file.writeln(0, "");
			file.writeln(0, "@Data");
			file.writeln(0, "@AllArgsConstructor");
			file.writeln(0, "@NoArgsConstructor");
			file.writeln(0, "public class " + upperCaseName + "DTO {");
			file.writeln(1, "private Long id;");

			writeAttributes(file, entity);
			writeRelations(file, entity);
			file.writeln(0, "");
			file.writeln(0, "}");
		}

	}

	private void writeAttributes(GeneratorOutputFile file, Entity entity) throws Exception {
		for (Attribute attribute : entity.getAttributes()) {
			if (attribute.getType().equals(AttributeType.ENUM)) {
				if (attribute.getEnumName().isEmpty()) throw new Exception("Attribute defined as Enum but enum name wasn't provided.");
				file.writeln(1, "@Enumerated(EnumType.STRING)");
				file.writeln(1, "private " + StringUtils.uppercaseFirst(attribute.getEnumName()) + " " + attribute.getName() + ";");
			} else {
				file.writeln(1, "private " + attribute.getType().getGeneratorCode() + " " + attribute.getName() + ";");
			}
		}
	}

	private void writeRelations(GeneratorOutputFile file, Entity entity) throws Exception {
		for (Relation relation : entity.getRelations()) {
			String relationName = StringUtil.isBlank(relation.getRelationName()) ? relation.getEntityName() : relation.getRelationName();
			switch (relation.getRelationType()) {
			case ONE_TO_MANY:
				file.writeln(1, "List<" + StringUtils.uppercaseFirst(relation.getEntityName()) + "> " + relationName + "List;");
				file.writeln(0, "");
				break;
			case ONE_TO_ONE:
				file.writeln(1, "private " + StringUtils.uppercaseFirst(relation.getEntityName()) + " " + relationName + ";");
				file.writeln(0, "");
				break;
			case MANY_TO_MANY:
				file.writeln(1, "private List<" + StringUtils.uppercaseFirst(relation.getEntityName()) + "> " + relationName + "List;");
				file.writeln(0, "");
				break;
			case MANY_TO_ONE:
				file.writeln(1, "private " + StringUtils.uppercaseFirst(relation.getEntityName()) + " " + relationName + ";");
				file.writeln(0, "");
				break;
			default:
				break;
			}
		}
	}
}
