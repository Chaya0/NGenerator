package com.generator.validator.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AttributeTypeIsNotDefinedExceptionTest {

	@Test
	public void testExceptionMessage() {
		String attributeName = "testAttribute";
		AttributeTypeIsNotDefinedException exception = assertThrows(AttributeTypeIsNotDefinedException.class, () -> {
			throw new AttributeTypeIsNotDefinedException(attributeName);
		});

		assertEquals("Attribute type is not defined for attribute: " + attributeName, exception.getMessage());
	}
}