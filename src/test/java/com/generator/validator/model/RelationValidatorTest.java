package com.generator.validator.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.model.Entity;
import com.generator.model.Relation;
import com.generator.model.enums.FetchType;
import com.generator.model.enums.RelationType;
import com.generator.validator.exceptions.DuplicateRelationNameException;
import com.generator.validator.exceptions.InvalidEntityNameException;

public class RelationValidatorTest {

	private RelationValidator relationValidator;
	private List<Entity> entities;

	@BeforeEach
	public void setUp() {
		entities = Arrays.asList(new Entity("entity1", null, null, null, null, null, null), new Entity("entity2", null, null, null, null, null, null));
		relationValidator = new RelationValidator(entities);
	}

	@Test
	public void testValidateDuplicateRelationNames() throws Exception {
		Relation relation = new Relation("entity1", "table1", "relation1", true, RelationType.ONE_TO_ONE, FetchType.EAGER, null);
		// Add the same relation to the set to simulate duplication
		relationValidator.validate(relation);
		assertThrows(DuplicateRelationNameException.class, () -> relationValidator.validate(relation));
	}

	@Test
	public void testValidateRelationTypeWithNullType() throws Exception {
		Relation relation = new Relation("entity1", "table1", "relation1", true, null, FetchType.EAGER, null);
		assertThrows(NullPointerException.class, () -> relationValidator.validate(relation));
	}

	@Test
	public void testValidateInvalidEntityName() throws Exception {
		Relation relation = new Relation("invalidEntity", "table1", "relation1", true, RelationType.ONE_TO_ONE, FetchType.EAGER, null);
		assertThrows(InvalidEntityNameException.class, () -> relationValidator.validate(relation));
	}

	@Test
	public void testValidateValidRelation() throws Exception {
		Relation relation = new Relation("entity1", "table1", "relation1", true, RelationType.ONE_TO_ONE, FetchType.EAGER, null);
		relationValidator.validate(relation); // Should not throw an exception
	}
}