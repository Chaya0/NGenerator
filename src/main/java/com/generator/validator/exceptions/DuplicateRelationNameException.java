package com.generator.validator.exceptions;

public class DuplicateRelationNameException extends Exception {

	public DuplicateRelationNameException(String relationName) {
		super("Duplicate relation name: " + relationName + '.');
	}

	private static final long serialVersionUID = 1L;

}
