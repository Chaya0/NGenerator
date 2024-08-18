package com.generator.validator.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnumDoesNotExsistsExceptionTest {

	@Test
	public void testExceptionMessage() {
		String enumName = "testEnum";
		EnumDoesNotExsistsException exception = assertThrows(EnumDoesNotExsistsException.class, () -> {
			throw new EnumDoesNotExsistsException(enumName);
		});

		assertEquals("Enum " + enumName + " does not exsists!", exception.getMessage());
	}
}