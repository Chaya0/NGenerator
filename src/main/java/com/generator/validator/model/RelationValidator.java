package com.generator.validator.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.generator.model.Entity;
import com.generator.model.Relation;
import com.generator.validator.Validator;
import com.generator.validator.exceptions.DuplicateRelationNameException;
import com.generator.validator.exceptions.InvalidEntityNameException;

public class RelationValidator implements Validator<Relation> {

	private List<Entity> entities;

	public RelationValidator(List<Entity> entities) {
		this.entities = entities;
	}

	@Override
	public void validate(Relation relation) throws Exception {
		validateDuplicateRelationNames(relation);
		validateRelationType(relation);
		validateEntityName(relation);
	}

	private void validateDuplicateRelationNames(Relation relation) throws Exception {
		Set<String> relationNames = new HashSet<>();
		if (!relationNames.add(relation.getRelationName())) {
			System.err.println("Duplicate relation name found: " + relation.getRelationName());
			throw new DuplicateRelationNameException(relation.getRelationName());
		}
	}

	private void validateRelationType(Relation relation) throws Exception {
		if (relation.getRelationType() == null) {
			System.err.println("Relation type cannot be null for relation: " + relation.getRelationName());
		}
	}

	/*
	 * private void validateFetchType(Relation relation) throws Exception {
	 * if (relation.getFetchType() == null) {
	 * System.err.println("Fetch type cannot be null for relation: " +
	 * relation.getRelationName());
	 * }
	 * }
	 */

	private void validateEntityName(Relation relation) throws Exception {
		if (!entities.stream().map(Entity::getName).collect(Collectors.toList()).contains(relation.getEntityName())) {
			throw new InvalidEntityNameException(relation.getEntityName());
		}
	}
}