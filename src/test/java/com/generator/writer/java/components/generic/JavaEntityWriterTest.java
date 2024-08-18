package com.generator.writer.java.components.generic;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.generator.model.AppModel;
import com.generator.model.Attribute;
import com.generator.model.Entity;
import com.generator.model.Relation;
import com.generator.model.enums.AttributeType;
import com.generator.model.enums.FetchType;
import com.generator.model.enums.InheritanceType;
import com.generator.model.enums.RelationType;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

class JavaEntityWriterTest {

	@InjectMocks
	private JavaEntityWriter javaEntityWriter;

	@Mock
	private WriterUtils writerUtils;

	@Mock
	private GeneratorOutputFile generatorOutputFile;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testCreateAppModel() throws Exception {
		// Arrange
		AppModel appModel = new AppModel();
		Entity entity = new Entity();
		entity.setName("TestEntity");
		entity.setAttributes(new ArrayList<>());
		entity.setRelations(new ArrayList<>());
		entity.setInheritanceType(InheritanceType.NULL);

		List<Entity> entities = new ArrayList<>();
		entities.add(entity);
		appModel.setEntities(entities);

		// Mock WriterUtils
		when(writerUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(generatorOutputFile);

		// Act
		javaEntityWriter.create(appModel);

		// Assert
		verify(writerUtils, times(1)).getOutputResource(anyString(), eq("TestEntity.java"), eq(true));
		verify(generatorOutputFile, times(1)).writeln(anyInt(), anyString());
	}

	@Test
	void testCreateEntity() throws Exception {
		// Arrange
		Entity entity = new Entity();
		entity.setName("TestEntity");
		entity.setAttributes(new ArrayList<>());
		entity.setRelations(new ArrayList<>());
		entity.setInheritanceType(InheritanceType.NULL);

		// Mock WriterUtils
		when(writerUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(generatorOutputFile);

		// Act
		javaEntityWriter.create(entity);

		// Assert
		verify(writerUtils, times(1)).getOutputResource(anyString(), eq("TestEntity.java"), eq(true));
		verify(generatorOutputFile, times(1)).writeln(anyInt(), anyString());
	}

	@Test
	void testWriteAttributes() throws Exception {
		// Arrange
		Entity entity = new Entity();
		entity.setName("TestEntity");
		List<Attribute> attributes = new ArrayList<>();
		Attribute attribute = new Attribute();
		attribute.setName("name");
		attribute.setType(AttributeType.STRING);
		attribute.setNullable(false);
		attribute.setUnique(false);
		attributes.add(attribute);
		entity.setAttributes(attributes);

		// Mock WriterUtils
		when(writerUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(generatorOutputFile);

		// Act
		javaEntityWriter.create(entity);

		// Assert
		verify(generatorOutputFile).writeln(eq(1), eq("@Column(nullable = false)"));
		verify(generatorOutputFile).writeln(eq(1), eq("private String name;"));
	}

	@Test
	void testWriteRelations() throws Exception {
		// Arrange
		Entity entity = new Entity();
		entity.setName("TestEntity");
		List<Relation> relations = new ArrayList<>();
		Relation relation = new Relation();
		relation.setRelationType(RelationType.ONE_TO_MANY);
		relation.setEntityName("RelatedEntity");
		relation.setFetchType(FetchType.LAZY);
		relation.setOwningSide(true);
		relations.add(relation);
		entity.setRelations(relations);

		// Mock WriterUtils
		when(writerUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(generatorOutputFile);

		// Act
		javaEntityWriter.create(entity);

		// Assert
		verify(generatorOutputFile).writeln(eq(1), eq("OneToMany(mappedBy = \"TestEntity\", fetch = FetchType.LAZY)"));
		verify(generatorOutputFile).writeln(eq(1), eq("private List<RelatedEntity> relatedEntityList;"));
	}
}
