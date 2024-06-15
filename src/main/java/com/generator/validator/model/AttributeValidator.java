package com.generator.validator.model;

import com.generator.model.Attribute;
import com.generator.model.enums.AttributeType;
import com.generator.validator.Validator;
import com.generator.validator.exceptions.AttributeTypeIsNotDefinedException;
import com.generator.validator.exceptions.EmptyNameException;
import com.generator.validator.exceptions.EnumNameIsRequiredExsistsException;

public class AttributeValidator implements Validator<Attribute> {

	public AttributeValidator() {
	}

	@Override
	public void validate(Attribute attribute) throws Exception {
		validateAttributeName(attribute.getName());
		validateAttributeType(attribute);
		validateEnumName(attribute);
	}

	private void validateAttributeName(String attributeName) throws Exception {
		if (attributeName == null || attributeName.isEmpty()) {
			throw new EmptyNameException("Attribute name cannot be null or empty.");
		}
	}

	private void validateAttributeType(Attribute attribute) throws Exception {
		if (attribute.getType() == null) {
			throw new AttributeTypeIsNotDefinedException(attribute.getName());
		}
	}

	private void validateEnumName(Attribute attribute) throws Exception {
		if (attribute.getType() == AttributeType.ENUM) {
			if (attribute.getEnumName() == null) {
				throw new EnumNameIsRequiredExsistsException(attribute.getName());
			}
		}
	}
}