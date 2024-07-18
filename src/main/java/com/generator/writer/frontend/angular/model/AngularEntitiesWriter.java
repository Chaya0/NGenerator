package com.generator.writer.frontend.angular.model;

import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.Relation;
import com.generator.model.enums.AttributeType;
import com.generator.util.StringUtils;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;

public class AngularEntitiesWriter {
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getFrontendFeaturesEntitiesPath() + StringUtils.camelToKebabCase(entity.getName()), StringUtils.camelToKebabCase(entity.getName())+ ".ts", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "export class " + upperCaseName + " {");
			for (Attribute attribute : entity.getAttributes()) {
				if (attribute.getType().equals(AttributeType.ENUM)) {
					file.writeln(1, attribute.getName() + "?: " + attribute.getEnumName() + ";");
				} else {
					file.writeln(1, attribute.getName() + "?: " + attribute.getType().getAngularTypeCode() + ";");
				}
			}
			for (Relation relation : entity.getRelations()) {
				file.writeln(1, relation.getRelationName() == null ? relation.getEntityName() : relation.getRelationName() + "?: " + StringUtils.uppercaseFirst(relation.getEntityName()) + ";");
			}
			file.writeln(0, "}");

		}
	}
}
