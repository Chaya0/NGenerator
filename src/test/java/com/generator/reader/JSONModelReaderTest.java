package com.generator.reader;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.generator.model.AppModel;
import com.generator.validator.Validator;
import com.generator.validator.model.AppModelValidator;

import jakarta.xml.bind.ValidationException;

public class JSONModelReaderTest {

	private JSONModelReader jsonModelReader;
	private ObjectMapper objectMapper;
	private Validator<AppModel> validator;

	@BeforeEach
	public void setUp() {
		jsonModelReader = new JSONModelReader();
		objectMapper = mock(ObjectMapper.class);
		validator = mock(AppModelValidator.class);
	}

	@Test
	public void testReadModelSuccess() throws Exception {
		AppModel expectedAppModel = new AppModel();
		File mockFile = new File("./model.json");

		when(objectMapper.readValue(mockFile, AppModel.class)).thenReturn(expectedAppModel);
		doNothing().when(validator).validate(expectedAppModel);

		AppModel result = jsonModelReader.readModel();

		assertNotNull(result);
		assertEquals(expectedAppModel, result);
	}

	@Test
	public void testReadModelIOException() throws Exception {
		File mockFile = new File("./model.json");
		when(objectMapper.readValue(mockFile, AppModel.class)).thenThrow(new IOException());

		AppModel result = jsonModelReader.readModel();

		assertNull(result);
	}

	@Test
	public void testReadModelValidationException() throws Exception {
		AppModel appModel = new AppModel();
		when(objectMapper.readValue(any(File.class), eq(AppModel.class))).thenReturn(appModel);
		doThrow(new ValidationException("Validation failed")).when(validator).validate(appModel);

		AppModel result = jsonModelReader.readModel();

		assertNull(result);
	}

}
