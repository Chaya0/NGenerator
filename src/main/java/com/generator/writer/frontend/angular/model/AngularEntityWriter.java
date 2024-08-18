package com.generator.writer.frontend.angular.model;

import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.Relation;
import com.generator.model.enums.AttributeType;
import com.generator.model.enums.RelationType;
import com.generator.util.StringUtils;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class AngularEntityWriter {
	
	public void create(AppModel appModel) throws Exception{
		for(Entity entity : appModel.getEntities()) { 
			create(entity);
		}
	}
	
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendFeaturesEntitiesPath() + StringUtils.camelToKebabCase(entity.getName()), StringUtils.camelToKebabCase(entity.getName())+ ".ts", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			for (Relation relation : entity.getRelations()) {
				if(relation.getRelationType().equals(RelationType.ONE_TO_MANY) || relation.getRelationType().equals(RelationType.MANY_TO_MANY)) continue;
				String kebabCaseNameImport = StringUtils.camelToKebabCase(relation.getEntityName());
				file.writeln(0, "import { " + StringUtils.uppercaseFirst(relation.getEntityName()) + " } from '../" + kebabCaseNameImport + "/" + kebabCaseNameImport +  "';");
			}
			file.writeln(0, "");
			file.writeln(0, "export class " + upperCaseName + " {");
			for (Attribute attribute : entity.getAttributes()) {
				if (attribute.getType().equals(AttributeType.ENUM)) {
					file.writeln(1, "// " +  attribute.getName() + "?: " + attribute.getEnumName() + ";");
				} else {
					file.writeln(1, attribute.getName() + "?: " + attribute.getType().getAngularTypeCode() + ";");
				}
			}
			for (Relation relation : entity.getRelations()) {
				if(relation.getRelationType().equals(RelationType.ONE_TO_MANY) || relation.getRelationType().equals(RelationType.MANY_TO_MANY)) continue;
				file.writeln(1, (relation.getRelationName() == null ? relation.getEntityName() : relation.getRelationName()) + "?: " + StringUtils.uppercaseFirst(relation.getEntityName()) + ";");
			}
			file.writeln(0, "}");

		}
	}

}
