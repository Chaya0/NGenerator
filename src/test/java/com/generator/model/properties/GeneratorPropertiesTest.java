package com.generator.model.properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.model.enums.ApplicationStructureType;
import com.generator.model.enums.AuthorisationType;
import com.generator.model.enums.DatabaseType;
import com.generator.model.enums.FrontendType;
import com.generator.model.enums.ModelFileType;

public class GeneratorPropertiesTest {

	private GeneratorProperties generatorProperties;

	@BeforeEach
	public void setUp() {
		generatorProperties = new GeneratorProperties();
	}

	@Test
	public void testDefaultValues() {
		assertFalse(generatorProperties.isGenerateSwaggerComponent());
		assertFalse(generatorProperties.isGenerateAuthorisationComponents());
		assertFalse(generatorProperties.isGeneratePermissionsOnEndpoints());
		assertFalse(generatorProperties.isGenerateQuartzComponents());
		assertFalse(generatorProperties.isGenerateFrontend());
		assertEquals(DatabaseType.POSTGRES, generatorProperties.getDatabaseType());
		assertEquals(AuthorisationType.JWT, generatorProperties.getAuthorisationType());
		assertEquals(FrontendType.ANGULAR, generatorProperties.getFrontendType());
		assertEquals(ModelFileType.XML, generatorProperties.getModelFileType());
		assertEquals(ApplicationStructureType.MONOLITHIC, generatorProperties.getApplicationStructureType());
		assertTrue(generatorProperties.isGenerateBasicRepository());
		assertTrue(generatorProperties.isGenerateGenericRepository());
		assertTrue(generatorProperties.isGenerateBasicService());
		assertTrue(generatorProperties.isGenerateGenericService());
		assertTrue(generatorProperties.isGenerateBasicController());
		assertTrue(generatorProperties.isGenerateGenericController());
		assertTrue(generatorProperties.isGenerateGenericEntity());
		assertTrue(generatorProperties.isGenerateGenericEnums());
		assertTrue(generatorProperties.isGenerateDTOs());
	}

	@Test
	public void testSettersAndGetters() {
		generatorProperties.setGenerateSwaggerComponent(true);
		assertTrue(generatorProperties.isGenerateSwaggerComponent());

		generatorProperties.setGenerateAuthorisationComponents(true);
		assertTrue(generatorProperties.isGenerateAuthorisationComponents());

		generatorProperties.setGeneratePermissionsOnEndpoints(true);
		assertTrue(generatorProperties.isGeneratePermissionsOnEndpoints());

		generatorProperties.setGenerateQuartzComponents(true);
		assertTrue(generatorProperties.isGenerateQuartzComponents());

		generatorProperties.setGenerateFrontend(true);
		assertTrue(generatorProperties.isGenerateFrontend());

		generatorProperties.setDatabaseType(DatabaseType.MYSQL);
		assertEquals(DatabaseType.MYSQL, generatorProperties.getDatabaseType());

		generatorProperties.setAuthorisationType(AuthorisationType.JWT);
		assertEquals(AuthorisationType.JWT, generatorProperties.getAuthorisationType());

		generatorProperties.setFrontendType(FrontendType.NULL);
		assertEquals(FrontendType.NULL, generatorProperties.getFrontendType());

		generatorProperties.setModelFileType(ModelFileType.JSON);
		assertEquals(ModelFileType.JSON, generatorProperties.getModelFileType());

		generatorProperties.setApplicationStructureType(ApplicationStructureType.MICROSERVICES);
		assertEquals(ApplicationStructureType.MICROSERVICES, generatorProperties.getApplicationStructureType());

		generatorProperties.setGenerateBasicRepository(false);
		assertFalse(generatorProperties.isGenerateBasicRepository());

		generatorProperties.setGenerateGenericRepository(false);
		assertFalse(generatorProperties.isGenerateGenericRepository());

		generatorProperties.setGenerateBasicService(false);
		assertFalse(generatorProperties.isGenerateBasicService());

		generatorProperties.setGenerateGenericService(false);
		assertFalse(generatorProperties.isGenerateGenericService());

		generatorProperties.setGenerateBasicController(false);
		assertFalse(generatorProperties.isGenerateBasicController());

		generatorProperties.setGenerateGenericController(false);
		assertFalse(generatorProperties.isGenerateGenericController());

		generatorProperties.setGenerateGenericEntity(false);
		assertFalse(generatorProperties.isGenerateGenericEntity());

		generatorProperties.setGenerateGenericEnums(false);
		assertFalse(generatorProperties.isGenerateGenericEnums());

		generatorProperties.setGenerateDTOs(false);
		assertFalse(generatorProperties.isGenerateDTOs());
	}
}