package com.generator.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.EnumModel;
import com.generator.model.Relation;
import com.generator.model.enums.AttributeType;
import com.generator.validator.exceptions.DuplicateAttributeNameException;
import com.generator.validator.exceptions.DuplicateEntityNameException;
import com.generator.validator.exceptions.DuplicateEnumNameException;
import com.generator.validator.exceptions.EmptyNameException;
import com.generator.validator.exceptions.EnumNameIsRequiredExsistsException;

class ValidatorFirstImpl {
	private static final Logger logger = LogManager.getLogger(ValidatorFirstImpl.class);
	
	public static void validateModel(AppModel appModel) throws Exception {
		logger.info("Validating...");
		
		validateEnums(appModel);
		validateEntities(appModel.getEntities());
		
		logger.info("Validation completed successfully!");
	}
	
	private static void validateEntities(List<Entity> entities) throws Exception {
		checkForDuplicateEntityNames(entities);
		for (Entity entity : entities) {
			validateEntity(entity);
		}
	}

	private static void validateEntity(Entity entity) throws Exception {
		validateAttributes(entity.getAttributes());
		
		for (Relation relation : entity.getRelations()) {
			validateRelations(relation);
		}
	}

	private static void checkForDuplicateEntityNames(List<Entity> entities) throws Exception {
		Set<String> entityNames = new HashSet<>();
		for (Entity entity : entities) {
			if (!entityNames.add(entity.getName())) {
				throw new DuplicateEntityNameException("");
			}
		}
	}

	private static void checkForDuplicateAttributeNames(List<Attribute> attributes) throws Exception {
		Set<String> attributeNames = new HashSet<>();
		for (Attribute attribute : attributes) {
			if (!attributeNames.add(attribute.getName())) {
				throw new DuplicateAttributeNameException(attribute.getName());
			}
		}
	}

	private static void validateAttributes(List<Attribute> attributes) throws Exception {
		checkForDuplicateAttributeNames(attributes);
		validateReservedWords(attributes);
		validateNonEmptyNames(attributes);
	}

	private static void validateRelations(Relation relation) throws Exception {

	}

	private static void validateEnums(AppModel appModel) throws Exception {
		Set<String> enumNames = new HashSet<>();
		for (EnumModel enumModel : appModel.getEnums()) {
			if (!enumNames.add(enumModel.getName())) {
				throw new DuplicateEnumNameException(enumModel.getName());
			}
		}
		for (Entity entity : appModel.getEntities()) {
			checkIfEnumNameIsUsedAndNotDeclared(entity, enumNames);
		}
	}

	private static void checkIfEnumNameIsUsedAndNotDeclared(Entity entity, Set<String> enumNames) throws Exception {
		for (Attribute attribute : entity.getAttributes()) {
			if (attribute.getType().equals(AttributeType.ENUM) && !enumNames.contains(attribute.getEnumName())) {
				throw new EnumNameIsRequiredExsistsException("");
			}
		}
	}

	private static void validateReservedWords(List<Attribute> attributes) throws Exception {
        Set<String> reservedWords = getReservedWords();
        for (Attribute attribute : attributes) {
            if (reservedWords.contains(attribute.getName().toUpperCase())) {
                System.err.println("Attribute name cannot be a reserved word: " + attribute.getName());
            }
        }
    }
	
	private static void validateNonEmptyNames(List<Attribute> attributes) throws Exception {
        for (Attribute attribute : attributes) {
            if (attribute.getName() == null || attribute.getName().isEmpty()) {
            	throw new EmptyNameException("Attribute name cannot be null or empty.");
            }
        }
    }
	
	private static Set<String> getReservedWords() {
        Set<String> reservedWords = new HashSet<>();
        reservedWords.add("SELECT");
        reservedWords.add("INSERT");
        reservedWords.add("DELETE");
        return reservedWords;
    }
}
