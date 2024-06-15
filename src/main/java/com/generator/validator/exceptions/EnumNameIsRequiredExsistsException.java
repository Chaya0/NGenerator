package com.generator.validator.exceptions;

public class EnumNameIsRequiredExsistsException extends Exception{
	public EnumNameIsRequiredExsistsException(String name) {
		super("Attribute type is defined as ENUM but enumName is not specified for attribute " + name);
	}

	private static final long serialVersionUID = 1L;

}
