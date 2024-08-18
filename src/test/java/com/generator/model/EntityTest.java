package com.generator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.model.enums.AttributeType;
import com.generator.model.enums.GenerationType;
import com.generator.model.enums.InheritanceType;
import com.generator.model.enums.RelationType;

public class EntityTest {

	private Entity entity;

	@BeforeEach
	public void setUp() {
		// Sample attributes and relations for testing
		Attribute attribute1 = new Attribute("status", AttributeType.ENUM, "StatusEnum", false, false, false);
		Attribute attribute2 = new Attribute("name", AttributeType.STRING, null, false, false, false);

		Relation relation1 = new Relation("User", "users", "userRelation", true, RelationType.ONE_TO_MANY, null, null);
		Relation relation2 = new Relation("Role", "roles", "roleRelation", false, RelationType.MANY_TO_ONE, null, null);

		List<Attribute> attributes = Arrays.asList(attribute1, attribute2);
		List<Relation> relations = Arrays.asList(relation1, relation2);

		entity = new Entity("User", "users", "BaseEntity", InheritanceType.SINGLE_TABLE, GenerationType.TABLE, relations, attributes);
	}

	@Test
	public void testRelationWithEntityNameExists() {
		assertTrue(entity.relationWithEntityNameExsists("User"));
		assertTrue(entity.relationWithEntityNameExsists("Role"));
		assertFalse(entity.relationWithEntityNameExsists("NonExistent"));
	}

	@Test
	public void testGetEnumsForImport() {
		List<String> expectedEnums = Arrays.asList("StatusEnum");
		assertEquals(expectedEnums, entity.getEnumsForImport());
	}

	@Test
	public void testToString() {
		String expectedString = "Entity [name=User, inherits=BaseEntity, inheritanceType=SINGLE_TABLE, generationType=TABLE, relations=[Relation(entityName=User, tableName=users, relationName=userRelation, owningSide=true, relationType=ONE_TO_MANY, fetchType=NULL), Relation(entityName=Role, tableName=roles, relationName=roleRelation, owningSide=false, relationType=MANY_TO_ONE, fetchType=NULL)], attributes=[Attribute(name=status, type=ENUM, enumName=StatusEnum, nullable=true, unique=false), Attribute(name=name, type=STRING, enumName=null, nullable=true, unique=false)]]";
		assertEquals(expectedString, entity.toString());
	}
}