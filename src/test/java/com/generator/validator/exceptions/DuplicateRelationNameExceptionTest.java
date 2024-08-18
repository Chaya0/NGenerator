package com.generator.validator.exceptions;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DuplicateRelationNameExceptionTest {

	@Test
	public void testExceptionMessage() {
		String relationName = "testRelation";
		DuplicateRelationNameException exception = assertThrows(DuplicateRelationNameException.class, () -> {
			throw new DuplicateRelationNameException(relationName);
		});

		assertEquals("Duplicate relation name: " + relationName + '.', exception.getMessage());
	}
}