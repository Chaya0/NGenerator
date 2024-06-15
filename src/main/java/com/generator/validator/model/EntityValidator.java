package com.generator.validator.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.Relation;
import com.generator.validator.Validator;
import com.generator.validator.exceptions.DuplicateAttributeNameException;
import com.generator.validator.exceptions.EmptyNameException;

public class EntityValidator implements Validator<Entity> {

	@Override
	public void validate(Entity entity) throws Exception {
		validateEntityName(entity.getName());
		validateAttributes(entity.getAttributes());
		validateRelations(entity.getRelations());
	}

	private void validateEntityName(String entityName) throws Exception {
		if (entityName == null || entityName.isEmpty()) {
			throw new EmptyNameException("Entity name cannot be null or empty.");
		}
	}

	private void validateAttributes(List<Attribute> attributes) throws Exception {
		checkForDuplicateAttributeNames(attributes);
		for (Attribute attribute : attributes) {
			Validator<Attribute> attributeValidator = new AttributeValidator(); // Pass enum names if needed
			attributeValidator.validate(attribute);
		}
	}

	private void validateRelations(List<Relation> relations) throws Exception {
	}

	private void checkForDuplicateAttributeNames(List<Attribute> attributes) throws Exception {
		Set<String> attributeNames = new HashSet<>();
		for (Attribute attribute : attributes) {
			if (!attributeNames.add(attribute.getName())) {
				throw new DuplicateAttributeNameException(attribute.getName());
			}
		}
	}
}