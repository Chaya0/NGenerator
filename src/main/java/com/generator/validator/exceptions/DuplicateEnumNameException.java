package com.generator.validator.exceptions;

public class DuplicateEnumNameException extends Exception{
	public DuplicateEnumNameException(String enumName) {
		super("Duplicate enum name: " + enumName+ '.');
	}

	private static final long serialVersionUID = 1L;

}
