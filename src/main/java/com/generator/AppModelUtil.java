package com.generator;

import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.Relation;
import com.generator.model.enums.AttributeType;
import com.generator.model.enums.CascadeType;
import com.generator.model.enums.FetchType;
import com.generator.model.enums.RelationType;
import com.generator.util.StringUtils;

public class AppModelUtil {
	public static void addAdditionalEntitiesAndAttributes(AppModel appModel) {
		if (Application.getGeneratorProperties().isGenerateAuthorisationComponents()) {
			addUserEntity(appModel);
		}
	}

	private static void addUserEntity(AppModel appModel) {
		Entity entity = appModel.getEntityByName("user");

		if (entity == null) {
			entity = createEntity("user");
			entity.getAttributes().add(createAttribute("username", AttributeType.STRING, true, false));
			entity.getAttributes().add(createAttribute("password", AttributeType.STRING, true, false, true));
			entity.getRelations().add(createRelation("role", RelationType.MANY_TO_ONE));
			appModel.getEntities().add(entity);
		} else {
			ensureAttributeExists(entity, "username", AttributeType.STRING, true, false);
			ensureAttributeExists(entity, "password", AttributeType.STRING, true, false, true);
			ensureRelationExsists(entity, "role", RelationType.MANY_TO_ONE);
		}
		if (Application.getGeneratorProperties().isGeneratePermissionsAndRoles()) {
			addRoleEntity(appModel);
			addPermissionEntity(appModel);
		}
	}

	private static void addRoleEntity(AppModel appModel) {
		Entity entity = appModel.getEntityByName("role");

		if (entity == null) {
			entity = createEntity("role", "role");
			entity.getAttributes().add(createAttribute("name", AttributeType.STRING, true, false));
			entity.getAttributes().add(createAttribute("description", AttributeType.STRING, false, true));
			entity.getRelations().add(createRelation("permission", RelationType.MANY_TO_MANY, FetchType.EAGER));
			appModel.getEntities().add(entity);
			return;
		} else {
			ensureAttributeExists(entity, "name", AttributeType.STRING, true, false);
			ensureAttributeExists(entity, "description", AttributeType.STRING, false, true);
			ensureRelationExsists(entity, "permission", RelationType.MANY_TO_MANY);
		}
	}

	private static void addPermissionEntity(AppModel appModel) {
		Entity entity = appModel.getEntityByName("permission");

		if (entity == null) {
			entity = createEntity("permission", "permission");
			entity.getAttributes().add(createAttribute("name", AttributeType.STRING, true, false));
			entity.getAttributes().add(createAttribute("description", AttributeType.STRING, false, true));
			appModel.getEntities().add(entity);
			return;
		} else {
			ensureAttributeExists(entity, "name", AttributeType.STRING, true, false);
			ensureAttributeExists(entity, "description", AttributeType.STRING, false, true);
		}
	}

	private static Entity createEntity(String entityName, String tableName) {
		Entity entity = new Entity();
		entity.setName(entityName);
		entity.setTableName(tableName);
		return entity;
	}

	private static Entity createEntity(String entityName) {
		return createEntity(entityName, StringUtils.camelToSnakeCase(Application.getSpringProperties().getName()) + entityName);
	}

	private static Attribute createAttribute(String name, AttributeType type, boolean unique, boolean nullable) {
		return createAttribute(name, type, unique, nullable, false);
	}

	private static Attribute createAttribute(String name, AttributeType type, boolean unique, boolean nullable, boolean systemVariable) {
		Attribute attribute = new Attribute();
		attribute.setName(name);
		attribute.setType(type);
		attribute.setUnique(unique);
		attribute.setNullable(nullable);
		attribute.setSystemVariable(systemVariable);
		return attribute;
	}

	private static void ensureAttributeExists(Entity entity, String attributeName, AttributeType type, boolean unique, boolean nullable) {
		ensureAttributeExists(entity, attributeName, type, unique, nullable, false);
	}

	private static void ensureAttributeExists(Entity entity, String attributeName, AttributeType type, boolean unique, boolean nullable, boolean systemVariable) {
		Attribute attribute = entity.getAttributeByAttributeName(attributeName);
		if (attribute == null) {
			attribute = createAttribute(attributeName, type, unique, nullable, systemVariable);
			entity.getAttributes().add(attribute);
		}
	}

	private static void ensureRelationExsists(Entity entity, String entityName, RelationType relationType) {
		ensureRelationExsists(entity, entityName, relationType, true);
	}

	private static void ensureRelationExsists(Entity entity, String entityName, RelationType relationType, boolean owningSide) {
		Relation relation = entity.getRelationByRelationName(entityName);
		if (relation == null) {
			relation = createRelation(entityName, relationType, FetchType.NULL, owningSide);
			entity.getRelations().add(relation);
		}
	}

	private static Relation createRelation(String entityName, RelationType relationType) {
		return createRelation(entityName, relationType, FetchType.NULL, true);
	}
	private static Relation createRelation(String entityName, RelationType relationType, FetchType fetchType) {
		return createRelation(entityName, relationType, fetchType, true);
	}

	private static Relation createRelation(String entityName, RelationType relationType, FetchType fetchType, boolean owningSide) {
		Relation relation = new Relation();
		relation.setEntityName(entityName);
		relation.setRelationType(relationType);
		relation.setOwningSide(owningSide);
		relation.setFetchType(fetchType);
		return relation;
	}

}