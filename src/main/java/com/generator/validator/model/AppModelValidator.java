package com.generator.validator.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.EnumModel;
import com.generator.model.enums.AttributeType;
import com.generator.validator.Validator;
import com.generator.validator.exceptions.DuplicateEntityNameException;
import com.generator.validator.exceptions.DuplicateEnumNameException;
import com.generator.validator.exceptions.EnumNameIsRequiredExsistsException;

public class AppModelValidator implements Validator<AppModel> {

    @Override
    public void validate(AppModel appModel) throws Exception {
        validateEnums(appModel);
        validateEntities(appModel.getEntities());
    }

    private void validateEntities(List<Entity> entities) throws Exception {
        checkForDuplicateEntityNames(entities);
        for (Entity entity : entities) {
            Validator<Entity> entityValidator = new EntityValidator();
            entityValidator.validate(entity);
        }
    }

    private void validateEnums(AppModel appModel) throws Exception {
        Set<String> enumNames = new HashSet<>();
        for (EnumModel enumModel : appModel.getEnums()) {
            if (!enumNames.add(enumModel.getName())) {
                throw new DuplicateEnumNameException();
            }
        }
        for (Entity entity : appModel.getEntities()) {
            checkIfEnumNameIsUsedAndNotDeclared(entity, enumNames);
        }
    }

    private void checkIfEnumNameIsUsedAndNotDeclared(Entity entity, Set<String> enumNames) throws Exception {
        for (Attribute attribute : entity.getAttributes()) {
            if (attribute.getType() == AttributeType.ENUM && !enumNames.contains(attribute.getEnumName())) {
                throw new EnumNameIsRequiredExsistsException(entity.getName());
            }
        }
    }

    private void checkForDuplicateEntityNames(List<Entity> entities) throws Exception {
        Set<String> entityNames = new HashSet<>();
        for (Entity entity : entities) {
            if (!entityNames.add(entity.getName())) {
                throw new DuplicateEntityNameException(entity.getName());
            }
        }
    }
}