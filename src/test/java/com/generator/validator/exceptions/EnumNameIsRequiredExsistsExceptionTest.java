package com.generator.validator.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnumNameIsRequiredExsistsExceptionTest {

	@Test
	public void testExceptionMessage() {
		String attributeName = "testAttribute";
		EnumNameIsRequiredExsistsException exception = assertThrows(EnumNameIsRequiredExsistsException.class, () -> {
			throw new EnumNameIsRequiredExsistsException(attributeName);
		});

		assertEquals("Attribute type is defined as ENUM but enumName is not specified for attribute " + attributeName, exception.getMessage());
	}
}