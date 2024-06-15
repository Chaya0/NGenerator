package com.generator.validator.exceptions;

public class DuplicateAttributeNameException extends Exception{
	public DuplicateAttributeNameException(String name) {
		super("Duplicate attribute name " + name + ".");
	}

	private static final long serialVersionUID = 1L;

}
