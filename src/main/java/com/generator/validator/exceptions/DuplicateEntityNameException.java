package com.generator.validator.exceptions;

public class DuplicateEntityNameException extends Exception{
	public DuplicateEntityNameException(String entityName) {
		super("Duplicate entity name: " + entityName+ '.');
	}

	private static final long serialVersionUID = 1L;

}
