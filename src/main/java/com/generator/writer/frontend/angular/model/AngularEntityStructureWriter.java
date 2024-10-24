package com.generator.writer.frontend.angular.model;

import java.io.IOException;

import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.Relation;
import com.generator.model.enums.RelationType;
import com.generator.util.StringUtils;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

public class AngularEntityStructureWriter {

	public void create(AppModel appModel) throws Exception {
		for (Entity entity : appModel.getEntities()) {
			create(entity);
		}
	}

	public void create(Entity entity) throws Exception {
		String upperCaseName = StringUtils.uppercaseFirst(entity.getName());
		try (GeneratorOutputFile file = WriterUtils.getOutputResource(WriterUtils.getFrontendFeaturesEntitiesPath() + StringUtils.camelToKebabCase(entity.getName()),
				StringUtils.camelToKebabCase(entity.getName()) + "-structure.ts", true)) {
			file.writeln(0, "import {Attribute} from \"../../../core/entity-utils/attribute\";");
			file.writeln(0, "import {Structure} from \"../structure\";");
			file.writeln(0, "import {SearchField} from \"../../../core/entity-utils/search-field\";");
			file.writeln(0, "import {InputField} from \"../../../core/entity-utils/input-field\";");
			file.writeln(0, "");
			file.writeln(0, "export class " + upperCaseName + "Structure implements Structure {");
			file.writeln(0, "");
			file.writeln(1, "static #instance: " + upperCaseName + "Structure;");
			file.writeln(0, "");
			file.writeln(1, "entityName: string = '" + upperCaseName + "';");
			file.writeln(1, "title: string = '" + StringUtils.camelToSnakeCase(entity.getName()) + "';");
			file.writeln(1, "fkSearchAttribute: string[] = [''];");
			file.writeln(0, "");
			file.writeln(1, "attributes: Attribute[] = [");
			writeRelationsAttributes(entity, file);
			writeEntityAttributes(entity, file);
			file.writeln(1, "];");
			file.breakLine();
			file.writeln(0, "");
			file.writeln(1, "private constructor() {}");
			file.writeln(1, "public static get instance(): " + upperCaseName + "Structure {");
			file.writeln(2, "if(!" + upperCaseName + "Structure.#instance) {");
			file.writeln(3, upperCaseName + "Structure.#instance = new " + upperCaseName + "Structure();");
			file.writeln(2, "}");
			file.writeln(2, "return " + upperCaseName + "Structure.#instance;");
			file.writeln(1, "}");
			file.writeln(0, "}");
		}
	}

	private void writeEntityAttributes(Entity entity, GeneratorOutputFile file) throws IOException {
		for (Attribute attribute : entity.getAttributes()) {
			file.writeln(2, "new Attribute(");
			file.writeln(3, "'" + attribute.getName() + "',");
			file.writeln(3, "'" + attribute.getType().getCode() + "',");
			file.writeln(3, "SearchField" + getDisplayTypeForAttribute(attribute, false));
			file.writeln(3, "InputField" + getDisplayTypeForAttribute(attribute, true));
			file.writeln(2, "),");
		}
	}

	private void writeRelationsAttributes(Entity entity, GeneratorOutputFile file) throws IOException {
		for (Relation relation : entity.getRelations()) {
			if (relation.getRelationType().equals(RelationType.ONE_TO_MANY) || relation.getRelationType().equals(RelationType.MANY_TO_MANY)) continue;
			file.writeln(2, "new Attribute(");
			file.writeln(3, "'" + (relation.getRelationName() == null ? relation.getEntityName() : relation.getRelationName()) + "',");
			file.writeln(3, "'" + StringUtils.uppercaseFirst(relation.getEntityName()) + "',");
			file.writeln(3, "SearchField.DROPDOWN(),");
			file.writeln(3, "InputField.DROPDOWN()");
			file.writeln(2, "),");
		}
	}

	private String getDisplayTypeForAttribute(Attribute attribute, boolean inputField) {
		String value = "";
		switch (attribute.getType()) {
		case STRING, DOUBLE, INTEGER, LONG, NULL: {
			value = ".INPUT()";
			break;
		}
		case DATE, LOCAL_DATE, LOCAL_DATE_IME: {
			value = ".CALENDAR().maxLength(new Date())";
			break;
		}
		case ENUM: {
			value = ".DROPDOWN()";
			break;
		}
		case BOOLEAN: {
			value = ".CHECKBOX()";
			break;
		}
		default:
			value = ".INPUT()";
			break;
		}
		if (!attribute.isNullable() && inputField) value += ".required(true)";
		if (!inputField) value += ",";
		return value;
	}
}
