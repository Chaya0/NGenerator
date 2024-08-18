package com.generator.reader.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.model.enums.AttributeType;

public class AttributeTypeXmlAdapterTest {

	private AttributeTypeXmlAdapter adapter;

	@BeforeEach
	public void setUp() {
		adapter = new AttributeTypeXmlAdapter();
	}

	@Test
	public void testUnmarshal() throws Exception {
		// Test unmarshalling from a string to an AttributeType enum
		String code = "STRING";
		AttributeType result = adapter.unmarshal(code);
		assertEquals(AttributeType.STRING, result);
	}

	@Test
	public void testMarshal() throws Exception {
		// Test marshalling from an AttributeType enum to a string
		AttributeType attributeType = AttributeType.STRING;
		String result = adapter.marshal(attributeType);
		assertEquals("STRING", result);
	}

	@Test
	public void testUnmarshalInvalidValue() {
		// Test unmarshalling with an invalid value
		String invalidCode = "INVALID";
		assertThrows(Exception.class, () -> adapter.unmarshal(invalidCode));
	}

	@Test
	public void testMarshalNull() throws Exception {
		// Test marshalling with a null AttributeType
		assertThrows(NullPointerException.class, () -> adapter.marshal(null));
	}
}