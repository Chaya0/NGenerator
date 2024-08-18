package com.generator.validator.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.model.Attribute;
import com.generator.model.enums.AttributeType;
import com.generator.validator.exceptions.AttributeTypeIsNotDefinedException;
import com.generator.validator.exceptions.EmptyNameException;
import com.generator.validator.exceptions.EnumNameIsRequiredExsistsException;

public class AttributeValidatorTest {

	private AttributeValidator attributeValidator;

	@BeforeEach
	public void setUp() {
		attributeValidator = new AttributeValidator();
	}

	@Test
	public void testValidateAttributeNameWithNullName() {
		Attribute attribute = new Attribute(null, AttributeType.STRING, null, true, false, false);
		assertThrows(EmptyNameException.class, () -> attributeValidator.validate(attribute));
	}

	@Test
	public void testValidateAttributeNameWithEmptyName() {
		Attribute attribute = new Attribute("", AttributeType.STRING, null, true, false, false);
		assertThrows(EmptyNameException.class, () -> attributeValidator.validate(attribute));
	}

	@Test
	public void testValidateAttributeTypeWithNullType() {
		Attribute attribute = new Attribute("attr", null, null, true, false, false);
		assertThrows(AttributeTypeIsNotDefinedException.class, () -> attributeValidator.validate(attribute));
	}

	@Test
	public void testValidateEnumNameWithNullEnumName() {
		Attribute attribute = new Attribute("attr", AttributeType.ENUM, null, true, false, false);
		assertThrows(EnumNameIsRequiredExsistsException.class, () -> attributeValidator.validate(attribute));
	}

	@Test
	public void testValidateValidAttribute() throws Exception {
		Attribute attribute = new Attribute("attr", AttributeType.STRING, null, true, false, false);
		attributeValidator.validate(attribute); // Should not throw an exception
	}
}
