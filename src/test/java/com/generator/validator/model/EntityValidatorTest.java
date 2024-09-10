package com.generator.validator.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.enums.AttributeType;
import com.generator.validator.exceptions.DuplicateAttributeNameException;
import com.generator.validator.exceptions.EmptyNameException;

public class EntityValidatorTest {

	private EntityValidator entityValidator;

	@BeforeEach
	public void setUp() {
		entityValidator = new EntityValidator();
	}

	@Test
	public void testValidateEntityNameWithNullName() {
		Entity entity = new Entity(null, null, null, null, null, null, null);
		assertThrows(EmptyNameException.class, () -> entityValidator.validate(entity));
	}

	@Test
	public void testValidateEntityNameWithEmptyName() {
		Entity entity = new Entity("", null, null, null, null, null, null);
		assertThrows(EmptyNameException.class, () -> entityValidator.validate(entity));
	}

	@Test
	public void testValidateAttributesWithDuplicateNames() {
		Attribute attr1 = new Attribute("attr", AttributeType.STRING, null, true, false, false);
		Attribute attr2 = new Attribute("attr", AttributeType.STRING, null, true, false, false);
		Entity entity = new Entity("entity", null, null, null, null, null, Arrays.asList(attr1, attr2));
		assertThrows(DuplicateAttributeNameException.class, () -> entityValidator.validate(entity));
	}

	@Test
	public void testValidateValidEntity() throws Exception {
		Attribute attr = new Attribute("attr", AttributeType.STRING, null, true, false, false);
		Entity entity = new Entity("entity", null, null, null, null, null, Collections.singletonList(attr));
		entityValidator.validate(entity); // Should not throw an exception
	}

	// Add more tests as needed for relations validation and other edge cases
}