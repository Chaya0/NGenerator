package com.generator.writer.java.components.generic;

import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.generator.model.AppModel;
import com.generator.model.EnumModel;
import com.generator.writer.utils.GeneratorOutputFile;
import com.generator.writer.utils.WriterUtils;

class JavaEnumWriterTest {

	@InjectMocks
	private JavaEnumWriter javaEnumWriter;

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
		EnumModel enumModel = new EnumModel();
		enumModel.setName("TestEnum");
		enumModel.setValues(Arrays.asList("VALUE_ONE", "VALUE_TWO"));

		List<EnumModel> enums = Arrays.asList(enumModel);
		appModel.setEnums(enums);

		// Mock WriterUtils
		when(writerUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(generatorOutputFile);

		// Act
		javaEnumWriter.create(appModel);

		// Assert
		verify(writerUtils, times(1)).getOutputResource(anyString(), eq("TestEnum.java"), eq(true));
		verify(generatorOutputFile, times(1)).writeln(0, "package " + WriterUtils.getImportModelPackageName() + ".enums;");
		verify(generatorOutputFile, times(1)).writeln(0, "public enum TestEnum {");
		verify(generatorOutputFile, times(1)).writeln(1, "VALUE_ONE,");
		verify(generatorOutputFile, times(1)).writeln(1, "VALUE_TWO,");
		verify(generatorOutputFile, times(1)).writeln(0, "}");
	}

	@Test
	void testCreateEnumModel() throws Exception {
		// Arrange
		EnumModel enumModel = new EnumModel();
		enumModel.setName("TestEnum");
		enumModel.setValues(Arrays.asList("VALUE_ONE", "VALUE_TWO"));

		// Mock WriterUtils
		when(writerUtils.getOutputResource(anyString(), anyString(), anyBoolean())).thenReturn(generatorOutputFile);

		// Act
		javaEnumWriter.create(enumModel);

		// Assert
		verify(writerUtils, times(1)).getOutputResource(anyString(), eq("TestEnum.java"), eq(true));
		verify(generatorOutputFile, times(1)).writeln(0, "package " + WriterUtils.getImportModelPackageName() + ".enums;");
		verify(generatorOutputFile, times(1)).writeln(0, "public enum TestEnum {");
		verify(generatorOutputFile, times(1)).writeln(1, "VALUE_ONE,");
		verify(generatorOutputFile, times(1)).writeln(1, "VALUE_TWO,");
		verify(generatorOutputFile, times(1)).writeln(0, "}");
	}
}
