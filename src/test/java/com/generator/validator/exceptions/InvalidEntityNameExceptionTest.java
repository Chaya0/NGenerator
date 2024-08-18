package com.generator.validator.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class InvalidEntityNameExceptionTest {

	@Test
	public void testExceptionMessage() {
		String entityName = "testEntity";
		InvalidEntityNameException exception = assertThrows(InvalidEntityNameException.class, () -> {
			throw new InvalidEntityNameException(entityName);
		});

		assertEquals("Entity with " + entityName + " does not exsist!", exception.getMessage());
	}
}