package com.generator.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.model.enums.AttributeType;

public class AttributeTest {

	private Attribute attribute;

	@BeforeEach
	public void setUp() {
		attribute = new Attribute("name", AttributeType.STRING, "enumName", true, true, false);
	}

	@Test
	public void testGetName() {
		assertEquals("name", attribute.getName());
	}

	@Test
	public void testGetType() {
		assertEquals(AttributeType.STRING, attribute.getType());
	}

	@Test
	public void testGetEnumName() {
		assertEquals("enumName", attribute.getEnumName());
	}

	@Test
	public void testIsNullable() {
		assertTrue(attribute.isNullable());
	}

	@Test
	public void testIsUnique() {
		assertTrue(attribute.isUnique());
	}

	@Test
	public void testIsSystemVariable() {
		assertFalse(attribute.isSystemVariable());
	}

	@Test
	public void testToString() {
		String expectedString = "Attribute(name=name, type=STRING, enumName=enumName, nullable=true, unique=true)";
		assertEquals(expectedString, attribute.toString());
	}
}