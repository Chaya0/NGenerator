package com.generator.reader;

import static org.hamcrest.CoreMatchers.any;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.mockStatic;
import static org.mockito.Mockito.when;

import java.io.File;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.model.AppModel;
import com.generator.validator.Validator;
import com.generator.validator.model.AppModelValidator;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import jakarta.xml.bind.ValidationException;

public class XMLModelReaderTest {

	private XMLModelReader xmlModelReader;
	private JAXBContext jaxbContext;
	private Unmarshaller unmarshaller;
	private Validator<AppModel> validator;

	@BeforeEach
	public void setUp() throws Exception {
		xmlModelReader = new XMLModelReader();
		jaxbContext = mock(JAXBContext.class);
		unmarshaller = mock(Unmarshaller.class);
		validator = mock(AppModelValidator.class);

		mockStatic(LogManager.class);
		when(LogManager.getLogger(XMLModelReader.class)).thenReturn(mock(Logger.class));
	}

	@Test
	public void testReadModelSuccess() throws Exception {
		AppModel expectedAppModel = new AppModel();
		File mockFile = new File("./model.xml");

		when(jaxbContext.createUnmarshaller()).thenReturn(unmarshaller);
		when(unmarshaller.unmarshal(mockFile)).thenReturn(expectedAppModel);
		doNothing().when(validator).validate(expectedAppModel);

		AppModel result = XMLModelReader.readModel();

		assertNotNull(result);
		assertEquals(expectedAppModel, result);
	}

	@Test
	public void testReadModelJAXBException() throws Exception {
		File mockFile = new File("./model.xml");
		when(jaxbContext.createUnmarshaller()).thenReturn(unmarshaller);
		when(unmarshaller.unmarshal(mockFile)).thenThrow(new JAXBException("JAXBException"));

		AppModel result = XMLModelReader.readModel();

		assertNull(result);
	}

	@Test
	public void testReadModelValidationException() throws Exception {
		AppModel appModel = new AppModel();
		when(jaxbContext.createUnmarshaller()).thenReturn(unmarshaller);
		when(unmarshaller.unmarshal((File) any(File.class))).thenReturn(appModel);
		doThrow(new ValidationException("Validation failed")).when(validator).validate(appModel);

		AppModel result = XMLModelReader.readModel();

		assertNull(result);
	}

	@Test
    public void testReadModelException() throws Exception {
        when(jaxbContext.createUnmarshaller()).thenThrow(new JAXBException("Exception during JAXBContext creation"));

        AppModel result = XMLModelReader.readModel();

        assertNull(result);
    }

}