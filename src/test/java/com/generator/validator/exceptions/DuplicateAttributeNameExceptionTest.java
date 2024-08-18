package com.generator.validator.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DuplicateAttributeNameExceptionTest {

	@Test
	public void testExceptionMessage() {
		String attributeName = "testAttribute";
		DuplicateAttributeNameException exception = assertThrows(DuplicateAttributeNameException.class, () -> {
			throw new DuplicateAttributeNameException(attributeName);
		});

		assertEquals("Duplicate attribute name " + attributeName + ".", exception.getMessage());
	}
}