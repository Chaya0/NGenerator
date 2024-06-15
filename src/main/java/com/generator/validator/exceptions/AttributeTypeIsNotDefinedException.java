package com.generator.validator.exceptions;

public class AttributeTypeIsNotDefinedException extends Exception {
	public AttributeTypeIsNotDefinedException(String name) {
		super("Attribute type is not defined for attribute: " + name);
	}

	private static final long serialVersionUID = 1L;

}
