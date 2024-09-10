package com.generator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.model.enums.AttributeType;
import com.generator.model.enums.GenerationType;
import com.generator.model.enums.InheritanceType;
import com.generator.model.enums.RelationType;

public class AppModelTest {

	private AppModel appModel;

	@BeforeEach
	public void setUp() {
		// Creating sample entities and enums for testing
		Entity entity1 = new Entity("User", "users", "BaseEntity", InheritanceType.SINGLE_TABLE, GenerationType.TABLE,
				Arrays.asList(new Relation("Role", "roles", "userRoleRelation", true, RelationType.ONE_TO_MANY, null, null)),
				Arrays.asList(new Attribute("status", AttributeType.ENUM, "StatusEnum", false, false, false)));

		Entity entity2 = new Entity("Role", "roles", "BaseEntity", InheritanceType.JOINED, GenerationType.IDENTITY,
				Arrays.asList(new Relation("User", "users", "roleUserRelation", false, RelationType.MANY_TO_ONE, null, null)),
				Arrays.asList(new Attribute("name", AttributeType.STRING, null, false, false, false)));

		EnumModel enum1 = new EnumModel("UserRole", Arrays.asList("ADMIN", "USER"));
		EnumModel enum2 = new EnumModel("Status", Arrays.asList("ACTIVE", "INACTIVE"));

		List<Entity> entities = Arrays.asList(entity1, entity2);
		List<EnumModel> enums = Arrays.asList(enum1, enum2);

		appModel = new AppModel(entities, enums);
	}

	@Test
	public void testGetEntityByName() {
		Entity userEntity = appModel.getEntityByName("User");
		assertNotNull(userEntity);
		assertEquals("User", userEntity.getName());

		Entity roleEntity = appModel.getEntityByName("Role");
		assertNotNull(roleEntity);
		assertEquals("Role", roleEntity.getName());

		Entity nonExistentEntity = appModel.getEntityByName("NonExistent");
		assertNull(nonExistentEntity);
	}
}