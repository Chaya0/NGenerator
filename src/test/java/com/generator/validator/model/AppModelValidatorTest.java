package com.generator.validator.model;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.EnumModel;
import com.generator.model.enums.AttributeType;
import com.generator.validator.exceptions.DuplicateEntityNameException;
import com.generator.validator.exceptions.DuplicateEnumNameException;
import com.generator.validator.exceptions.EnumNameIsRequiredExsistsException;

public class AppModelValidatorTest {

	private AppModelValidator appModelValidator;

	@BeforeEach
	public void setUp() {
		appModelValidator = new AppModelValidator();
	}

	@Test
	public void testValidateEntitiesWithDuplicateEntityName() throws Exception {
		Entity entity1 = new Entity("entity", null, null, null, null, null, null);
		Entity entity2 = new Entity("entity", null, null, null, null, null, null);
		List<Entity> entities = Arrays.asList(entity1, entity2);

		AppModel appModel = new AppModel();
		appModel.setEntities(entities);
		appModel.setEnums(Arrays.asList());

		assertThrows(DuplicateEntityNameException.class, () -> appModelValidator.validate(appModel));
	}

	@Test
	public void testValidateEnumsWithDuplicateEnumName() throws Exception {
		EnumModel enum1 = new EnumModel("Enum1", Arrays.asList("VALUE1", "VALUE2"));
		EnumModel enum2 = new EnumModel("Enum1", Arrays.asList("VALUE3"));
		List<EnumModel> enums = Arrays.asList(enum1, enum2);

		Entity entity = new Entity("Entity1", null, null, null, null, null, Arrays.asList(new Attribute("attr1", AttributeType.ENUM, "Enum1", false, false, false)));
		List<Entity> entities = Arrays.asList(entity);

		AppModel appModel = new AppModel();
		appModel.setEntities(entities);
		appModel.setEnums(enums);

		assertThrows(DuplicateEnumNameException.class, () -> appModelValidator.validate(appModel));
	}

	@Test
	public void testValidateEnumsWithEnumNameNotDeclared() throws Exception {
		EnumModel enumModel = new EnumModel("Enum1", Arrays.asList("VALUE1"));
		List<EnumModel> enums = Arrays.asList(enumModel);

		Entity entity = new Entity("Entity1", null, null, null, null, null, Arrays.asList(new Attribute("attr1", AttributeType.ENUM, "UndefinedEnum", false, false, false)));
		List<Entity> entities = Arrays.asList(entity);

		AppModel appModel = new AppModel();
		appModel.setEntities(entities);
		appModel.setEnums(enums);

		assertThrows(EnumNameIsRequiredExsistsException.class, () -> appModelValidator.validate(appModel));
	}
}