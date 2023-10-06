package com.generator.reader;

import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.Relation;

public class Validator {

	public static void validateModel(AppModel appModel) throws Exception {
		checkForDuplicateName(appModel);
		for (Entity entity : appModel.getEntities()) {
			validateEntity(entity);
		}
	}

	private static void validateEntity(Entity entity) throws Exception {
		for (Attribute attribute : entity.getAttributes()) {
			validateAttribute(attribute);
		}
		for (Relation relation : entity.getRelations()) {
			validateRelation(relation);
		}
	}

	private static void checkForDuplicateName(AppModel appModel) throws Exception {

	}

	private static void validateAttribute(Attribute attribute) {

	}

	private static void validateRelation(Relation relation) throws Exception {

	}

}
