package com.generator.reader.adapter;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.generator.model.enums.GenerationType;

public class GenerationTypeXmlAdapterTest {

	private GenerationTypeXmlAdapter adapter;

	@BeforeEach
	public void setUp() {
		adapter = new GenerationTypeXmlAdapter();
	}

	@Test
	public void testUnmarshal() throws Exception {
		String code = "IDENTITY";
		GenerationType result = adapter.unmarshal(code);
		assertEquals(GenerationType.IDENTITY, result);
	}

	@Test
	public void testMarshal() throws Exception {
		GenerationType generationType = GenerationType.IDENTITY;
		String result = adapter.marshal(generationType);
		assertEquals("IDENTITY", result);
	}

	@Test
	public void testUnmarshalInvalidValue() {
		String invalidCode = "INVALID";
		assertThrows(Exception.class, () -> adapter.unmarshal(invalidCode));
	}

	@Test
	public void testMarshalNull() throws Exception {
		assertThrows(NullPointerException.class, () -> adapter.marshal(null));
	}
}