package com.generator.validator.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DuplicateEntityNameExceptionTest {

	@Test
	public void testExceptionMessage() {
		String entityName = "testEntity";
		DuplicateEntityNameException exception = assertThrows(DuplicateEntityNameException.class, () -> {
			throw new DuplicateEntityNameException(entityName);
		});

		assertEquals("Duplicate entity name: " + entityName + '.', exception.getMessage());
	}
}