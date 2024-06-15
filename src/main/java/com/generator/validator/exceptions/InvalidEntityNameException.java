package com.generator.validator.exceptions;

public class InvalidEntityNameException extends Exception {
	private static final long serialVersionUID = 1L;

	public InvalidEntityNameException(String entityName) {
		super("Entity with " + entityName + " does not exsist!");
	}

}
