package com.generator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.model.enums.FetchType;
import com.generator.model.enums.RelationType;

public class RelationTest {

	private Relation relation;

	@BeforeEach
	public void setUp() {
		relation = new Relation("User", "users", "userRelation", true, RelationType.ONE_TO_MANY, FetchType.EAGER, null);
	}

	@Test
	public void testGetEntityName() {
		assertEquals("User", relation.getEntityName());
	}

	@Test
	public void testGetTableName() {
		assertEquals("users", relation.getTableName());
	}

	@Test
	public void testGetRelationName() {
		assertEquals("userRelation", relation.getRelationName());
	}

	@Test
	public void testIsOwningSide() {
		assertTrue(relation.isOwningSide());
	}

	@Test
	public void testGetRelationType() {
		assertEquals(RelationType.ONE_TO_MANY, relation.getRelationType());
	}

	@Test
	public void testGetFetchType() {
		assertEquals(FetchType.EAGER, relation.getFetchType());
	}

	@Test
	public void testToString() {
		String expectedString = "Relation(entityName=User, tableName=users, relationName=userRelation, owningSide=true, relationType=ONE_TO_MANY, fetchType=EAGER)";
		assertEquals(expectedString, relation.toString());
	}
}