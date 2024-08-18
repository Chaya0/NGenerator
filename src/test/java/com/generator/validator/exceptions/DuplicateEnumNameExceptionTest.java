package com.generator.validator.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DuplicateEnumNameExceptionTest {

	@Test
	public void testExceptionMessage() {
		String enumName = "testEnum";
		DuplicateEnumNameException exception = assertThrows(DuplicateEnumNameException.class, () -> {
			throw new DuplicateEnumNameException(enumName);
		});

		assertEquals("Duplicate enum name: " + enumName + '.', exception.getMessage());
	}
}