package com.generator.validator.exceptions;

public class EnumDoesNotExsistsException extends Exception{
	public EnumDoesNotExsistsException(String name) {
		super("Enum " + name + " does not exsists!");
	}

	private static final long serialVersionUID = 1L;

}
