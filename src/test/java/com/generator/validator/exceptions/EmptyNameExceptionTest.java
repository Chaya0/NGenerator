package com.generator.validator.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EmptyNameExceptionTest {

	@Test
	public void testExceptionMessage() {
		String message = "Name cannot be empty";
		EmptyNameException exception = assertThrows(EmptyNameException.class, () -> {
			throw new EmptyNameException(message);
		});

		assertEquals(message, exception.getMessage());
	}
}