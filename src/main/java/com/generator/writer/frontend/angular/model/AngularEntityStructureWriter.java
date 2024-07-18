package com.generator.writer.frontend.angular.model;

import java.io.IOException;

import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.Relation;
import com.generator.util.StringUtils;
import com.generator.writer.GeneratorOutputFile;
import com.generator.writer.Utils;

public class AngularEntityStructureWriter {
	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		try (GeneratorOutputFile file = Utils.getOutputResource(Utils.getFrontendFeaturesEntitiesPath() + StringUtils.camelToKebabCase(entity.getName()),
				StringUtils.camelToKebabCase(entity.getName()) + "-structure.ts", false)) {
			if (file.hasAlreadyExisted()) {
				return;
			}
			file.writeln(0, "import {Attribute} from \"../../../core/entity-utils/attribute\";");
			file.writeln(0, "import {Structure} from \"./structure\";");
			file.writeln(0, "");
			file.writeln(0, "export class " + upperCaseName + "Structure implements Structure {");
			file.writeln(0, "");
			file.writeln(1, "static #instance: " + upperCaseName + "Structure;");
			file.writeln(0, "");
			file.writeln(1, "entityName: string = '" + upperCaseName + "';");
			file.writeln(1, "title: string = '" + StringUtils.camelToSnakeCase(entity.getName()) + "';");
			file.writeln(1, "fkSearchAttribute: string = '';");
			file.writeln(0, "");
			file.writeln(1, "private constructor() {}");
			file.writeln(1, "public static get instance(): " + upperCaseName + "Structure {");
			file.writeln(2, "if(!" + upperCaseName + "Structure.#instance) {");
			file.writeln(3, upperCaseName + "Structure.#instance = new " + upperCaseName + "Structure();");
			file.writeln(2, "}");
			file.writeln(2, "return " + upperCaseName + "Structure.#instance;");
			file.writeln(1, "}");
			file.writeln(0, "");
			file.writeln(1, "attributes: Attribute[] = [");
			writeRelationsAttributes(entity, file);
			writeEntityAttributes(entity, file);

		}
	}

	private void writeEntityAttributes(Entity entity, GeneratorOutputFile file) throws IOException {
		for (Attribute attribute : entity.getAttributes()) {
			file.writeln(2, "new Attribute(");
			file.writeln(3, "'" + attribute.getName() + "',");
			file.writeln(3, "'" + attribute.getType().getCode() + "',");
			file.writeln(3, "null,");
			file.writeln(3, "null,");
			file.writeln(3, attribute.isNullable() + ",");
			file.writeln(3, attribute.isUnique() + ",");
			file.writeln(3, attribute.getEnumName() == null ? "false" : "true" + "");
			file.writeln(2, "),");
		}
		file.writeln(2, "new Attribute(");
		file.writeln(3, "'id',");
		file.writeln(3, "'primaryKey',");
		file.writeln(3, "null,");
		file.writeln(3, "null,");
		file.writeln(3, "false,");
		file.writeln(3, "true,");
		file.writeln(3, "false");
		file.writeln(2, ")");
		file.writeln(1, "];");
		file.writeln(0, "}");
	}

	private void writeRelationsAttributes(Entity entity, GeneratorOutputFile file) throws IOException {
		for (Relation relation : entity.getRelations()) {
			file.writeln(2, "new Attribute(");
			file.writeln(3, "'" + relation.getRelationName() == null ? relation.getEntityName() : relation.getRelationName() + "',");
			file.writeln(3, "'" + StringUtils.uppercaseFirst(relation.getEntityName()) + "',");
			file.writeln(3, "null,");
			file.writeln(3, "null,");
			file.writeln(3, "true,");
			file.writeln(3, "false,");
			file.writeln(3, "false");
			file.writeln(2, "),");
		}
	}

}
